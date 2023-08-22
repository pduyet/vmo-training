package tranning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic01_Intro {

    @Test
    public void TC01_VerifyTitelFacebook(){
        WebDriver drive = new ChromeDriver();
        drive.get("https://www.facebook.com/");
        Assert.assertEquals(drive.getTitle(),"Facebook – log in or sign up");
    }

}
