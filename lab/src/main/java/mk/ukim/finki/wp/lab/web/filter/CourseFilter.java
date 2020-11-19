/*package mk.ukim.finki.wp.lab.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter
public class CourseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse) response;
        String courseID=(String) req.getSession().getAttribute("courseID");
        String path=req.getServletPath();
        //if(courseID==null && !path.equals("/courses")) {
          //  res.sendRedirect("/courses");
        //}else {
       //     chain.doFilter(request,response);
        //}
    }

    @Override
    public void destroy() {

    }
}

*/