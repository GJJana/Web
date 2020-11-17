package Exer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class np_vezbi {

    static long countFunc(Collection<Collection<String>> collections, String str)
    {
        //br na pojavuvanja na str vo kolekciite
         return collections.stream()
                .mapToLong(x->x.stream()
                .filter(m->m.equals(str))
                        .peek(m-> System.out.println("m:"+m))
                        .count()
                ).sum();
    }
    public static void Reverse(Collection<String> collection)
    {
        Collections.reverse(Arrays.asList(collection));
    }

    static boolean isPrime(int n){
        return n>2&& IntStream.rangeClosed(2,n/2)
                .noneMatch(x->n%x==0);
    }
    static List<Integer> getPrimes(int n){
        //finalna lista
        List<Integer> primes=new ArrayList<>();
        IntStream.rangeClosed(2,n)
                .forEach(primes::add);
        for(int i=0;i<primes.size();i++){
            int currPrime= primes.get(i);
            //iteratorot pocnuva od elementot do nego
            ListIterator<Integer> it=primes.listIterator(i+1);
            //do kraj na listata odi i gi vadi elementite delici so brojot
            while(it.hasNext())
            {
                if(it.next()%currPrime==0)
                    it.remove();
            }

        }
    return primes;
    }

    static Integer winner(ArrayList<Integer> lista)
    {
        int brojac=0;
        while(lista.size()!=1)
        {
            brojac=1;
            ListIterator<Integer> it=lista.listIterator(0);
            while (brojac!=3)
            {
                if(it.hasNext())
                {
                    //System.out.println(lista.toString()+"next");
                    it.next();
                    brojac++;
                }else if(it.hasPrevious())
                {
                    //System.out.println(lista.toString()+"prev");
                    it.previous();
                    brojac++;
                }
            }
            //koga brojac==3
            //System.out.println(brojac);
            it.remove();
        }
        return lista.get(0);

    }

    public static void main(String[] args) {

    }

}
