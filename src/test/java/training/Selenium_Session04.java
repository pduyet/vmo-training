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

public class Selenium_Session04 {
    WebDriver driver;
    String parentId;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC01_verifyNumberUser() {
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,6150)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
        String numberUser = driver.findElement(By.xpath("//div[@class='lfloat']/div[contains(text(),'followers')]")).getText();
        System.out.println(numberUser);
        Assert.assertTrue(numberUser.contains("166K"));
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='hotline']//li//second)[1]")).getText().contains("1900.6335.07"));

    }

    @Test
    public void TC02_verify() {
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,6150)", "");
        parentId = driver.getWindowHandle();
        String parentTilte = driver.getTitle();
        driver.findElement(By.xpath("//div[@id='k-footer']//following-sibling::img[@alt='facebook']")).click();
        String titleFB = "Kyna.vn | Ho Chi Minh City | Facebook";
        switchWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        System.out.println(driver.getTitle());
        Assert.assertTrue(titleFB.contains(driver.getTitle()));
        switchWindowById(parentId);
        driver.findElement(By.xpath("//div[@id='k-footer']//following-sibling::img[@alt='youtube']")).click();
        String titleYoutube = "Kyna.vn - YouTube";
        switchWindowByTitle(titleYoutube);
        Assert.assertTrue(driver.getTitle().contains(titleYoutube));
        switchWindowById(parentId);
        driver.findElement(By.xpath("(//div[@id='k-footer-copyright']//following-sibling::img)[1]")).click();
        String titleNumber3 = "Thông tin website thương mại điện tử - Online.Gov.VN";
        switchWindowByTitle(titleNumber3);
        Assert.assertTrue(driver.getTitle().contains(titleNumber3));
        switchWindowById(parentId);
        driver.findElement(By.xpath("(//div[@id='k-footer-copyright']//following-sibling::img)[2]")).click();
        String titleNumber4 = "Thông tin website thương mại điện tử - Online.Gov.VN";
        switchWindowByTitle(titleNumber4);
        switchWindowById(parentId);
        Assert.assertTrue(closeAllWindowWithoutParent(parentId));

    }

    public void switchWindowById(String parentId) {
        Set<String> allWindows = driver.getWindowHandles();  // tra ve id cuar cac tab kieu string
        for (String runWindow : allWindows) {
            if (runWindow.equals(parentId)) {
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
            if (currentTitle.equals(title)) {

                break;
            }
        }

    }

    public boolean closeAllWindowWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1) {
            System.out.println("title tab cuoi cung: " + driver.getTitle());
            return true;
        } else return false;
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
