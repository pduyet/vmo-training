package Pratices;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic01_Introduction {
    @Test
    public void TC01_VerifyTitlePageDemoQa(){
    WebDriver driver = new ChromeDriver();
    driver.get("https://demoqa.com/");
    Assert.assertEquals(driver.getTitle(), "DEMOQA");
}
    @Test
    public void TC02_VerifyTitlePage24h(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.24h.com.vn/");
        Assert.assertEquals(driver.getTitle(), "Tin tức bóng đá, thể thao, giải trí | Đọc tin tức 24h mới nhất");
    }
    @Test
    public void TC03_VerifyTitlePageYouTube(){
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getTitle(), "YouTube");
    }

}
