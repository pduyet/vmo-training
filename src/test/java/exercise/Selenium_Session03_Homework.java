package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium_Session03_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC01_HandleCheckbox() {
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='navbar-inverse-collapse']")).isDisplayed());
        WebElement checkboxBMW = driver.findElement(By.id("bmwcheck"));
        checkboxBMW.click();
        Assert.assertTrue(checkboxBMW.isSelected());
        WebElement checkboxBenz = driver.findElement(By.id("benzcheck"));
        checkboxBenz.click();
        Assert.assertTrue(checkboxBenz.isSelected());
        checkboxBMW.click();
        Assert.assertFalse(checkboxBMW.isSelected());
    }
    @Test
    public void TC02_HandleRadiobutton(){
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='navbar-inverse-collapse']")).isDisplayed());
        WebElement radiobtnHonda = driver.findElement(By.id("hondaradio"));
        radiobtnHonda.click();
        Assert.assertTrue(radiobtnHonda.isSelected());
        WebElement radiobtnBenz = driver.findElement(By.id("benzradio"));
        radiobtnBenz.click();
        Assert.assertTrue(radiobtnBenz.isSelected());
        radiobtnHonda.click();
        Assert.assertTrue(radiobtnHonda.isSelected());
        radiobtnHonda.click();
        Assert.assertTrue(radiobtnHonda.isSelected());
    }
    @Test
    public void TC03_FieldStatus(){
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='navbar-inverse-collapse']")).isDisplayed());
        WebElement input = driver.findElement(By.id("enabled-example-input"));
        Assert.assertTrue(input.isEnabled());
        WebElement btnDisable = driver.findElement(By.id("disabled-button"));
        WebElement btnEnable = driver.findElement(By.id("enabled-button"));
        if (input.isEnabled()) {
            btnDisable.click();
            Assert.assertFalse(input.isEnabled());
        }
        else {
            btnEnable.click();
            Assert.assertTrue(input.isEnabled());
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
