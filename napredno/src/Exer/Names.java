package Exer;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Names {
    public static void main(String[] args) throws IOException {
        File file1=new File("C:\\Users\\janag\\IdeaProjects\\napredno\\src\\Exer\\Mesures.txt");
        File file2=new File("C:\\Users\\janag\\IdeaProjects\\napredno\\src\\Exer\\Measures2");
        BufferedReader br=new BufferedReader(new FileReader(file1));
        BufferedReader br2=new BufferedReader(new FileReader(file2));
        String s;
        Set<String> names1= new HashSet<>();
        while((s=br.readLine())!=null){
            names1.add(s.split(" ")[0]);
        }
        Set<String> names2= new HashSet<>();
        while((s=br2.readLine())!=null){
            names2.add(s.split(" ")[0]);
        }
        System.out.println(names1.stream().filter(names2::contains).count());



    }
}
