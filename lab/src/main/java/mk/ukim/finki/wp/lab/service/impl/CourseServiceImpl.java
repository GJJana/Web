package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.WrongTeacherIdExeption;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if (courseId == null)
            throw new IllegalArgumentException();
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (username == null || username.isEmpty() || courseId == null)
            throw new IllegalArgumentException();
        Course c = courseRepository.findAllCourses().stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
        c.addStudent(studentService.listAll().stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null));
        return c;

    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course addStudentToCourse(Student student, Course course) {
       return courseRepository.addStudentToCourse(student,course);
    }

    @Override
    public Course saveCourse(String name, String description, Long teacherId)  {
        return courseRepository.saveCourse(name,description,teacherId);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public Course findCourseById(Long id) {
        return this.courseRepository.findById(id);
    }


}
