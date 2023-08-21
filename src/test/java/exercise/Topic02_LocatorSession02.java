package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Topic02_LocatorSession02 {
    WebDriver driver;
    String userName = "Admin";
    String password = "admin123";
    String firstName = "Linh";
    String middleName = "Huong";
    String lastName = "Nguyen";
    String email = "linhnth86@gmail.com";
    String url = "https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.10.0";
    String comment = "Let's do it then go to sleep";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void VerifyCreateEmployee() throws InterruptedException {
        driver.get(("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);
        boolean leftMenu = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']")).isEnabled();

        Assert.assertTrue(leftMenu);

        WebElement linkPIM = driver.findElement(By.xpath("//span[text()[contains(.,'PIM')]]"));
        boolean isPIMSelected = linkPIM.isSelected();

        if (!isPIMSelected) {
            linkPIM.click();
            Thread.sleep(2000);
        }
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(10000);
        WebElement lblFullName = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-imagesection']//h6"));

        Assert.assertEquals(lblFullName.getText(), firstName + " " + lastName);
    }

    @Test
    public void TC02_ValidationInformationGlobalSQA() {
        driver.get("https://www.globalsqa.com/samplepagetest/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/samplepagetest/");

        driver.findElement(By.xpath("//input[@class='name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@class='email']")).sendKeys(email);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//input[@class='url']")).sendKeys(url);
        driver.findElement(By.xpath("//input[@value='Automation Testing']")).click();
        driver.findElement(By.xpath("//input[@value='Post Graduate']")).click();
        driver.findElement(By.xpath("//textarea[@name='g2599-comment']")).sendKeys(comment);

        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String lblMessage = driver.findElement(By.xpath("//h3[contains(text(),'Message Sent')]")).getText();
        Assert.assertTrue(lblMessage.toUpperCase().contains("MESSAGE SENT"));

        String lblName = driver.findElement(By.xpath("//p[contains(text(),'Name')]")).getText();
        Assert.assertTrue(lblName.contains(firstName));

        String lblEmail = driver.findElement(By.xpath("//p[contains(text(),'Email')]")).getText();
        Assert.assertTrue(lblEmail.contains(email));

        String lblWebsite = driver.findElement(By.xpath("//p[contains(text(),'Website')]")).getText();
        Assert.assertTrue(lblWebsite.contains(url));

        String lblExpertise = driver.findElement(By.xpath("//p[contains(text(),'Expertise')]")).getText();
        Assert.assertTrue(lblExpertise.contains("Automation Testing"));

        String lblEducation = driver.findElement(By.xpath("//p[contains(text(),'Education')]")).getText();
        Assert.assertTrue(lblEducation.contains("Post Graduate"));

        String lblComment = driver.findElement(By.xpath("//p[contains(text(),'Comment')]")).getText();
        Assert.assertTrue(lblComment.contains(comment));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
