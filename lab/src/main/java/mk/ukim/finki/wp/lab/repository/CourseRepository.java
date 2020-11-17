package mk.ukim.finki.wp.lab.repository;

//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.WrongTeacherIdExeption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    List<Course> courses;
    private TeacherRepository teacherRepository;

    public CourseRepository(){
        this.courses = new ArrayList<>();
        List<Teacher>teachers=teacherRepository.findAll();
        if(teachers.size()>=5) {
            courses.add(new Course("Veb Programiranje", "SpringBoot Java tehnologija", teachers.get(0)));
            courses.add(new Course("Operativni Sistemi", "OS i konkurentnost na procesi", teachers.get(1)));
            courses.add(new Course("Elektronska i mobilna trgovija", "React i razvivanje na SpringBoott +React aplikacii", teachers.get(2)));
            courses.add(new Course("Kompjuterski mrezi ", "Protokoli za prakjanje i primanje paketi i arhitektura na mrezi", teachers.get(3)));
            courses.add(new Course("Internet Tehnologii", "ASP.NET Web Forms i MVC", teachers.get(4)));
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> findAllCourses() {
        return courses;
    }

    public Course findById(Long courseId) {
        return courses.stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null);
    }
    public Course findByName(String name) {
        return courses.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }


    public List<Student> findAllStudentsByCourse(Long courseId) {
        return courses.stream().filter(x -> x.getCourseId().equals(courseId)).findFirst().orElse(null).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        courses.stream().filter(x -> x.getCourseId().equals(course.getCourseId())).findFirst().orElse(null).addStudent(student);
        return courses.stream().filter(x -> x.getCourseId().equals(course.getCourseId())).findFirst().orElse(null);
    }
    //edit
    //add
    public void saveCourse(String name,String description,Long teacherId) throws WrongTeacherIdExeption {
            Teacher teacher=teacherRepository.findAll().stream().filter(x->x.getId().equals(teacherId)).findFirst().orElse(null);
            if(teacher==null)
                throw new WrongTeacherIdExeption();
            Course course=findByName(name);
            if(course==null)
                this.courses.add(new Course(name,description,teacher));
            else
            {
                course.setDescription(description);
                course.setName(name);
            }

    }


}
