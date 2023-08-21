package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.webaudio.WebAudio;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic02_LocatorSession02 {
    WebDriver driver;
    String firstName = "Thai";
    String middleName = "Anh";
    String lastName = "Ngo";
    String email = "ngoanhthai100899@gmail.com";
    String url = "https://www.facebook.com/";
    String comment = "hong bik cmt j";
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void TC01_CreateANewEmployeeInOrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        WebElement sidePanel = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']"));
        Assert.assertTrue(sidePanel.isDisplayed());
        WebElement lblTitle = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-title']"));
        String title = lblTitle.getText();
        if (title.contains("PIM")&&driver.findElement(By.xpath("//a[text()='Add Employee']")).isDisplayed()){
            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        }
        else {
            driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-middlename']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-lastname']")).sendKeys(lastName);
        WebElement id = driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"));
        id.clear();
        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
        Thread.sleep(7000);
        String lblFullName = driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']")).getText();
        Assert.assertTrue(lblFullName.contains(firstName + " " + lastName));
    }
    @Test
    public void TC02_ValidationInformationInGlobalSQA() throws InterruptedException {
        driver.get("https://www.globalsqa.com/samplepagetest/");
        driver.findElement(By.xpath("//input[@class='name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@class='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='g2599-website']")).sendKeys(url);
        String lblSelectExpertise = driver.findElement(By.xpath("//input[@value='Functional Testing']")).getText();
        WebElement btnFunctionalTesting = driver.findElement(By.xpath("//input[@value='Functional Testing']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        btnFunctionalTesting.click();
        String lblSelectEducation = driver.findElement(By.xpath("//input[@value='Graduate']")).getText();
        driver.findElement(By.xpath("//input[@value='Graduate']")).click();
        driver.findElement(By.xpath("//textarea[@class='textarea']")).sendKeys(comment);
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class='pushbutton-wide']"));
        btnSubmit.click();

        String lblName = driver.findElement(By.xpath("//p[contains(text(),'Name')]")).getText();
        Assert.assertTrue(lblName.contains(firstName));
        String lblEmail = driver.findElement(By.xpath("//p[contains(text(),'Email')]")).getText();
        Assert.assertTrue(lblEmail.contains(email));
        String lblWebsite = driver.findElement(By.xpath("//p[contains(text(),'Website')]")).getText();
        Assert.assertTrue(lblWebsite.contains(url));
        String lblExpertise = driver.findElement(By.xpath("//p[contains(text(),'Expertise')]")).getText();
        Assert.assertTrue(lblExpertise.contains(lblSelectExpertise));
        String lblEducation = driver.findElement(By.xpath("//p[contains(text(),'Education')]")).getText();
        Assert.assertTrue(lblEducation.contains(lblSelectEducation));
        String lblComment = driver.findElement(By.xpath("//p[contains(text(),'Comment')]")).getText();
        Assert.assertTrue(lblComment.contains(comment));
    }
    @AfterMethod
    public void teardown(){driver.quit();}
}
