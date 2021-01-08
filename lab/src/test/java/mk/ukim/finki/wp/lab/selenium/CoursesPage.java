package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Getter
public class CoursesPage  extends AbstractPage{
    @FindBy(css = "tr[class=course]")
    private List<WebElement> coursesRows;


    @FindBy(css = ".delete-course")
    private List<WebElement> deleteButtons;


    @FindBy(css= ".edit-course")
    private List<WebElement> editButtons;


    @FindBy(css = ".submit-course")
    private List<WebElement> submitButtons;


    @FindBy(css = ".add-course")
    private List<WebElement> addProductButton;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }
    public static CoursesPage to(WebDriver driver)
    {
        get(driver,"/courses");
       return PageFactory.initElements(driver,CoursesPage.class);
    }
    public void assertElements( int deleteButtons, int editButtons,int submitButtons, int addButtons)
    {
       // Assert.assertEquals("rows do not mach",coursesNumber,this.getCoursesRows().size());
        Assert.assertEquals("delete do not mach",deleteButtons,this.getDeleteButtons().size());
        Assert.assertEquals("edit do not mach",editButtons,this.getEditButtons().size());
        Assert.assertEquals("submit do not mach",submitButtons,this.getSubmitButtons().size());
        Assert.assertEquals("add do not mach",addButtons,this.getAddProductButton().size());


    }
}
