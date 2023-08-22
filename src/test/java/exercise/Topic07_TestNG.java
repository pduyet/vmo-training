package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic07_TestNG {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    }
    @Test
    public void HW21() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Parameters({"admin","Admin123"})
    @Test
    public void HW22(@Optional("admin") String username, @Optional("Admin123") String pw){
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Test(dataProvider = "SearchProvider")
    public void HW23(String username, String pw){
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        {"admin","Admin123"}
                };
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
