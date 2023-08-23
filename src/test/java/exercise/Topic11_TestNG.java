package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;
public class Topic11_TestNG {
    WebDriver driver;

    @BeforeGroups(groups = {"LoginTest"})
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(groups= {"LoginTest"})
    public void TC01_LoginSuccessful(){
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
    }

    @Test(groups= {"LoginTest"})
    @Parameters ({"userName","password"})
    public void TC02_LoginUnsuccessfulWithIncorrectUserOrPass(@Optional String userName, @Optional String password) throws InterruptedException {
        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
        user.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        user.sendKeys(userName);
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        pass.sendKeys(password);
        Thread.sleep(3000);
    }

    @Test (dataProvider = "dataProvider",groups= {"LoginTest"})
    public void TC03_LoginUnsuccessfulWithBlankUserOrPass(String userName, String password) throws InterruptedException {
        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
        user.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        user.sendKeys(userName);
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        pass.sendKeys(password);
        Thread.sleep(3000);
    }

    @DataProvider(name = "dataProvider")
    public Object [][] getDataFromDataProvider () {
        return new Object[][]
                {
                        {"", "admin123"},
                        {"Admin", ""},
                        {"",""}
                };
    }

    @AfterGroups(groups = {"LoginTest"})
    public void tearDown(){
        driver.quit();
    }

}
