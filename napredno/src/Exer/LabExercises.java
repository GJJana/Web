package Exer;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class LabExercises {
    List<Student> students=new ArrayList<>();
    public void addStudent (Student student){
        this.students.add(student);
    }
    public void printByAveragePoints (boolean ascending, int n){
        //сортирани според сумарните поени
        //доколку се исти сумарните поени, според индексот,
        //во растечки редослед доколку ascending е true, a во спротивно во опаѓачки.

        if(ascending)
           this.students= this.students.stream().sorted(new StudentComD()).collect(Collectors.toList());
        else
            this.students= this.students.stream().sorted(new StudentComA()).collect(Collectors.toList());
        this.students.stream().limit(n).forEach(System.out::println);
    }

    public List<Student> failedStudents (){
        List<Student> tmp=new ArrayList<>();
        this.students.stream().filter(x->x.getPoints().size()<8).forEach(tmp::add);
        tmp.sort(new StudentComByIndex());
        tmp.sort(new StudentComByPoints());
        return tmp;
    }

    public Map<Integer,Double> getStatisticsByYear(){

        List<Student> t=this.failedStudents();
        List<Student> tmp=this.students;
        tmp.removeAll(t);
        Map<Integer,Double> map=new HashMap<>();
        System.out.println(tmp.size());
        tmp.stream().forEach(x->{
            map.putIfAbsent(20-Integer.parseInt(x.getIndex().substring(0,2)),
                    tmp.stream().filter(k->k.getIndex().substring(0,2).equals(x.getIndex().substring(0,2))).mapToDouble(Student::sumPoints).sum()/tmp.stream().filter(k->k.getIndex().substring(0,2).equals(x.getIndex().substring(0,2))).count());

        });
       // map=map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        return map;
    }

    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(0 ,1 ,1 ,1, 0, 1 ,0 ,1);
        Student s=new Student("181564",list);
        System.out.println(s.sumPoints());
        LabExercises lab=new LabExercises();
        lab.addStudent(s);
        String k=Stream.generate(()->"*").limit(6).collect(Collectors.joining());
        System.out.println(String.format("%2d|%s",6,k));





    }
}
