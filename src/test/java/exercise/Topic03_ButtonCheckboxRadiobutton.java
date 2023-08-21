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

public class Topic03_ButtonCheckboxRadiobutton {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_HandleCheckbox() {
        String url = "https://www.letskodeit.com/practice";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        driver.findElement(By.xpath("//input[@id='bmwcheck']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='bmwcheck']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='benzcheck']")).isSelected());
        driver.findElement(By.xpath("//input[@id='benzcheck']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='benzcheck']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='bmwcheck']")).isSelected());
        driver.findElement(By.xpath("//input[@id='bmwcheck']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='bmwcheck']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='benzcheck']")).isSelected());
    }

    @Test
    public void TC02_HandleRadioButton() {
        String url = "https://www.letskodeit.com/practice";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        WebElement benzRadioButton = driver.findElement(By.xpath("//input[@id='benzradio']"));
        WebElement hondaRadioButton = driver.findElement(By.xpath("//input[@id='hondaradio']"));
        hondaRadioButton.click();
        Assert.assertTrue(hondaRadioButton.isSelected());
        Assert.assertFalse(benzRadioButton.isSelected());
        benzRadioButton.click();
        Assert.assertTrue(benzRadioButton.isSelected());
        Assert.assertFalse(hondaRadioButton.isSelected());
        hondaRadioButton.click();
        Assert.assertTrue(hondaRadioButton.isSelected());
        Assert.assertFalse(benzRadioButton.isSelected());
        hondaRadioButton.click();
        Assert.assertTrue(hondaRadioButton.isSelected());
        Assert.assertFalse(benzRadioButton.isSelected());
    }

    @Test
    public void TC03_FieldStatus() {
        String url = "https://www.letskodeit.com/practice";
        driver.get(url);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("return document.readyState").toString().equals("complete");
        String s = driver.getCurrentUrl();
        if (s.equals(url)) {
            System.out.println("Page Loaded");
            System.out.println("Current Url: " + s);
        } else {
            System.out.println("Page did not load");
        }
        WebElement fieldStatus = driver.findElement(By.xpath("//input[@id='enabled-example-input']"));
        if (fieldStatus.isEnabled()) {
            System.out.println("Current Status is Enabled");
            driver.findElement(By.xpath("//input[@id='disabled-button']")).click();
            Assert.assertFalse(fieldStatus.isEnabled());
        } else {
            System.out.println("Current Status is Disabled");
            driver.findElement(By.xpath("//input[@id='enabled-button']")).click();
            Assert.assertTrue(fieldStatus.isEnabled());
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
