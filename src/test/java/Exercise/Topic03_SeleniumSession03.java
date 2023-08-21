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

import java.util.List;
public class Topic03_SeleniumSession03 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();}

    @Test
    public void T01_handleCheckBox() throws InterruptedException{
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertEquals(driver.getTitle(),"Practice Page");
        Thread.sleep(5000);
        WebElement clickBMW = driver.findElement(By.xpath("//input[@value ='bmw' and @type = 'checkbox']"));
        clickBMW.click();
        Assert.assertTrue(clickBMW.isSelected());
        WebElement clickBenz = driver.findElement(By.xpath("//input[@id ='benzcheck']"));
        clickBenz.click();
        Assert.assertTrue(clickBenz.isSelected());
        WebElement clickReCheckBMW = driver.findElement(By.xpath("//input[@id ='bmwcheck']"));
        clickReCheckBMW.click();
        Assert.assertFalse(clickReCheckBMW.isSelected());
    };

    @Test
    public void T02_handleRadio() throws InterruptedException{
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertEquals(driver.getTitle(),"Practice Page");
        Thread.sleep(5000);
        WebElement clickHonda = driver.findElement(By.xpath("//input[@id ='hondaradio']"));
        clickHonda.click();
        Assert.assertTrue(clickHonda.isSelected());

        WebElement clickBenz = driver.findElement(By.xpath("//input[@id ='benzradio']"));
        clickBenz.click();
        Assert.assertTrue(clickBenz.isSelected());
        Assert.assertFalse(clickHonda.isSelected());

        WebElement clickHonda2 = driver.findElement(By.xpath("//input[@id ='hondaradio']"));
        clickHonda2.click();
        Assert.assertTrue(clickHonda2.isSelected());
        Assert.assertFalse(clickBenz.isSelected());

        WebElement clickHonda3 = driver.findElement(By.xpath("//input[@id ='hondaradio']"));
        clickHonda3.click();
        Assert.assertTrue(clickHonda3.isSelected());
    };

    @Test
    public void T03_fieldStatus() throws InterruptedException{
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertEquals(driver.getTitle(),"Practice Page");
        WebElement verifyEnable = driver.findElement(By.xpath("//input[@id ='enabled-example-input']"));
        if (verifyEnable.isEnabled()) {
            WebElement btnDisable = driver.findElement(By.xpath("//input[@id ='disabled-button']"));
            btnDisable.click();
            WebElement AfterClickDisable = driver.findElement(By.xpath("//input[@id ='enabled-example-input']"));
            Assert.assertFalse(AfterClickDisable.isEnabled());
        } else {
            WebElement btnEnable = driver.findElement(By.xpath("//input[@id ='enabled-button']"));
            btnEnable.click();
            WebElement AfterClickEnable = driver.findElement(By.xpath("//input[@id ='enabled-example-input']"));
            Assert.assertTrue(AfterClickEnable.isEnabled());

        }
    };


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
