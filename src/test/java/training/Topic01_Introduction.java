package training;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Topic01_Introduction {
    @Test
    public void TC01_VerifyTitleFacebook(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
    }
}
