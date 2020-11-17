package Exer;

import java.util.Comparator;

public class AvgDiscountCom implements Comparator<Store> {

    @Override
    public int compare(Store o1, Store o2) {
        if(o1.getAvgDisc().equals(o2.getAvgDisc()))
            return o1.getName().compareTo(o2.getName());
        return o1.getAvgDisc().compareTo(o2.getAvgDisc());

    }
}
