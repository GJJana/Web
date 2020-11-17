package Exer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Audition1 {

    public Map<String,List<Candidate>> map;

    public Audition1() {
        this.map=new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age){
        map.putIfAbsent(city,new ArrayList<Candidate>());
        map.get(city).add(new Candidate(code,name,age));

    }
   public void listByCity(String city){

        map.get(city).stream().sorted().forEach(System.out::println);
   }


    public static void main(String[] args) {
        Audition1 a=new Audition1();
        a.addParticipant("Skopje","A12","Jana",34);
        a.addParticipant("Skopje","Mk2","Jana",25);
        a.addParticipant("Bitola","MMM","Em",34);
        a.addParticipant("Bitola","A14","Bobo",11);
        a.addParticipant("Bitola","A112","Marija",10);
        a.listByCity("Bitola");

    }
}
