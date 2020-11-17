package Exer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Benford {

  //pocetnata cifra vo sekoj br e razlicna i opagja so zgolemuvanjero na cifrite(1-9)
    //1-30;9-5


    public static List<Integer> percentage(List<String> numbers){
        List<Integer> p=new ArrayList<>();
        for(int i=1;i<11;i++){
            // za sekoj broj se gleda kolku e prisuten vo listata
            int finalI = i;
            Long k=numbers.stream().filter(x->Integer.parseInt(x)== finalI).count();

            p.add(k.intValue()*100/numbers.size());

        }
        return p;
    }

    public static void main(String[] args) throws Exception {

        File file=new File("C:\\Users\\janag\\IdeaProjects\\napredno\\src\\Exer\\Mesures.txt");
        BufferedReader br=new BufferedReader(new FileReader(file));

        List<String> list=new ArrayList<>();
        String tmp;
        while(( tmp=br.readLine())!=null){
            list.add(tmp.substring(0,1));
        }


        List<Integer> per=percentage(list);
        for(Integer i:per)
        {
            System.out.println(i);
        }

        System.out.println(per.stream().mapToInt(Integer::intValue).sum());
        String s="jana";
        String [] str=s.split("");
        System.out.println(str.length);


    }


}
