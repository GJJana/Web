package Exer;

import java.util.Comparator;

public class StudentComByIndex implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getIndex().compareTo(o2.getIndex());
    }
}
