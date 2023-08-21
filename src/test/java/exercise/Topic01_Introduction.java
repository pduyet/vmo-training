package exercise;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic01_Introduction {
    @Test
    public void TC1_VerifyTitleDemoqa() {
        WebDriver dr = new ChromeDriver();
        dr.get("https://demoqa.com/");
        Assert.assertEquals(dr.getTitle(), "DEMOQA");
    }
    @Test
    public  void TC2_VerifyTitle24h() {
        WebDriver dr = new FirefoxDriver();
        dr.get("https://www.24h.com.vn/");
        Assert.assertEquals(dr.getTitle(), "Tin tức bóng đá, thể thao, giải trí | Đọc tin tức 24h mới nhất");
    }
    @Test
    public  void TC3_VerifyTitleYTB() {
        WebDriver dr = new EdgeDriver();
        dr.get("https://www.youtube.com/");
        Assert.assertEquals(dr.getTitle(), "YouTube");
    }

}
