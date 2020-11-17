package Exer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {
    private String index;
    private List<Integer> points;

    public Student(String index) {
        this.index = index;
        this.points=new ArrayList<>();
    }

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

   public double sumPoints(){
       System.out.println(points.stream().mapToInt(Integer::intValue).sum()/10);
        return points.stream().mapToInt(Integer::intValue).sum()/10;
   }

    public String getIndex() {
        return index;
    }

    public List<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String s=new String();
        if(this.getPoints().size()<8)
            s="NO";
        else s="YES";

        return this.getIndex()+" "+s+" "+String.format("%.2f",this.sumPoints());
    }
}
