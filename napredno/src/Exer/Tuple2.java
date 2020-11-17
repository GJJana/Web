package Exer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface Tuple2 <T extends Comparable<T> > extends Comparable<Tuple2<T>> {


    public T getFirst();
    public T getSecond();


    @Override
   public int compareTo(Tuple2<T> o);
}
