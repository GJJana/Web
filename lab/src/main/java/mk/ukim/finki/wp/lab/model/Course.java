package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Course {
    Long courseId;
    String name;
    String description;
    List<Student> students;
    public Teacher teacher;

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

    }
    public Course( String name, String description,Teacher teacher) {
       this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<>();
        this.teacher=teacher;

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
}
