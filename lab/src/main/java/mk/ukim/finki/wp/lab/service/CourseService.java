package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.WrongTeacherIdExeption;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course addStudentToCourse(Student student, Course course);
    Course saveCourse(Long courseId,String name,String description,Long teacherId) ;
    void deleteById(Long id);
    Course findCourseById(Long id);
    List<Course> search(String text);

}
