package exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Unit09_UserInteraction {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", true);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void TC01() throws AWTException, InterruptedException {
        driver.get("https://spreadsheetpage.com/accounting/account-receivable/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://spreadsheetpage.com/accounting/account-receivable/");
        driver.findElement(By.xpath("//a[text()='Download this template for free']")).click();
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_D);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_O);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_W);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_W);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_N);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_N);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_L);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_L);
        Thread.sleep(500);

        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_O);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_O);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_A);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_D);
        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void TC02_1() {
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement dest = driver.findElement(By.xpath("(//p[text()='Drop here']/parent::div)[1]"));
        System.out.println(dest.getText());
        actions.dragAndDrop(source, dest).build().perform();
        System.out.println(dest.getText());
        Assert.assertEquals(dest.getText(), "Dropped!");
    }

    @Test
    public void TC02_2() {
        driver.get("https://demoqa.com/droppable");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");
        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement dest = driver.findElement(By.xpath("(//p[text()='Drop here']/parent::div)[1]"));
        System.out.println(dest.getText());
        actions.clickAndHold(source).moveToElement(dest).release(dest).build().perform();
        System.out.println(dest.getText());
        Assert.assertEquals(dest.getText(), "Dropped!");
    }

    @Test
    public void TC02() throws InterruptedException {
        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/text-box");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement textFullName = driver.findElement(By.id("userName"));
        WebElement textEmail = driver.findElement(By.id("userEmail"));
        WebElement textAddress = driver.findElement(By.id("currentAddress"));
        WebElement textPermanentAddress = driver.findElement(By.id("permanentAddress"));
        WebElement btnSubmit = driver.findElement(By.id("submit"));

        String fullname = "Chung";
        String email = "chungnt@gmail.com";
        String currentAddress = "Thai\nBinh";
        String permanentAddress = "Cau Giay\nHa Noi";
        js.executeScript("arguments[0].value = arguments[1];", textFullName, fullname);
        js.executeScript("arguments[0].value = arguments[1];", textEmail, email);
        js.executeScript("arguments[0].value = arguments[1];", textAddress, currentAddress);
        js.executeScript("arguments[0].value = arguments[1];", textPermanentAddress, permanentAddress);
        js.executeScript("arguments[0].click();", btnSubmit);
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.id("name")).getText().contains(fullname));
        Assert.assertTrue(driver.findElement(By.id("email")).getText().contains(email));
        System.out.println(currentAddress.replace("\n", " "));
        //   String currentAdd = driver.findElement(By.id("currentAddress")).getText(); // nếu find element by id thì get text ra là empty --??
        String currentAdd = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        System.out.println(currentAdd);
        Assert.assertTrue(currentAdd.contains(currentAddress.replace("\n", " ")));
        String perAdd = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();
        Assert.assertTrue(perAdd.contains(permanentAddress.replace("\n", " ")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
