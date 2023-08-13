import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class demoUT {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @Test(enabled = false)
    public void robotClass() throws Exception {
        Robot rb = new Robot();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='username']")));
        Thread.sleep(10000);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_A);
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyPress(KeyEvent.VK_B);
    }

    @Test(enabled = false)
    public void robotActions() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://www.google.com.vn/");
        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.name("q"));
        actions.click(searchInput).sendKeys("vietnam").doubleClick().build().perform();
    }

    @Test
    public void javaScriptExecutor() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        driver.get("https://www.google.com.vn/");
//        Thread.sleep(2000);
//
//        WebElement searchInput = driver.findElement(By.name("q"));
//        js.executeScript("arguments[0].value = arguments[1];", searchInput, "Hello");

        driver.get("https://kenh14.vn/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//div[@data-marked-zoneid='kenh14_home_bs1']/li[1]"));

        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
