package mk.ukim.finki.wp.lab.repository;


//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {


    public List<Student> getStudents() {
        return DataHolder.students;
    }

    public List<Student> findAllStudents() {
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return DataHolder.students.stream().filter(x -> x.getName().contains(text) || x.getSurname().contains(text)).collect(Collectors.toList());
    }

    public void addNewStudent(Student s) {
        DataHolder.students.add(s);
    }



}
