package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class DataHolder {
    public static List<Student> students=new ArrayList<>();
    public static List<Course> courses=new ArrayList<>();
    public static List<Teacher> teachers=new ArrayList<>();




    @PostConstruct
    public void init(){
        students.add(new Student("petar.petrov","pp","Petar","Petrov"));
        students.add(new Student("mile.milev","mm","Mile","Milev"));
        students.add(new Student("gjorgji.gjorgjiev","gg","Gjorgji","Gjorgjiev"));
        students.add(new Student("ana.aneva","aa","Ana","Aneva"));
        students.add(new Student("jana.janeva","jj","Jana","Janeva"));

        teachers.add(new Teacher("Biljana","Biljanovska"));
        teachers.add(new Teacher("Marija","Marinkova"));
        teachers.add(new Teacher("Kosta","Kostov"));
        teachers.add(new Teacher("Kalina","Kalinova"));
        teachers.add(new Teacher("Monika","Moneva"));

        courses.add(new Course("Veb Programiranje","SpringBoot Java tehnologija",teachers.get(0)));
        courses.add(new Course("Operativni Sistemi","OS i konkurentnost na procesi",teachers.get(1)));
        courses.add(new Course("Elektronska i mobilna trgovija","React i razvivanje na SpringBoott +React aplikacii",teachers.get(2)));
        courses.add(new Course("Kompjuterski mrezi ","Protokoli za prakjanje i primanje paketi i arhitektura na mrezi",teachers.get(3)));
        courses.add(new Course("Internet Tehnologii","ASP.NET Web Forms i MVC",teachers.get(4)));
        courses= courses.stream().sorted().collect(Collectors.toList());
    }
}
