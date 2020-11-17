package Exer;

import java.util.Comparator;

public class TotalDiscCom  implements Comparator<Store> {

    @Override
    public int compare(Store o1, Store o2) {
        if(o1.getTotlDisc().equals(o2.getTotlDisc()))
            return o1.getName().compareTo(o2.getName());
        return o2.getTotlDisc().compareTo(o1.getTotlDisc());
    }
}
