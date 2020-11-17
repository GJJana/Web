package Exer;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
        private List<Task> tasks=new ArrayList<>();
        private Map<String,List<Task>> category=new HashMap<>();


    public void readTasks (String te) throws IOException,DeadlineNotValidException {
        //[категорија],[име_на_задача],[oпис],[рок_за_задачата],[приоритет]
        //BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        List<String> m=Arrays.asList(te.split("\n"));

        Task t;
        for(String str:m){
            //System.out.println(str);
            List<String>list=new ArrayList<>(Arrays.asList(str.split(",")));
            //System.out.println(list.toString());
               if(list.size()<5)
                    t=new Task(list.get(0),list.get(1),list.get(2));
                else
                    t=new Task(list.get(0),list.get(1),list.get(2),LocalDateTime.parse(list.get(3)),Integer.parseInt(list.get(4)));
            //System.out.println(t.toString());
                tasks.add(t);
                category.putIfAbsent(list.get(0),new ArrayList<>());
                category.get(list.get(0)).add(t);



        }



    }
    void printTasks(OutputStream os, boolean includePriority, boolean includeCategory) throws IOException{


        //includeCategory да се испечатат задачите групирани според категории, во спротивно се печатат сите внесени задачи
        if(includeCategory)
        {
            if(includePriority)
            {
                this.category.forEach((key,val)->{
                    try {
                        os.write(key.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(key);
                    val.stream().sorted(new TaskPCom()).forEach(l-> {
                        try {
                            os.write(l.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(l.toString());
                    });
                });
            }
            else {
                this.category.forEach((key,val)->{
                    try {
                        os.write(key.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(key);
                    val.stream().sorted(new TaskCom()).forEach(l-> {
                        try {
                            os.write(l.toString().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(l.toString());
                    });
                });

            }

        }
        else {

            if (includePriority) {
                this.tasks.stream().sorted(new TaskPCom()).forEach(l-> {
                    try {
                        os.write(l.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(l.toString());
                });

            }
            else {
                this.tasks.stream().sorted(new TaskCom()).forEach(l-> {
                    try {
                        os.write(l.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(l.toString());
                });

            }

        }
        //includePriority spored prioritet sortirani ; rastecki spore sega-data


    }

    public static void main(String[] args) throws IOException,DeadlineNotValidException {

        TaskManager manager = new TaskManager();

        System.out.println("Tasks reading");
        try{
            manager.readTasks("School,NP,lab 1 po NP,2021-06-23T23:59:59.000,1\n" +
                    "School,NP,lab 2 po NP,2021-06-27T23:59:59.000\n" +
                    "School,NP,lab 3 po NP,2021-07-04T23:59:59.000,2\n" +
                    "School,NP,lab 4 po NP,2021-07-11T23:59:59.000\n" +
                    "Home,NP,prepare for June exam :)\n" +
                    "Home,NP,solve all exercises,3\n");}
        catch(DeadlineNotValidException e){
            new DeadlineNotValidException("Nevaliden");
        }
        System.out.println("By categories with priority");
        manager.printTasks(System.out, true, true);
        System.out.println("-------------------------");
        System.out.println("By categories without priority");
        manager.printTasks(System.out, false, true);
        System.out.println("-------------------------");
        System.out.println("All tasks without priority");
        manager.printTasks(System.out, false, false);
        System.out.println("-------------------------");
        System.out.println("All tasks with priority");
        manager.printTasks(System.out, true, false);
        System.out.println("-------------------------");

    }

}
