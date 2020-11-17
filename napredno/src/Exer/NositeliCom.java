package Exer;

import java.util.Comparator;

public class NositeliCom implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getTimestamp().getNano()-o2.getTimestamp().getNano();
    }
}
