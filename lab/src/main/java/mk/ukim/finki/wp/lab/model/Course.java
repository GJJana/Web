package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Type getType() {
        return type;
    }

    public Teacher getTeacher() {
        return teacher;
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

    }

    public Course( String name, String description,Teacher teacher) {
       //this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students=new ArrayList<>();
        this.teacher=teacher;
        this.type=Type.MANDATORY;

    }
    public Course( String name, String description,Teacher teacher,Type type) {
        //this.courseId = (long) (Math.random()*1000);
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
