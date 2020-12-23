package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    public Grade() {
    }

    public Character getGrade() {
        return grade;
    }

    public Grade(Long id, Character grade, Student student, Course course) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public Grade(Character grade, Student student, Course course) {
        //this.id = (long) (Math.random()*1000);
        this.grade = grade;
        this.student = student;
        this.course = course;
    }
}
