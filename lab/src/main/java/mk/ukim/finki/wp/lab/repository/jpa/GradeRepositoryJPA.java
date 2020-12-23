package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepositoryJPA extends JpaRepository<Grade,Long> {
    List<Grade> findAllByCourse_CourseId(Long CourseId);

}
