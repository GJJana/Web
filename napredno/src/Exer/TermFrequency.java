package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TermFrequency {
    private Map<String,Integer> freqWords;
    private String sen;



    public  String srediZbor(String zbor ){
        StringBuilder sb=new StringBuilder();
        char[]parts=zbor.toCharArray();
        for(int i=0;i<parts.length;i++){
            if(Character.isAlphabetic(parts[i])){
                if(Character.isUpperCase(parts[i]))
                    sb.append(Character.toLowerCase(parts[i]));
                    else
                        sb.append(parts[i]);
            }

        }
        return sb.toString();
    }


    public TermFrequency(InputStream inputStream, String[] stopWords) throws IOException {
        //stopWords zborovi koi treba da se ignoriraat
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb =new StringBuilder();
        String line;
        while((line=br.readLine())!=null)
        {
            sb.append(line).append(" ");
        }

        this.sen=sb.toString();
        List<String> t=Arrays.asList(sen.split(" "));
        t.forEach(x->x=this.srediZbor(x));
        List<String> tmp=Arrays.asList(stopWords);
        tmp.forEach(x->x=this.srediZbor(x));
        Set<String> set=new TreeSet<>(t);
        set.forEach(y->{
            if(tmp.contains(y))
                set.remove(y);
        });
        this.freqWords=new TreeMap<>();
        set.forEach(x->freqWords.put(x,0));
    }
    public int countTotal(){
        return this.sen.length();
    }
    public int countDistinct(){
        //unikatni zbotovi
        return this.freqWords.size();
    }
   public List<String> mostOften(int k){
        List<String> tmp=new ArrayList<>();

        this.freqWords.values().stream().sorted().forEach(x->{
            String l=this.freqWords.keySet().stream().filter(y->this.freqWords.get(y).equals(x))
                    .findFirst().orElse(null);

            if(l!=null&&tmp.size()!=k) {
                tmp.add(l);
            }
        });
        return tmp;
   }

    public static void main(String[] args) throws IOException {

        String sen="Jana mnogu saka da jade.";
        String [] stopWords={"kazi","saka","jade"};
        List<String> t=Arrays.asList(sen.split(" "));
        //t.forEach(x->x=srediZbor(x));
        t.forEach(System.out::println);
        List<String> tmp=Arrays.asList(stopWords);
        Set<String> set=new TreeSet<>(t);
        set.forEach(x->{
            if(tmp.contains(x))
                set.remove(x);
        });
        set.forEach(System.out::println);
        System.out.println("novo");




    }
}
