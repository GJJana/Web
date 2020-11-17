package Exer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cluster<T extends Point2D> {
    private Map<Long,T> list=new HashMap<>();

    void addItem(T element){
        this.list.put(element.getId(),element);
    }
    void near(long id, int top){
        Map<T,Double> tmp=new  HashMap<>();
        T el=list.get(id);
        list.values().stream().filter(p->p.getId()!=id).forEach(p1->tmp.putIfAbsent(p1,el.distance(p1)));
        Iterator i=IntStream.range(1,top+1).iterator();
        tmp.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(top).forEach(l-> System.out.println(String.format("%d. %d -> %.3f",i.next(),(int)l.getKey().getId(),l.getValue())));


    }

}
