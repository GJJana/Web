package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.impl.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJPA;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryJPA studentRepository;


    public StudentServiceImpl(StudentRepositoryJPA studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException();
        return studentRepository.findAllByNameOrSurname(text,text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username == null || username.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty() || password == null || password.isEmpty())
            throw new IllegalArgumentException();
        Student s = new Student(username, password, name, surname);
        studentRepository.save(s);
        return s;
    }



}
