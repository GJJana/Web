package Exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerArray  extends ResizableArray<Integer>{

    public IntegerArray() {
        super();
    }
    public IntegerArray(Integer[] i){
        super(i);
    }

    public double sum(){
        double s=0;
        for(int i=0;i<this.getCapacity();i++){
            s+=this.elementAt(i);
        }
        return s;
    }
    public double mean(){
        return this.sum()/(this.getCapacity()+1);
    }
    public int countNonZero(){
        int count=0;
        for(int i=0;i<this.getCapacity();i++){
            if(this.elementAt(i)==0)
                count++;
        }
        return count;
    }
    public IntegerArray distinct(){
        List<Integer> tmp= Arrays.asList(this.getElements());
        tmp=tmp.stream().distinct().collect(Collectors.toList());
        Integer[] n=new Integer[tmp.size()];
        n=tmp.toArray(n);
        return new IntegerArray(n);

    }
    public IntegerArray increment(int offset){
        Integer[]tmp=this.getElements();
        Stream.of(tmp).forEach(x->x+=offset);
        return new IntegerArray(tmp);
    }



}
