package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic03_CheckBoxSession02 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.letskodeit.com/practice");
    }
    @Test
    public void TC01_HandleCheckBox() {
        Assert.assertEquals(driver.getTitle(), "Practice Page");
        WebElement bmwCheckbox = driver.findElement(By.xpath("//input[@id='bmwcheck']"));
        bmwCheckbox.click();
        Assert.assertTrue(bmwCheckbox.isSelected());
        WebElement benzCheckbox = driver.findElement(By.xpath("//input[@id='benzcheck']"));
        benzCheckbox.click();
        Assert.assertTrue(bmwCheckbox.isSelected());
        Assert.assertTrue(benzCheckbox.isSelected());
        bmwCheckbox.click();
        Assert.assertTrue(benzCheckbox.isSelected());
        Assert.assertFalse(bmwCheckbox.isSelected());
    }
    @Test
    public void TC02_HandleRadioButton() {
        Assert.assertEquals(driver.getTitle(), "Practice Page");
        WebElement hondaButton = driver.findElement(By.xpath("//input[@id='hondaradio']"));
        hondaButton.click();
        Assert.assertTrue(hondaButton.isSelected());
        WebElement benzButton = driver.findElement(By.xpath("//input[@id='benzradio']"));
        benzButton.click();
        Assert.assertTrue(benzButton.isSelected());
        Assert.assertFalse(hondaButton.isSelected());
        hondaButton.click();
        Assert.assertTrue(hondaButton.isSelected());
        Assert.assertFalse(benzButton.isSelected());
        hondaButton.click();
        Assert.assertTrue(hondaButton.isSelected());
        Assert.assertFalse(benzButton.isSelected());
    }
    @Test
    public void TC03_FieldStatus() {
        Assert.assertEquals(driver.getTitle(), "Practice Page");
        WebElement textBoxExample = driver.findElement(By.xpath("//input[@id='enabled-example-input']"));
        WebElement btnDisable = driver.findElement(By.xpath("//input[@id='disabled-button']"));
        WebElement btnEnable = driver.findElement(By.xpath("//input[@id='enabled-button']"));
        if (textBoxExample.isEnabled()) {
            btnDisable.click();
            Assert.assertFalse(textBoxExample.isEnabled());
        }else {
            btnEnable.click();
            Assert.assertTrue(textBoxExample.isEnabled());
        }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
