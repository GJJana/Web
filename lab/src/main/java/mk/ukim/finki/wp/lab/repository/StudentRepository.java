package mk.ukim.finki.wp.lab.repository;


//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("petar.petrov", "pp", "Petar", "Petrov"));
        students.add(new Student("mile.milev", "mm", "Mile", "Milev"));
        students.add(new Student("gjorgji.gjorgjiev", "gg", "Gjorgji", "Gjorgjiev"));
        students.add(new Student("ana.aneva", "aa", "Ana", "Aneva"));
        students.add(new Student("jana.janeva", "jj", "Jana", "Janeva"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> findAllStudents() {
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return students.stream().filter(x -> x.getName().contains(text) || x.getSurname().contains(text)).collect(Collectors.toList());
    }

    public void addNewStudent(Student s) {
        students.add(s);
    }



}
