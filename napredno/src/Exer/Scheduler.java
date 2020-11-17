package Exer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler <T>{
    private List<Timestamp<T>> timestamps;

    public Scheduler() {
        this.timestamps=new ArrayList<>();
    }
    public void add(Timestamp<T> t){
        this.timestamps.add(t);
        this.timestamps=this.timestamps.stream().sorted().collect(Collectors.toList());
    }
    public boolean remove(Timestamp<T> t){
        return this.timestamps.remove(t);
    }
    public Timestamp<T> next(){
      //najblisko do tekovnoto i ne e pominato
        int index=0;
        LocalDateTime now=LocalDateTime.now();
        for(Timestamp<T> t : timestamps){
            if(t.getTime().isAfter(now))
            {
                index=this.timestamps.indexOf(t)-1;
                break;
            }
        }
        return this.timestamps.get(index);
    }
    public Timestamp<T> last(){
     //najblisko do tekovnoto pominato
        int index=0;
        LocalDateTime now=LocalDateTime.now();
        for(Timestamp<T> t : timestamps){
            if(t.getTime().isAfter(now))
            {
                index=this.timestamps.indexOf(t);
                break;
            }
        }
        return this.timestamps.get(index);
    }
    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end){
        //lista nastani megu begin i end ne vklucuvajkji gi
        List<Timestamp<T>> tmp=new ArrayList<>();
        for (Timestamp<T> t :timestamps){
            if(t.getTime().isAfter(begin)&&t.getTime().isBefore(end))
                tmp.add(t);
        }
        return tmp;
    }
}
