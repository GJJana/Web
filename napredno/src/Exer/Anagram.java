package Exer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram{
   Map<String, Set<String>> map;
   Set<String> set;

   //5 ili povekje anagrami
    //anagram-zbor sosttaven od istite bukvi

   public Anagram(){
       map=new HashMap<>();
       set=new HashSet<>();
   }

   void najdiAnagrami(List<String> lista)
   {
       for(String s:lista)
       {

           set=lista.stream().filter(x->anagram(x,s)).collect(Collectors.toSet());
           if(!set.isEmpty()&& !map.containsValue(set))
           {

               map.put(s,set);
           }

       }
   }

  boolean anagram(String str,String str1)
  {
      char[] s=str.toCharArray();
      char [] m=str1.toCharArray();
      Arrays.sort(s);
      Arrays.sort(m);
      return Arrays.equals(s,m);
  }


   public static void main(String[] args) {
        Anagram a=new Anagram();
        //se polni hasmapata
       List<String> input=new ArrayList<String>();
       input.add("Jana");
       input.add("anaJ");
       input.add("kkk");
       input.add("mm");
       a.najdiAnagrami(input);
       a.map.keySet().stream().sorted().forEach(x->a.map.get(x).stream().sorted().forEach(y->System.out.printf("%s ",y)));

   }




}