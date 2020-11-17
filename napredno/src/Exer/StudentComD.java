package Exer;

import java.util.Comparator;

public class StudentComD implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        double sum1=o1.sumPoints();
        double sum2=o2.sumPoints();
        if(sum1-sum2>0)
            return 1;
        if(sum1-sum2<0)
            return -1;
        //opagacki
        return o1.getIndex().compareTo(o2.getIndex());

    }
}
