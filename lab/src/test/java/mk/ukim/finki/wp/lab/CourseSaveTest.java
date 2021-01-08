package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.impl.CourseRepository;
import mk.ukim.finki.wp.lab.repository.impl.StudentRepository;
import mk.ukim.finki.wp.lab.repository.impl.TeacherRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJPA;
import mk.ukim.finki.wp.lab.service.impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class CourseSaveTest {
    @Mock
    private CourseRepositoryJPA courseRepository;
    @Mock
    private TeacherRepositoryJPA teacherRepository;
    @Mock
    private StudentRepositoryJPA studentRepository;

    private CourseServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Teacher teacher=new Teacher("name","surname");
        Course course=new Course("Verojatnost i statistika","Matematicki predmet",teacher);
        Student student=new Student("username","password","name","surname");
        Mockito.when(this.courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        Mockito.when(this.teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);;
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);;
        this.service=Mockito.spy(new CourseServiceImpl(this.courseRepository,this.studentRepository,this.teacherRepository));

    }
    @Test
    public void testSuccessSaveCourseIdNULL(){
       Course course=this.service.saveCourse(null,"Verojatnost i statistika","Matematicki predmet", (long) 1);
       Mockito.verify(this.service).saveCourse(null,"Verojatnost i statistika","Matematicki predmet", (long) 1);
        Assert.assertNotNull("Course is null",course);
        Assert.assertEquals("name do not mach","Verojatnost i statistika",course.getName());
        Assert.assertEquals("desc do not mach","Matematicki predmet",course.getDescription());

    }
    @Test
    public void testNullTeacherId()
    {
        Assert.assertThrows("IllegalArgumentException expected",IllegalArgumentException.class,()->this.service.saveCourse(null,"Verojatnost i statistika","Matematicki predmet",null));
        Mockito.verify(this.service).saveCourse(null,"Verojatnost i statistika","Matematicki predmet", null);
    }
    @Test
    public void testSuccessSaveCourseId(){
        Course course=this.service.saveCourse((long)6,"Verojatnost i statistika","Matematicki predmet", (long) 1);
        Mockito.verify(this.service).saveCourse((long)6,"Verojatnost i statistika","Matematicki predmet", (long) 1);
        Assert.assertNotNull("Course is null",course);
        Assert.assertEquals("name do not mach","Verojatnost i statistika",course.getName());
        Assert.assertEquals("desc do not mach","Matematicki predmet",course.getDescription());

    }



}
