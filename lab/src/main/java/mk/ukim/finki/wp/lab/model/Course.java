package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Course implements Comparable<Course>{
    Long courseId;
    String name;
    String description;
    List<Student> students;
    public Teacher teacher;
     public enum Type  {
        WINTER,
        SUMMER,
        MANDATORY
    }
    Type type;

    public Type getType() {
        return type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void addStudent(Student student){
       this.students.add(student);
   }

    public Long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Student> getStudents() {
        return students;
    }


    public Course(){
       this.students=new ArrayList<>();
    }
    public Course(Long courseId, String name, String description,Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students=new ArrayList<>();
        this.teacher=teacher;
        this.type=Type.MANDATORY;


    }
    public Course( String name, String description,Teacher teacher) {
       this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<>();
        this.teacher=teacher;
        this.type=Type.MANDATORY;

    }
    public Course( String name, String description,Teacher teacher,Type type) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<>();
        this.teacher=teacher;
        this.type=type;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }
}
