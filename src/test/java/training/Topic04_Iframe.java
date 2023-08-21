package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class Topic04_Iframe {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)", "");
    }
    @Test
    public void TC01_Iframe() {
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
        String text = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
        Assert.assertTrue(text.contains("166"));
        driver.switchTo().parentFrame();
        Assert.assertEquals(driver.findElement(By.xpath("//first[normalize-space()='Hotline']/following-sibling::second")).getText(),"1900.6335.07");
    }
    @Test
    public void TC02_WindowTab(){
        driver.findElement(By.xpath("//div[@class='hotline']/div[@class='social']/a/img[@alt='facebook']")).click();
        driver.findElement(By.xpath("//div[@class='hotline']/div[@class='social']/a/img[@alt='youtube']")).click();
//        driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[1]//img[1]")).click();
        String parentID = driver.getWindowHandle();
        switchWindowByID(parentID);
        String titleFb = "Kyna.vn | Ho Chi Minh City | Facebook";
        switchWindowByTitle(titleFb);
        String titleYt = "Kyna.vn - YouTube";
        switchWindowByTitle(titleYt);
        closeAllWindowsWithoutParent(parentID);

    }
    public void switchWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)){
                break;
                //document.title
            }
        }
    }
    public boolean closeAllWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        }else {
            return false;
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
