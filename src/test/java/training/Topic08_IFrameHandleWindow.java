package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Topic08_IFrameHandleWindow {
    WebDriver driver;

    @Test
    public void verifyNumberOfFollower() {
        driver = new ChromeDriver();
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,950)", "");
        WebElement iframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
        driver.switchTo().frame(iframe);

        String follower = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
        System.out.println(follower);
        Assert.assertTrue(follower.contains("166K"));

        driver.switchTo().defaultContent();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='hotline']//ul//li//second")).getText(), "1900.6335.07");
        driver.quit();
    }

    @Test
    public void verifyWindow() {
        driver = new ChromeDriver();
        driver.get("https://skills.kynaenglish.vn/");
        String parentId = driver.getWindowHandle();

        WebElement faceBookLink = driver.findElement(By.xpath("(//div[@class='social']//a)[1]"));
        faceBookLink.click();

        WebElement youTubeLink = driver.findElement(By.xpath("(//div[@class='social']//a)[2]"));
        youTubeLink.click();

        WebElement govLink = driver.findElement(By.xpath("//a[@aria-label='k-footer-copyright'][1]"));
        govLink.click();

        switchWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        switchWindowByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

        switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
        Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/61473");

        switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        Assert.assertEquals(driver.getCurrentUrl(), "https://skills.kynaenglish.vn/");

        closeAllTabWithoutParent(parentId);

        driver.quit();
    }

    public void switchWindowById(String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentId)) {
                driver.switchTo().window(window);
                break;
            }
        }

    }

    public boolean closeAllTabWithoutParent(String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentId)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public void switchWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)) {
                break;
            }

        }

    }
}
