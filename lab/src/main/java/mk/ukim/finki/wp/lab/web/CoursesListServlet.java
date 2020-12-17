package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoursesListServlet", urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;
    private CourseService courseService;

    public CoursesListServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", courseService.listAll());
        springTemplateEngine.process("listCourses.html", context, resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tmp = (String) req.getParameter("courseID");
        if (tmp == null || tmp.isEmpty())
            resp.sendRedirect("/listCourses");
        req.getSession().setAttribute("courseID", tmp);
        resp.sendRedirect("/addStudent");
    }
}
