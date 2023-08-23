package Topic07;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic07_B01_Homework {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUpBeforeExecuteTC() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @BeforeMethod
    public void setUp() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.xpath("//input[@name='username']"))).perform();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Keys.DELETE);
        actions.doubleClick(driver.findElement(By.xpath("//input[@name='password']"))).perform();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.DELETE);

    }

    @Test
    public void tc01() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
    }

    @Test
    @Parameters({"username", "password"})
    public void tc02(String username, String password) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    @Test(dataProvider = "LoginProvider")
    public void tc03(String username, String password) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    @DataProvider(name = "LoginProvider")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"", ""}
                };
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
