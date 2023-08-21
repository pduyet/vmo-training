package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


public class Topic02_LocatorSession02 {
    WebDriver driver;
    String firstName = "Do";
    String middleName = "Thi";
    String lastName = "Hau";
    String fullName = firstName + " " + lastName;
    String name = "haudt";
    String email = "haudt@vmogroup.com";
    String website  = "https://www.globalsqa.com/samplepagetest/";
    String comment  = "We provide solutions for all sorts of functional and non-functional testing as well as automation testing.";
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void TC01_CreateNewEmployeeInOrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Thread.sleep(5000);

        WebElement sidePanel = driver.findElement(By.xpath("//nav[@aria-label='Sidepanel']"));
        sidePanel.isDisplayed();

        String labelPim = driver.findElement(By.xpath("//*[contains(@class,'oxd-topbar-header-breadcrumb-module')]")).getText();

        if ( labelPim == "PIM") {
            driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
            Thread.sleep(3000);
        }else {
            driver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
            Thread.sleep(3000);
        }
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);

        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);

        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        Thread.sleep(10000);

        driver.findElement(By.cssSelector(".oxd-text.oxd-text--h6.--strong")).getText().contains(fullName);
    };
    @Test
    public void TC02_ValidationInformationInGlobalSQA() throws InterruptedException {
        driver.get("https://www.globalsqa.com/samplepagetest/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/samplepagetest/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='g2599-name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='g2599-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='g2599-website']")).sendKeys(website);

        driver.findElement(By.cssSelector("#g2599-experienceinyears")).click();
        Random random = new Random();
        List<WebElement> experienceList = driver.findElements(By.xpath("//option"));
        int maxExperience = experienceList.size();
        int randomExperience = random.nextInt(maxExperience);
        experienceList.get(randomExperience).click();
        String elExperience = experienceList.get(randomExperience).getText();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        List<WebElement> expertiseList = driver.findElements(By.xpath("//*[contains(@class,'grunion-checkbox-multiple-label checkbox-multiple')]"));
        int maxExpertise = expertiseList.size();
        int randomExpertise = random.nextInt(maxExpertise);
        expertiseList.get(randomExpertise).click();
        String elExpertise = expertiseList.get(randomExpertise).getText();

        js.executeScript("window.scrollBy(0,350)", "");
        List<WebElement> educationList = driver.findElements(By.xpath("//*[contains(@class,'grunion-radio-label radio')]"));
        int maxEducation = educationList.size();
        int randomEducation = random.nextInt(maxEducation);
        educationList.get(randomEducation).click();
        String elEducation = educationList.get(randomEducation).getText();

        driver.findElement(By.cssSelector("#contact-form-comment-g2599-comment")).sendKeys(comment);
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='content_bgr']//h3[1]")).getText(), "Message Sent (go back)");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Name')]")).getText().contains(name));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Email')]")).getText().contains(email));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Website')]")).getText().contains(website));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Experience ')]")).getText().contains(elExperience));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Expertise ')]")).getText().contains(elExpertise));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Education')]")).getText().contains(elEducation));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Comment')]")).getText().contains(comment));

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
