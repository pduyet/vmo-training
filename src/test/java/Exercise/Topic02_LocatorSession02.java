package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic02_LocatorSession02 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_CreateOrangeHRMSystem() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        String text = "PIM";
        String FirstName = "Nguyen";
        String MiddleName = "Mai";
        String LastName = "Dung";

        Assert.assertEquals(driver.getTitle(), "OrangeHRM");

        WebElement Username = driver.findElement(By.name("username"));
        Username.click();
        Username.sendKeys("Admin");
        WebElement Password = driver.findElement(By.name("password"));
        Password.click();
        Password.sendKeys("admin123");
        WebElement Btnlogin = driver.findElement(By.xpath("//button[@type='submit']"));
        Btnlogin.click();
        Thread.sleep(5000);
        WebElement Sidebar = driver.findElement(By.xpath("//aside[@class ='oxd-sidepanel']"));
        Sidebar.isDisplayed();
        WebElement title = driver.findElement(By.xpath("//h6[@class ='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']"));
        if (title.getText().contains(text)) {
            WebElement ButtonAdd = driver.findElement(By.xpath("//a[contains(text(),'Add Employee')]"));
            ButtonAdd.click();
        } else {
            WebElement PinTab = driver.findElement(By.xpath("(//a[@class = 'oxd-main-menu-item'])[2]"));
            PinTab.click();
            Thread.sleep(5000);
            WebElement ButtonAdd = driver.findElement(By.xpath("//a[contains(text(),'Add Employee')]"));
            ButtonAdd.click();
            Thread.sleep(5000);
        }
        WebElement InpFirstName = driver.findElement(By.xpath("//input[@name ='firstName']"));
        InpFirstName.sendKeys(FirstName);
        WebElement InpMiddleName = driver.findElement(By.xpath("//input[@name ='middleName']"));
        InpMiddleName.sendKeys(MiddleName);
        WebElement InpLastName = driver.findElement(By.xpath("//input[@name='lastName']"));
        InpLastName.sendKeys(LastName);
        WebElement BtnSave = driver.findElement(By.xpath("//button[@type ='submit']"));
        BtnSave.click();
        Thread.sleep(10000);
        WebElement Fullname = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-name']"));
        Assert.assertEquals(Fullname.getText(), FirstName + " " + LastName);
    };

    @Test
    public void TC02_ValidationGlobalSQA() throws InterruptedException {
        String name = "Dung";
        String email = "dungntm@vmogroup.com";
        String website = "https://www.google.com.vn/";
        String comment = "noi dung comment";
        String message = "Message Sent (go back)";

        driver.get("https://www.globalsqa.com/samplepagetest/");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/samplepagetest/");
        WebElement InpName = driver.findElement(By.xpath("//input[@id ='g2599-name']"));
        InpName.sendKeys(name);
        WebElement InpEmail = driver.findElement(By.xpath("//input[@id ='g2599-email']"));
        InpEmail.sendKeys(email);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement Website = driver.findElement(By.xpath("//input[@id ='g2599-website']"));
        Website.sendKeys(website);
        WebElement Expertise = driver.findElement(By.xpath("//input[@value='Automation Testing']"));
        Expertise.click();
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement Education = driver.findElement(By.xpath("//input[@value='Graduate']"));
        Education.click();
        WebElement Comment = driver.findElement(By.xpath("//textarea[@id='contact-form-comment-g2599-comment']"));
        Comment.sendKeys(comment);
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement BtnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        BtnSubmit.click();
        Thread.sleep(5000);
        WebElement Textmessage = driver.findElement(By.xpath("//h3[contains(text(),'Message Sent')]"));
        Assert.assertTrue(Textmessage.getText().contains(message));
        WebElement VerifyName = driver.findElement(By.xpath("//p[contains(text(),'Name')]"));
        Assert.assertTrue(VerifyName.getText().contains(name));
        WebElement VerifyEmail = driver.findElement(By.xpath("//p[contains(text(),'Email')]"));
        Assert.assertTrue(VerifyEmail.getText().contains(email));
        WebElement VerifyWebsite = driver.findElement(By.xpath("//p[contains(text(),'Website')]"));
        Assert.assertTrue(VerifyWebsite.getText().contains(website));
        WebElement VerifyExpertise = driver.findElement(By.xpath("//p[contains(text(),'Expertise')]"));
        Assert.assertTrue(VerifyExpertise.getText().contains("Automation Testing"));
        WebElement VerifyEducation = driver.findElement(By.xpath("//p[contains(text(),'Education')]"));
        Assert.assertTrue(VerifyEducation.getText().contains("Graduate"));
        WebElement VerifyComment = driver.findElement(By.xpath("//p[contains(text(),'Comment')]"));
        Assert.assertTrue(VerifyComment.getText().contains(comment));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

