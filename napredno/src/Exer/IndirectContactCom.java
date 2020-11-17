package Exer;

import java.util.Comparator;

public class IndirectContactCom implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if(o1.getName().equals(o2.getName()))
            return o1.getId().compareTo(o2.getId());
        return o1.getName().compareTo(o2.getName());
    }
}
