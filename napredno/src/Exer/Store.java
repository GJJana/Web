package Exer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Store {

    private String name;
    Map<String,Integer> CeniProc=new HashMap<>();
    private  Double totlDisc;
    private Double avgDisc;

    public String getName() {
        return name;
    }

    public Double getTotlDisc() {
        return totlDisc;
    }

    public Double getAvgDisc() {
        return avgDisc;
    }

    public Store(String name) {
        this.name = name;
        this.avgDisc=0.0;
        this.totlDisc=0.0;
    }
    public Store(String name, List<String> list){
        this.name=name;


        list.forEach(this::addCena);
    }

    public void addCena(String cena){

        this.CeniProc.put(cena,discountCal(cena));
        totalDiscount();
        averageDiscount();


    }

    public void averageDiscount(){
    ////100*cena na popust/cena->procent
    this.avgDisc= CeniProc.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public Integer discountCal(String s){
        return (int)(Double.parseDouble(s.split(":")[0])/Double.parseDouble(s.split(":")[1])*100);
    }
    public void totalDiscount(){
       this.totlDisc=this.CeniProc.keySet().stream().mapToDouble(x-> Integer.parseInt(x.split(":")[1])/Integer.parseInt(x.split(":")[0])).sum();
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("%s\nAverage discount: %.1f %% \nTotal discount: %.0f\n",name,avgDisc,totlDisc));
        CeniProc.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(l->
                sb.append(String.format("%d%% %s",l.getValue(),l.getKey().replace(":","/"))));
    return sb.toString();
    }
}
