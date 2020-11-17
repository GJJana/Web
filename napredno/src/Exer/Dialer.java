/*package Exer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dialer {

    List<Contact> contacts;
    Map<Contact, Set<Call>> map;
    Dialer(List<Contact> contacts)
    {
        this.contacts=contacts;
        Set<Call> set=new HashSet<>();
        for(Contact c:contacts)
        {
            map.put(c,set);
        }



    }

    void readCalls(InputStream in) throws IOException,WrongCallException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        //ako ne pocnuva brojot so 07
        //mora da e dolg 9 char
        //duration ne e vo format HH:mm:ss
        String line=br.readLine();
        int k=in.read();
        while(line!=null)
        {
            String [] parts=line.split("");
            if(!parts[0].startsWith("07")||parts[1].length()!=9||!parts[2].equals(String.format("%s%s:%s%s:%s%s")))
                    throw WrongCallException();
            line=br.readLine();
        }
    }
    void makeCall(String number ,long timestamp,String duration,String type)throws WrongCallException{
            //kontakt sto vo svojata lista broevi go ima number
        //ako ne postoi takov togas se dodava so imeto unknown
        map.values().stream()
                .filter(x->x.contains(number))
                .findAny()
                .map(x->x.add(new Call(number,timestamp,duration,type)))
                .orElse(map.put(new Contact("Unknown"),new HashSet<>(new Call(number,timestamp,duration,type))));

    }
    void printCallsOfType(OutputStream outputStream, String type){
        //site povici spored type sortirani spored vremetraenje
        map.values().stream()
                .forEach(x->x.stream()
                .filter(y->y.type==type).sorted().forEach(z->System.out.printf("%-15s %10l %15s %10s",z.number,z.timestamp,z.duration,z.type)));
    }
    void printCallsByContact(OutputStream outputStream,Contact contact){
        map.get(contact).stream()
        .sorted().forEach(z->System.out.printf("%-15s %10l %15s %10s",z.number,z.timestamp,z.duration,z.type));
    }
    void printAllCalls(OutputStream outputStream){
        map.keySet().stream()
                .forEach(x->System.out.println(x.ime,map.get(x).stream().forEach(z->System.out.printf("%-15s %10l %15s %10s",z.number,z.timestamp,z.duration,z.type))));
    }


    class Contact{
        String ime;
        List<String >numbers;
        Contact(String ime)
        {
            this.numbers=new ArrayList<>();

        }

        Contact(String ime,List<String> numbers)this
        {
            this.ime=ime;
            this.numbers=numbers;
        }
    }
    class Call implements Comparable<Call>{
        String number;
        long timestamp;
        String duration;
        String type;
        Call(String number,long timestamp,String duration,String type)
        {
            this.duration=duration;
            this.number=number;
            this.timestamp=timestamp;
            this.type=type;
        }

        @Override
        public int compareTo(Call o) {
            if(this.timestamp==o.timestamp)
                return this.number.compareTo(o.number);
            if(this.timestamp<o.timestamp)
                return -1;
            else return 0;
        }
    }
    class WrongCallException extends Exception{
        public WrongCallException()
        {
            super("");
        }
    }




}
*/