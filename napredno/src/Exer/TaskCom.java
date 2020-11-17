package Exer;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskCom implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        Integer t1 = o1.getTime().getMinute() - LocalDateTime.now().getMinute();
        Integer t2=o2.getTime().getMinute() - LocalDateTime.now().getMinute();
        return t1.compareTo(t2);
    }
}
