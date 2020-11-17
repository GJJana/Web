/*package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Student> students=new ArrayList<>();
    public static List<Course> courses=new ArrayList<>();



    @PostConstruct
    public void init(){
        students.add(new Student("petar.petrov","pp","Petar","Petrov"));
        students.add(new Student("mile.milev","mm","Mile","Milev"));
        students.add(new Student("gjorgji.gjorgjiev","gg","Gjorgji","Gjorgjiev"));
        students.add(new Student("ana.aneva","aa","Ana","Aneva"));
        students.add(new Student("jana.janeva","jj","Jana","Janeva"));

        courses.add(new Course(Long.parseLong("1"),"Veb Programiranje","SpringBoot Java tehnologija"));
        courses.add(new Course(Long.parseLong("2"),"Operativni Sistemi","OS i konkurentnost na procesi"));
        courses.add(new Course(Long.parseLong("3"),"Elektronska i mobilna trgovija","React i razvivanje na SpringBoott +React aplikacii"));
        courses.add(new Course(Long.parseLong("4"),"Kompjuterski mrezi ","Protokoli za prakjanje i primanje paketi i arhitektura na mrezi"));
        courses.add(new Course(Long.parseLong("5"),"Internet Tehnologii","ASP.NET Web Forms i MVC"));

    }
}
*/