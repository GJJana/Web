package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;


    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error,@RequestParam(required = false) String search, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);

        }
        List<Course> courseList=new ArrayList<>();
        if(search==null||search.isEmpty())
        {
            courseList = this.courseService.listAll();
        }
        if(search!=null)
        {
           courseList=this.courseService.search(search);
        }

        model.addAttribute("courses", courseList);
        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @PostMapping
    public String postCoursesPage(HttpServletRequest request, Model model) {

        if(request.getParameter("search")!=null)
        {
            return "redirect:/courses?error=&search="+request.getParameter("search");
        }

        if (request.getParameter("courseId") == null || request.getParameter("courseId").isEmpty()) {
            return "redirect:/courses?error=PleaseChooseA Course";
        }
        request.getSession().setAttribute("courseID", request.getParameter("courseId"));
        return "redirect:/addStudent";
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("bodyContent", "add-course");
        return "master-template";

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String addCoursePage(@PathVariable Long id, Model model) {
        Course course = this.courseService.findCourseById(id);
        if (course != null) {

            List<Teacher> teachers = this.teacherService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("teachers", teachers);
            model.addAttribute("bodyContent", "add-course");
            return "master-template";


        }
        return "redirect:/courses?error=CourseNotFound";

    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacher,HttpServletRequest request) {
       Long tmp;
        if(request.getParameter("courseId").equals(""))
           tmp=null;
        else
            tmp=Long.parseLong(request.getParameter("courseId"));
        this.courseService.saveCourse(tmp,name, description, teacher);
        return "redirect:/courses";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteById(id);
        return "redirect:/courses";
    }

}
