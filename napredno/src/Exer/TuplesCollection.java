package Exer;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class TuplesCollection<T extends Comparable<T>>  {

    private Set<Tuple2<T>> set=new TreeSet<>();



    public void addTuple(Tuple2<T> tuple2){
        //if(set.stream().noneMatch(x -> x.getFirst().equals(tuple2.getFirst()) && x.getSecond().equals(tuple2.getSecond())))
                this.set.add(tuple2);
    }
    public Collection<Tuple2<T>> getFilteredTuples(Predicate<Tuple2<T>> predicate){
        return set.stream().filter(predicate).collect(Collectors.toCollection(TreeSet::new));
    }
    public Map<T, List<Tuple2<T>>> groupBy(boolean first){
        //ako first e true klucevi se prvite elementi ako ne vtorite
        Map<T,List<Tuple2<T>>> map=new TreeMap<>();
        if(first)
            this.set.forEach(x->{
                map.putIfAbsent((T) x.getFirst(),new ArrayList<>());
               if(map.containsKey(x.getFirst()))
                   map.get(x.getFirst()).add(x);
                /*if(map.containsKey(x.getSecond()))
                    map.get(x.getSecond()).add(x);*/
            });
        else{
            this.set.forEach(x->{
                map.putIfAbsent((T) x.getSecond(),new ArrayList<>());
                /*if(map.containsKey(x.getFirst()))
                    map.get(x.getFirst()).add(x);*/
                if(map.containsKey(x.getSecond()))
                    map.get(x.getSecond()).add(x);
            });

        }
        map.forEach((k,v)->Collections.reverse(v));
        return map;
        //return map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

    }
   public Map<T,Integer> reduceBy(boolean first){
       Map<T,Integer> map=new TreeMap<>(Collections.reverseOrder());
       if(first)
           this.set.forEach(x->{
               map.putIfAbsent((T) x.getFirst(),
                       (int)( set.stream().filter(t->t.getFirst().equals(x.getFirst()))).count());
           });
       else
           this.set.forEach(x->{
               map.putIfAbsent((T) x.getSecond(),
                       (int)( set.stream().filter(t->t.getSecond().equals(x.getSecond())).count()));
           });
       //return map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
       return map;
   }
   public Collection<Tuple2<T>> uniqueTuples (boolean first){
        Map<T,List<Tuple2<T>>> tmp=this.groupBy(first);
        List<Tuple2<T>> t=new ArrayList<>();
        tmp.forEach((k,v)-> t.addAll(v));
        return t.stream().distinct().collect(Collectors.toList());
   }
    public int compare (TuplesCollection otherCollection){
        if(this.set.size()>otherCollection.set
        .size())
            return 1;
        if(this.set.size()<otherCollection.set
                .size())
            return -1;
        return 0;
    }



}
