package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    List<Teacher> teachers;
    public TeacherRepository(){
        this.teachers=new ArrayList<>();

        teachers.add(new Teacher("Biljana","Biljanovska"));
        teachers.add(new Teacher("Marija","Marinkova"));
        teachers.add(new Teacher("Kosta","Kostov"));
        teachers.add(new Teacher("Kalina","Kalinova"));
        teachers.add(new Teacher("Monika","Moneva"));

    }

    public List<Teacher> findAll() {
        return teachers;
    }
    public Optional<Teacher> findTeacherbyId(Long id){
        return this.teachers.stream()
                .filter(x->x.getId().equals(id))
                .findFirst();
    }
}
