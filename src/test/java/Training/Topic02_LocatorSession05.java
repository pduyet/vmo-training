package Training;

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

public class Topic02_LocatorSession05 {
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();}

    @Test
    public void T01_Checkenable() throws InterruptedException{
        driver.get("https://www.fahasa.com/customer/account/create");
        WebElement dangNhap = driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]"));
        dangNhap.click();
        WebElement btnLogin = driver.findElement(By.xpath("//button[@class ='fhs-btn-register']"));
        Assert.assertFalse((btnLogin.isEnabled()));
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id ='login_username']"));
        inputEmail.sendKeys("dungntm@vmogroup.com");
        Thread.sleep(5000);
        WebElement inputPass = driver.findElement(By.xpath("//input[@id ='login_password']"));
        inputPass.sendKeys("1111111");
        Thread.sleep(5000);
        WebElement Verify = driver.findElement(By.xpath("//button[@class ='fhs-btn-login']"));
        Assert.assertTrue(Verify.isEnabled());
    };

    @Test
    public void T02_Checkbox() throws InterruptedException{
        driver.get("https://demoqa.com/checkbox");
        WebElement iconButtonArray = driver.findElement(By.xpath("(//button[@class ='rct-collapse rct-collapse-btn'])[1]"));
        iconButtonArray.click();
        WebElement iconButtonArrayDocuments = driver.findElement(By.xpath("(//button[@class ='rct-collapse rct-collapse-btn'])[3]"));
        iconButtonArrayDocuments.click();
        WebElement iconButtonArrayOffice = driver.findElement(By.xpath("(//button[@class ='rct-collapse rct-collapse-btn'])[5]"));
        iconButtonArrayOffice.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        List<WebElement> verifyCheckBox = driver.findElements((By.xpath("//span[text()='Office']//parent::label//parent::span/parent::li//ol//label")));
         for (WebElement checkBox : verifyCheckBox) {
             checkBox.click();
         }
        Thread.sleep(5000);
        List<WebElement> verifySelected = driver.findElements(By.xpath("//span[text()='Office']//following::ol//input[@type ='checkbox']"));
        for (WebElement webElement : verifySelected) {
            Assert.assertTrue(webElement.isSelected());
        }
    }
    @Test
    public void T03_Checkradio() throws InterruptedException{
        driver.get("https://demoqa.com/radio-button");
        WebElement radioButton = driver.findElement(By.xpath("(//label[@class ='custom-control-label'])[2]"));
        Thread.sleep(5000);
        radioButton.click();
        WebElement verifyRadioSelected = driver.findElement(By.xpath("//input[@id ='impressiveRadio']"));
        Assert.assertTrue(verifyRadioSelected.isSelected());
        WebElement verifyText = driver.findElement(By.xpath("//span[@class = 'text-success']"));
        Assert.assertEquals(verifyText.getText(),"Impressive");
    }

    @Test
    public void T04_verifyNumberPeople() throws InterruptedException{
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='face-content']//iframe")));
        WebElement VerifyNumber = driver.findElement(By.xpath("//div[contains(text(),'166K followers')]']"));
        Assert.assertEquals(VerifyNumber.getText(),"166K followers");
        driver.switchTo().defaultContent();
        WebElement hotLine = driver.findElement(By.xpath("//first[contains(text(),'Hotline')]/following-sibling::second"));
        Assert.assertEquals(hotLine.getText(),"1900.6335.07");
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}


