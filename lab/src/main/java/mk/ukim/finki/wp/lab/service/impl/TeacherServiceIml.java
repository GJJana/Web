package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceIml implements TeacherService{
    private final TeacherRepository teacherRepository;

    public TeacherServiceIml(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
