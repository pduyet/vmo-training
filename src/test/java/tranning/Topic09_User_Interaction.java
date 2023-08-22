package tranning;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Topic09_User_Interaction {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @Test
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

    @Test
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
        driver.get("https://kenh14.vn/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//div[@data-marked-zoneid='kenh14_home_bs1']/li[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test
    public void Practice1() throws Exception {
        Robot rb = new Robot();
        driver.get("https://www.google.com.vn/");
        rb.keyPress(KeyEvent.VK_A);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_B);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_C);
        Thread.sleep(1000);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_D);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void Practice2() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        WebElement UserNameInput = driver.findElement(By.name("user-name"));
        actions.click(UserNameInput).sendKeys("standard_user").doubleClick().build().perform();
        WebElement PasswordInput = driver.findElement(By.name("password"));
        actions.click(PasswordInput).sendKeys("secret_sauce").doubleClick().build().perform();
        WebElement LoginButton = driver.findElement(By.name("login-button"));
        actions.click(LoginButton).build().perform();
    }

    @Test
    public void Practice3() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://kenh14.vn/");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("(//h2/a)[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(2000);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
