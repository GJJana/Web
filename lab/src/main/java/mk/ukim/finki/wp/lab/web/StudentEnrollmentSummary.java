package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentEnrollmentSummary", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final GradeService gradeService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //dodadi go na kursot studentot
        Long courseId = Long.parseLong((String) req.getSession().getAttribute("courseID"));
        String username = (String) req.getSession().getAttribute("selectedStudent");
        courseService.addStudentInCourse(username, courseId);
        Course c = courseService.findCourseById(courseId);
        List<Grade> grades=gradeService.getGradesForCourse(courseId);
        context.setVariable("course", c);
        context.setVariable("grades",grades );
        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/courses");
    }
}
