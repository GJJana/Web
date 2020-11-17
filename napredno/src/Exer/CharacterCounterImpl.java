package Exer;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterCounterImpl  {
    Map<Character,Integer> map;

    Map<Character,Integer> readCharacters(InputStream inputStram) throws IOException
    {
        //broi koj karakter kolku pati se pojavuva
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStram));
        String text=br.readLine();
        char[] t=text.toCharArray();
        for(int i=0;i<t.length;i++)
        {
            map.merge(t[i],1,Integer::sum);
        }
        return map;
    }

    void printByCharacter(OutputStream outputStream){
        //gi pecati karakterite i brojot na pojavuvanje , char leksigografski [char]:[count]
        map.keySet().stream().sorted().forEach(x->System.out.printf("%c %d",x,map.get(x)));


    }
    void printByFrequency(OutputStream outputStream){
        //gi pecati karakterite spored brojot na pojavuvanja [count]:[char]
        map.values().stream().sorted().forEach(x->System.out.printf("%c %d",getkey(x),x));
    }
    Character getkey(Integer value){
        map.values().stream().sorted();
        return map.keySet().stream().filter(x->map.get(x).equals(value)).findFirst().get();
    }
}
