package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.StudentAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.impl.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJPA;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryJPA courseRepository;
    private final StudentRepositoryJPA studentRepository;
    private final TeacherRepositoryJPA teacherRepository;

    public CourseServiceImpl(CourseRepositoryJPA courseRepository, StudentRepositoryJPA studentService,TeacherRepositoryJPA teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentService;
        this.teacherRepository=teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if (courseId == null)
            throw new IllegalArgumentException();
       Course tmp= courseRepository.findById(courseId).orElse(null);
       if(tmp==null)
           throw new IllegalArgumentException();

        return tmp.getStudents();
    }



    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (username == null || username.isEmpty() || courseId == null)
            throw new IllegalArgumentException();

        Course c = courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        Student s=studentRepository.findById(username).orElseThrow(IllegalArgumentException::new);
        if(c.getStudents().stream().anyMatch(x -> x.getUsername().equals(username)))
            throw new StudentAlreadyExistsException(username);
        c.getStudents().add(s);
        return this.courseRepository.save(c);


    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addStudentToCourse(Student student, Course course) {
        return addStudentInCourse(student.getUsername(),course.getCourseId());
    }

    @Override
    @Transactional
    public Course saveCourse(Long courseId,String name, String description, Long teacherId)  {
        if (teacherId==null)
            throw new IllegalArgumentException();
        Teacher t= teacherRepository.getOne(teacherId);
        if(courseId!=null)
            courseRepository.deleteById(courseId);
        return courseRepository.save(new Course(name,description,t));


    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);}

    @Override
    public List<Course> search(String text) {
        return courseRepository.findByNameContainingOrDescriptionContainingOrTeacher_NameOrTeacher_Surname(text,text,text,text);
    }
}
