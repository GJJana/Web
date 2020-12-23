package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryJPA extends JpaRepository<Course,Long> {
List<Course> findByNameContainingOrDescriptionContainingOrTeacher_NameOrTeacher_Surname(String text,String text1,String text2,String text3);



}
