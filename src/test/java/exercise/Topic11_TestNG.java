package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

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
    public void TC02_LoginUnsuccessfulWithIncorrectUserOrPass(@Optional String userName, @Optional String password){
        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
        user.clear();
        user.sendKeys(userName);
        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.clear();
        pass.sendKeys(password);
    }

    @Test (dataProvider = "dataProvider",groups= {"LoginTest"})
    public void TC03_LoginUnsuccessfulWithBlankUserOrPass(String userName, String password){
        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
        user.clear();
        user.sendKeys(userName);
        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.clear();
        pass.sendKeys(password);
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
