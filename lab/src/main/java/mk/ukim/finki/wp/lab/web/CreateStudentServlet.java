package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateStudentServlet", urlPatterns = "/createStudent")

public class CreateStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("students",studentService.listAll());
        //req.getAttribute("students");
        springTemplateEngine.process("addStudent.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            String username = (String) req.getParameter("username");
            String password = (String) req.getParameter("password");
            String name = (String) req.getParameter("name");
            String surname = (String) req.getParameter("surname");
            try {
                studentService.save(username, password, name, surname);
            } catch (IllegalArgumentException ex) {
               // WebContext context = new WebContext(req, resp, req.getServletContext());
                //context.setVariable("hasError", true);
                //context.setVariable("error", ex.getMessage());
                //springTemplateEngine.process("addStudent.html", context, resp.getWriter());
                resp.sendRedirect("/createStudent");

            }
            req.getSession().setAttribute("NewStudent", new Student(username, password, name, surname));

           // if(req.getParameter("usernameSearch")!=null)
           //{
              // WebContext context = new WebContext(req, resp, req.getServletContext());

                //req.setAttribute("students",studentService.searchByNameOrSurname((String)req.getParameter("usernameSearch")));
               //context.setVariable("students",studentService.searchByNameOrSurname((String)req.getParameter("usernameSearch")));

               
               //resp.sendRedirect("/createStudent");
              // springTemplateEngine.process("addStudent.html", context, resp.getWriter());

           // }
           // else{
              //  req.setAttribute("students",studentService.listAll());
                resp.sendRedirect("/createStudent");
           // }




    }
}
