package Exer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static Exer.CosineSimilarityCalculator.cosineSimilarity;
import static java.util.stream.Collectors.toMap;


class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
        int [] array1;
        int [] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}
public class TextProcessor {
    private Map<String,List<Integer>> texts=new HashMap<>();
    private Set<String> words=new TreeSet<>();
    public TextProcessor(){}
    //zbor so frekfencija na pojavuvanje
    private Map<String,Integer> freqWords=new TreeMap<>();
   // private List<String> original=new ArrayList<>();

   private String eliminacijaText(String s){
        StringBuilder sb=new StringBuilder();
        char[] c=s.toCharArray();
        for (char value : c) {
            if (Character.isAlphabetic(value))
                sb.append(Character.toLowerCase(value));
            if(Character.isSpaceChar(value)||value=='\'')
                sb.append(value);
        }
        return sb.toString();
    }
   private void addWords(String text){
        String [] w=text.split(" ");
        for (String s : w) {
            freqWords.putIfAbsent(s, 0);
            Integer k=freqWords.get(s)+1;
            freqWords.put(s,k);
        }
    }
    private void vectorText(){
       //gradenje vektori
        this.texts.forEach((key, value) -> this.freqWords.keySet().forEach(m->value.add((int) Arrays.stream(eliminacijaText(key).split(" ")).filter(l->l.equals(m)).count())));

    }
    public void readText (InputStream is) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String s ;
        while((s=br.readLine())!=null){

            this.texts.put(s,new ArrayList<>());
            this.addWords(eliminacijaText(s));
        }
        //mapa za text so vektor
        vectorText();

    }
    public  void printTextsVectors (OutputStream os)throws IOException{

       for(List<Integer> k:this.texts.values()){
           System.out.println(k);

            os.write(k.toString().getBytes());
           os.write("\n".getBytes());
       }
       os.flush();


       /* this.texts.values().forEach(v-> {
            v.forEach(m -> {
                System.out.println(m);
                try {
                    os.write(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                os.write("\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/


        os.flush();

    }
     public void printCorpus(OutputStream os, int n, boolean ascending) throws IOException {
        if(ascending)
        freqWords= freqWords.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        else
            freqWords= freqWords.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        freqWords.entrySet().stream().limit(n).forEach(e-> {
            System.out.println(String.format("%s : %d\n",e.getKey(),e.getValue()));
            try {
                os.write(String.format("%s : %d\n",e.getKey(),e.getValue()).getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        os.flush();
    }
    public void mostSimilarTexts (OutputStream os) throws IOException {

       Map<String, Double> tmp=new HashMap<>();

       this.texts.forEach((key,value)-> this.texts.forEach((k,v)->{
           if(!key.equals(k))
           tmp.put(key+"!"+k,cosineSimilarity(value,v));}));
       Double val=tmp.values().stream().max(Double::compareTo).orElse((double) 0);
       String s=tmp.keySet().stream().filter(l->tmp.get(l).equals(val)).findFirst().orElse(null);
       if(s==null)
           return;
       List<String> li=new ArrayList<>(texts.keySet());
       int index1=li.indexOf(s.split("!")[0]);
       int index2=li.indexOf(s.split("!")[1]);
       if(index1<index2){
           String s1=String.format("%s\n%s\n%f.5\n",li.get(index1),li.get(index2),val);
           os.write(s1.getBytes());
       }
       else{
           String s1=String.format("%s\n%s\n%f.5\n",li.get(index2),li.get(index1),val);
           os.write(s1.getBytes());
       }



       /*tmp.forEach((k,v)->{
           if(v.equals(val)) {
              String [] p=k.split("!");
              String s=p[0]+"\n"+p[1]+v+"\n";
               try {
                   os.write(s.getBytes());
               } catch (IOException e) {
                   e.printStackTrace();
               }
               System.out.println(s);
               return;
           }
       });*/
       os.flush();


    }
    public static void main(String[] args)throws IOException {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

        System.out.println("===PRINT VECTORS===");
        textProcessor.printTextsVectors(System.out);

        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
        textProcessor.printCorpus(System.out,  20, true);

        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
        textProcessor.printCorpus(System.out, 20, false);

        System.out.println("===MOST SIMILAR TEXTS===");
        textProcessor.mostSimilarTexts(System.out);
    }

}
