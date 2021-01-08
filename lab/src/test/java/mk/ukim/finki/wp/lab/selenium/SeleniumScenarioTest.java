package mk.ukim.finki.wp.lab.selenium;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    private HtmlUnitDriver driver;
    @BeforeEach
    private void setup(){
        this.driver=new HtmlUnitDriver(true);
    }
    @AfterEach
    private void destroy(){

    }
    @Test
    public void testScenario(){
        CoursesPage coursesPage=CoursesPage.to(this.driver);
        coursesPage.assertElements(0,0,0,0);
        LoginPage loginPage=LoginPage.openLogin(this.driver);
        LoginPage.doLogin(this.driver,loginPage,"admin","pass");
        coursesPage.assertElements(coursesPage.getCoursesRows().size(),coursesPage.getCoursesRows().size(),coursesPage.getCoursesRows().size(),1);


    }

}
