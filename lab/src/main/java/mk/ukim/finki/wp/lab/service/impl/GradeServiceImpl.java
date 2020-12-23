package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJPA;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    private final CourseRepositoryJPA courseRepository;
    private final StudentRepositoryJPA studentRepository;
    private final GradeRepositoryJPA gradeRepository;

    public GradeServiceImpl(CourseRepositoryJPA courseRepository, StudentRepositoryJPA studentRepository, GradeRepositoryJPA gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getGradesForCourse(Long courseId) {
        if(courseId==null)
            throw new IllegalArgumentException();
      return gradeRepository.findAllByCourse_CourseId(courseId);
    }
}
