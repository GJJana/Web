package webprog.aud1.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="helloworldservlet",urlPatterns = "/hello")

public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        PrintWriter wr=resp.getWriter();
        wr.write("<html><head></head><body><h1>HelloWorld"+username+"</h1></body></html>");
        wr.flush();
       // super.doGet(req, resp);
    }
}
