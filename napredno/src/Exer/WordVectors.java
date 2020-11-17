package Exer;

import java.util.*;
import java.util.stream.Collectors;

public class WordVectors {

    private final List<Integer> NEUTRAL=Arrays.asList(5,5,5,5,5);
    private Map<String,List<Integer>> map=new HashMap<>();
    private List<String> text=new ArrayList<>();
    public WordVectors(String[] words, List<List<Integer>> vectors){

        Iterator <List<Integer>>it=vectors.listIterator();
        for(int i=0;i<words.length;i++){
            if(!it.hasNext())
                break;
            this.map.put(words[i],it.next());
        }

    }
    public void readWords(List<String> words){
        this.text=words;
    }
    public List<Integer> slidingWindow(int n){
        //sliding window so golemina n(sosedni zborovi
        //pocnuva od 0-n-1 i se dvizi
        //za sekoi n elementi se dobiva po eden skalar

        //se sobiraat vektorite na nte elementi i se vadi max

       List<Integer> value=new ArrayList<>();

        for(int i=0;i<this.text.size();i++){
            List<Integer> vec=new ArrayList<>();
            List<List<Integer>> tmp=new ArrayList<>();
            //zima n zborovi od tekstot
            if(n+i>text.size())
                break;
            List<String> t=this.text.subList(i,i+n);
            //zima nivni vektori ako ne go zima default
            t.forEach(x-> {
                tmp.add(map.getOrDefault(x, NEUTRAL));
            });
            //se sobiraat na ist index vredostite vo eden vektor
            Wrapper w=new Wrapper();
            for(;w.j<5;w.j++){
                vec.add(tmp.stream().mapToInt(x->x.get(w.j)).sum());
            }
            System.out.println("vec");
            vec.forEach(System.out::println);
            value.add(vec.stream().max(Integer::compareTo).orElse(0));


        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] words = new String[n];
        List<List<Integer>> vectors = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            words[i] = parts[0];
            List<Integer> vector = Arrays.stream(parts[1].split(":"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            vectors.add(vector);
        }
        n = scanner.nextInt();
        scanner.nextLine();
        List<String> wordsList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            wordsList.add(scanner.nextLine());
        }
        WordVectors wordVectors = new WordVectors(words, vectors);
        wordVectors.readWords(wordsList);
        n = scanner.nextInt();
        List<Integer> result = wordVectors.slidingWindow(n);
        System.out.println(result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")));
        scanner.close();
    }

}
class Wrapper{
    public  int j;

    public Wrapper() {
        this.j=0;
    }
}
