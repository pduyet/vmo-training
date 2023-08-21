import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class test {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

    }

    @Test
    public void b2() {
        String url = "https://the-internet.herokuapp.com/nested_frames";
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        Assert.assertEquals(driver.getCurrentUrl(), url);
        List<WebElement> parentFrame = driver.findElements(By.xpath("//frame"));
        Assert.assertEquals(parentFrame.size(), 2);

        for (int i = 0; i < parentFrame.size(); i++) {

            if (i == 0) {
                driver.switchTo().frame(parentFrame.get(i));
                Assert.assertTrue(driver.findElement(By.xpath("//frameset[@name='frameset-middle']")).isDisplayed());
                List<WebElement> childFrameTop = driver.findElements(By.xpath("//frame"));
                Assert.assertEquals(childFrameTop.size(), 3);
                for (int j = 0; j < childFrameTop.size(); j++) {
                    List<WebElement> refreshedFrames = driver.findElements(By.xpath("//frame"));
                    driver.switchTo().frame(refreshedFrames.get(j));
                    String expectedText = "";
                    if (j == 0) {
                        expectedText = "LEFT";
                    } else if (j == 1) {
                        expectedText = "MIDDLE";
                    } else {
                        expectedText = "RIGHT";
                    }
                    Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), expectedText);
                    driver.switchTo().parentFrame();
                }
                driver.switchTo().defaultContent();
            } else {
                driver.switchTo().frame(parentFrame.get(i));
                String expectedText = "BOTTOM";
                Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), expectedText);
                driver.switchTo().parentFrame();
            }
        }
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
