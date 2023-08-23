package Topic01_Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_B02_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_CreateNewEmployeeOrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        String activeMenu = driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).getText();
        if (activeMenu == "PIM") {
            driver.findElement(By.xpath("//a[contains(text(),'Add Employee')]")).click();
            Thread.sleep(2000);
        } else {
            driver.findElement(By.xpath("//a[contains(@href,'viewPimModule')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'Add Employee')]")).click();
            Thread.sleep(2000);
        }
        String[] xpath =
                {
                        "//input[@name='firstName']",
                        "//input[@name='middleName']",
                        "//input[@name='lastName']"
                };
        String[] input =
                {
                        "Nguyen",
                        "Thao",
                        "Uyen"
                };
        for (int i = 0; i < xpath.length; i++) {
            driver.findElement(By.xpath(xpath[i])).sendKeys(input[i]);
        }
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(8000);
        String fullName = driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']")).getText();
        Assert.assertEquals(fullName, input[0] + " " + input[2]);
    }

    @Test
    public void TC02_ValidationInfoGlobalSQA() throws InterruptedException {
        driver.get("https://www.globalsqa.com/samplepagetest/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/samplepagetest/");
        String[] xpath =
                {
                        "//input[@id='g2599-name']",
                        "//input[@id='g2599-email']",
                        "//input[@id='g2599-website']",
                        "//input[@value='Functional Testing']",
                        "//input[@value='Graduate']",
                        "//textarea[@name='g2599-comment']"
                };
        String[] input =
                {
                        "Uyen Nguyen",
                        "uyenntt1@vmogroup.com",
                        "https://www.globalsqa.com/samplepagetest/",
                        "Functional Testing",
                        "Graduate",
                        "abc"
                };

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < xpath.length; i++) {
            if (i==3 || i ==4){
                driver.findElement(By.xpath(xpath[i])).click();
                js.executeScript("window.scrollBy(0,250)", "");
            } else {
                driver.findElement(By.xpath(xpath[i])).sendKeys(input[i]);
            }
        }
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        js.executeScript("window.scrollBy(0,-200)", "");
        Thread.sleep(2000);
        String check = driver.findElement(By.xpath("//div[@class='twelve columns']//div//h3")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(check.toUpperCase(),"MESSAGE SENT (GO BACK)");
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Name')]")).getText(), "Name: "+input[0]);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Email')]")).getText(), "Email: "+input[1]);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Website')]")).getText(), "Website: "+input[2]);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Expertise')]")).getText(), "Expertise :: "+input[3]);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Education')]")).getText(), "Education: "+input[4]);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Comment')]")).getText(), "Comment: "+input[5]);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
