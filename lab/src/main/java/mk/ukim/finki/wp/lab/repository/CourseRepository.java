package mk.ukim.finki.wp.lab.repository;

//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.WrongTeacherIdExeption;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {


    public List<Course> getCourses() {
        return DataHolder.courses;
    }

    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }

    public Course findById(Long courseId) {
        return DataHolder.courses.stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
    }
    public Course findByName(String name) {
        return DataHolder.courses.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }


    public List<Student> findAllStudentsByCourse(Long courseId) {
        return DataHolder.courses.stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        DataHolder.courses.stream().filter(x -> x.getCourseId().equals(course.getCourseId())).findFirst().orElse(null).addStudent(student);
        return DataHolder.courses.stream().filter(x -> x.getCourseId().equals(course.getCourseId())).findFirst().orElse(null);
    }
    //edit
    //add
    public Course saveCourse(String name,String description,Long teacherId) {
            Teacher teacher=DataHolder.teachers.stream().filter(x->x.getId().equals(teacherId)).findFirst().orElse(null);

            DataHolder.courses.removeIf(x->x.getName().equals(name));
            Course course=new Course(name,description,teacher);
            DataHolder.courses.add(course);
            return course;

    }
    public void deleteById(Long id)
    {
        DataHolder.courses.removeIf(x->x.getCourseId().equals(id));
    }


}
