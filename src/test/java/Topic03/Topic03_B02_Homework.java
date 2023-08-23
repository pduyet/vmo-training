package Topic03;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class Topic03_B02_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://www.letskodeit.com/practice";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void hw01_handleCheckbox() {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@id='checkbox-example-div']//label[@for]/input"));
        for (WebElement cb : checkBoxes) {
            Assert.assertFalse(cb.isSelected());
        }
        checkBoxes.get(0).click();
        Assert.assertTrue(checkBoxes.get(0).isSelected());
        checkBoxes.get(1).click();
        Assert.assertTrue(checkBoxes.get(1).isSelected());
        Assert.assertTrue(checkBoxes.get(0).isSelected());
        checkBoxes.get(0).click();
        Assert.assertFalse(checkBoxes.get(0).isSelected());
    }
    @Test
    public void hw02_handleRadioButton() {
        List<WebElement> radios = driver.findElements(By.xpath("//div[@id='radio-btn-example']//label[@for]/input"));
        for (WebElement rd : radios) {
            Assert.assertFalse(rd.isSelected());
        }
        radios.get(2).click();
        Assert.assertTrue(radios.get(2).isSelected());
        radios.get(1).click();
        Assert.assertTrue(radios.get(1).isSelected());
        Assert.assertFalse(radios.get(2).isSelected());
        radios.get(2).click();
        Assert.assertTrue(radios.get(2).isSelected());
        Assert.assertFalse(radios.get(1).isSelected());
        radios.get(2).click();
        Assert.assertTrue(radios.get(2).isSelected());
        Assert.assertFalse(radios.get(1).isSelected());
    }
    @Test
    public void hw03_fieldStatus() {
        WebElement enableBtn = driver.findElement(By.xpath("//input[@id='enabled-button']"));
        WebElement disableBtn = driver.findElement(By.xpath("//input[@id='disabled-button']"));
        WebElement inputForm = driver.findElement(By.xpath("//input[@id='enabled-example-input']"));
        if (inputForm.isEnabled()){
            disableBtn.click();
            Assert.assertFalse(inputForm.isEnabled());
        } else {
            enableBtn.click();
            Assert.assertTrue(inputForm.isEnabled());
        }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
