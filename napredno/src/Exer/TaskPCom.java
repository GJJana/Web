package Exer;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskPCom implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {
        if(o1.getPriority().equals(o2.getPriority())) {
            Integer t1 = o1.getTime().getMinute() - LocalDateTime.now().getMinute();
            Integer t2=o2.getTime().getMinute() - LocalDateTime.now().getMinute();
            return t1.compareTo(t2);
        }
        return o1.getPriority().compareTo(o2.getPriority());

    }
}
