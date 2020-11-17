package Exer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StudentRecords {


    private Map<String,List<Student1>> mapStuden=new TreeMap<>();
    private Map<String,Integer> mapDesetki=new HashMap<>();

    public int readRecords(InputStream inputStream) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        String s;
        int i=0;
        while ((s=br.readLine())!=null) {
            String[] p = s.split(" ");
            List<Integer> tmp = new ArrayList<>();
            Arrays.asList(p).subList(2, p.length).forEach(g -> tmp.add(Integer.parseInt(g)));
            mapStuden.putIfAbsent(p[1],new ArrayList<>());
            mapStuden.get(p[1]).add(new Student1(p[0],tmp));
            i++;
        }

        mapStuden.forEach((k,v)->
                mapDesetki.put(k,v.stream().mapToInt(l->l.brOceni(10)).sum()));
        mapDesetki=mapDesetki.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
        return i;
    }
    public void writeTable(OutputStream outputStream) throws IOException {
        //ортирани според просекот во опаѓачки редослед (ako se so ist prosek sporde kod)
        this.mapStuden.forEach((k,v)->{
            try {
                outputStream.write((k+"\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            v.stream().sorted().forEach(l-> {
                try {
                    outputStream.write((l.toString()+"\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        outputStream.flush();
    }
    void writeDistribution(OutputStream outputStream)throws IOException{
        mapDesetki.keySet().forEach(k->
                {
                    try {
                        outputStream.write((k+"\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    IntStream.range(6,11).forEach(b->{
                        int c=this.mapStuden.get(k).stream().mapToInt(l->l.brOceni(b)).sum();
                        int del;
                        if(c%10==0)
                            del=c/10;
                        else del=(c/10)+1;
                        try {
                            outputStream.write(String.format("%2d | %s(%d)\n",b, Stream.generate(()->"*").limit(del).collect(Collectors.joining()),c).getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        outputStream.flush();


    }

    public static void main(String[] args)throws IOException {
        System.out.println("=== READING RECORDS ===");
        StudentRecords studentRecords = new StudentRecords();
        int total = studentRecords.readRecords(System.in);
        System.out.printf("Total records: %d\n", total);
        System.out.println("=== WRITING TABLE ===");
        studentRecords.writeTable(System.out);
        System.out.println("=== WRITING DISTRIBUTION ===");
        studentRecords.writeDistribution(System.out);
    }

}
