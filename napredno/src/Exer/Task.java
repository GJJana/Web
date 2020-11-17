package Exer;

import java.time.LocalDateTime;

public class Task {

    //[категорија],[име_на_задача],[oпис],[рок_за_задачата],[приоритет]
    private String category;
    private String name;
    private String desc;
    private LocalDateTime time;
    private Integer priority;

    public Task(String category, String name, String desc) {
        this.category = category;
        this.name = name;
        this.desc = desc;
        this.priority=0;
        this.time=LocalDateTime.now();
    }

    public Task(String category, String name, String desc, LocalDateTime time, Integer priority) throws DeadlineNotValidException {
        if(time.isBefore(LocalDateTime.now()))
            throw new DeadlineNotValidException("Nevaliden rok");
        this.category = category;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", time=" + time +
                ", priority=" + priority +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Integer getPriority() {
        return priority;
    }
}
