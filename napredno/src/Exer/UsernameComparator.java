package Exer;

import java.util.Comparator;

public class UsernameComparator implements Comparator<ChatRoom> {
    @Override
    public int compare(ChatRoom o1, ChatRoom o2) {
        if(o1.getUsernames().size()>o2.getUsernames().size())
            return 1;
        if(o1.getUsernames().size()<o2.getUsernames().size())
            return -1;
        return 0;
    }
}
