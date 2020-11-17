package Kripto;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//frekfencija na sekoja bukva
public class LetterFreq {


    public static void main(String[] args) throws IOException {


        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> mapa = new HashMap<>();
        String input=br.readLine();
        int count=input.length();

        for(int i=0;i<input.length();i++)
        {
            String str=String.valueOf(input.charAt(i));
            if(mapa.containsKey(str))
            {
                mapa.put(str,mapa.get(str)+1);

            }
            else mapa.put(str,1);
        }
       mapa= mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(r,m)->r, LinkedHashMap::new));
        mapa.forEach((K,V)->{
            System.out.println(K+":"+(float)V/count);
        });


        //sekoi X bukvi frekfencija
        HashMap<String,Integer> cetri=new HashMap<>();
        int count1=input.length()-3;
        for(int i=0;i<input.length();i++)
        {   StringBuilder s=new StringBuilder();
            //int j=i+4;
            //int j=i+1;
            int j=i+2;
            if(j>=input.length())
                break;

            s.append(input.charAt(i)).append(input.charAt(j-1)).append(input.charAt(j));
            //FINKI- hint
            //if (input.charAt(i+1)!= input.charAt(j))
              // continue;
            if(cetri.containsKey(s.toString()))
                cetri.put(s.toString(),cetri.get(s.toString())+1);
                else cetri.put(s.toString(),1);
        }
        cetri= cetri.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(r,m)->r, LinkedHashMap::new));

        cetri.forEach((K, V)->{
            System.out.println(K+":"+(float)V/ count1);
        });



        String mak="А Б В Г Д Ѓ Е Ж З Ѕ И Ј К Л Љ М Н Њ О П Р С Т Ќ У Ф Х Ц Ч Џ Ш";
        String mar="Ж Ј З К ? И ? У М Н Л О Њ Т ? Ц Ф Х ? Ш Б В Ч Д Г С ? П Р Е А";
        List<String> keys= Stream.of(mak.split(" ")).collect(Collectors.toList());
        List<String> val=Stream.of(mar.split(" ")).collect(Collectors.toList());
        Map<String,String> map=new HashMap<>();

        for(int i=0;i<keys.size();i++)
        {
            map.put(keys.get(i),val.get(i));
        }
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<input.length();i++)
        {
           if(map.containsKey(String.valueOf(input.charAt(i))))
               sb.append(map.get(String.valueOf(input.charAt(i))));

        }

        System.out.println(sb.toString());
    }

}
