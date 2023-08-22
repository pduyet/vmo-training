package Pratices;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic07_Locator6 {

  WebDriver driver;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.letskodeit.com/practice");
    Assert.assertEquals(driver.getTitle(), "Practice Page");
  }

  @Test
  public void TC01_HandleCheckbox() {
    WebElement BMWCheckbox = driver.findElement(By.xpath("//input[@id='bmwcheck']"));
    BMWCheckbox.click();
    Assert.assertTrue(BMWCheckbox.isSelected());
    WebElement BenzCheckbox = driver.findElement(By.xpath("//input[@id='benzcheck']"));
    BenzCheckbox.click();
    Assert.assertTrue(BMWCheckbox.isSelected());
    Assert.assertTrue(BenzCheckbox.isSelected());
    BMWCheckbox.click();
    Assert.assertFalse(BMWCheckbox.isSelected());
    Assert.assertTrue(BenzCheckbox.isSelected());
  }

  @Test
  public void TC02_HandleRadioButton() {
    WebElement HondaRadioButton = driver.findElement(By.xpath("//input[@id='hondaradio']"));
    HondaRadioButton.click();
    Assert.assertTrue(HondaRadioButton.isSelected());
    WebElement BenzRadioButton = driver.findElement(By.xpath("//input[@id='benzradio']"));
    BenzRadioButton.click();
    Assert.assertTrue(BenzRadioButton.isSelected());
    Assert.assertFalse(HondaRadioButton.isSelected());
    HondaRadioButton.click();
    Assert.assertFalse(BenzRadioButton.isSelected());
    Assert.assertTrue(HondaRadioButton.isSelected());
    HondaRadioButton.click();
    Assert.assertTrue(HondaRadioButton.isSelected());
    Assert.assertFalse(BenzRadioButton.isSelected());
  }

  @Test
  public void TC03_VerifyFieldStatus() {
    WebElement InputExampleTextBox = driver.findElement(
        By.xpath("//input[@id='enabled-example-input']"));
    WebElement DisableButton = driver.findElement(By.xpath("//input[@id='disabled-button']"));
    WebElement EnableButton = driver.findElement(By.xpath("//input[@id='enabled-button']"));
    if (InputExampleTextBox.isEnabled()) {
      DisableButton.click();
      Assert.assertFalse(InputExampleTextBox.isEnabled());
    } else {
      EnableButton.click();
      Assert.assertTrue(InputExampleTextBox.isEnabled());
    }
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}