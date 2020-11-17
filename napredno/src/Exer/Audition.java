package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Audition{
    Map<String,List<Participant>> mapa;
    public Audition(){
        mapa=new HashMap<>();
    }
    void addPrticipant(String city,String code,String name, int age){
        if(mapa.containsKey(city))
        {
            boolean match=mapa.get(city).stream().anyMatch(x->x.code.equals(code));
            if(match)
                return;
            else mapa.get(city).add(new Participant(code,name,age));
        }else {
            List<Participant> list=new ArrayList<>();
            list.add(new Participant(code,name,age));
            mapa.put(city,list);
        }
    }
    void listBycity(String city){
         mapa.get(city).stream().sorted()
                 .forEach(x->System.out.println(x.toString()));
    }

    class Participant implements Comparable<Participant>{
        //String city;
        String code;
        String name;
        int age;

        public Participant(String code,String name, int age){
            //this.city=city;
            this.code=code;
            this.name= name;
            this.age= age;
        }

        @Override
        public int compareTo(Participant o) {
            if(o.name.equals(this.name))
            {
                if(this.age>o.age)
                    return 0;
                else return -1;
            }else return this.name.compareTo(o.name);

        }

        @Override
        public String toString() {
            return "Participant{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader( new InputStreamReader(System.in));
        Audition audition=new Audition();
        for(int i=0;i<4;i++) {
            String[] parts = br.readLine().split(" ");
            audition.addPrticipant(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]));
        }
        audition.listBycity(br.readLine());

    }
}