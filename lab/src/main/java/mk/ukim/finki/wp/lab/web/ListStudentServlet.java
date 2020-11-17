package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListStudentServlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("students", studentService.listAll());
        //context.setVariable("courseID",req.getSession().getAttribute("courseID"));

        springTemplateEngine.process("listStudents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getParameter("size");

        if (username == null || username.isEmpty()) {
            resp.sendRedirect("/addStudent");
        }
        if ((String) req.getSession().getAttribute("courseID") == null || ((String) req.getSession().getAttribute("courseID")).isEmpty()) {
            resp.sendRedirect("/listCourses");
        }

        req.getSession().setAttribute("selectedStudent", username);
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
