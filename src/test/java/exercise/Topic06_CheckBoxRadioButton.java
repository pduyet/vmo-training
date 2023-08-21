package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Topic06_CheckBoxRadioButton {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Hw01_HandleCheckBox(){
        openPageSuccessful();

        List<WebElement> listCheckBoxInput = driver.findElements(By.xpath(
                "//div[@id='checkbox-example-div']//input"));

        for (int i=0; i<listCheckBoxInput.size() -1 ; i++) {
            tickOnBox(listCheckBoxInput.get(i));
            verifyTick(listCheckBoxInput.get(i));
        }
        verifyTick(listCheckBoxInput.get(0));
        verifyTick(listCheckBoxInput.get(1));
        verifyUntick(listCheckBoxInput.get(2));

        unTickOnBox(listCheckBoxInput.get(0));

        verifyUntick(listCheckBoxInput.get(0));
        verifyTick(listCheckBoxInput.get(1));
        verifyUntick(listCheckBoxInput.get(2));
    }

    @Test
    public void Hw02_HandleRadioButton() {
        openPageSuccessful();

        List<WebElement> listRadioInput = driver.findElements(By.xpath(
                "//div[@id='radio-btn-example']//input"));
        tickOnBox(listRadioInput.get(2));
        verifyTick(listRadioInput.get(2));
        verifyUntick(listRadioInput.get(0));
        verifyUntick(listRadioInput.get(1));

        tickOnBox(listRadioInput.get(1));
        verifyTick(listRadioInput.get(1));
        verifyUntick(listRadioInput.get(0));
        verifyUntick(listRadioInput.get(2));

        tickOnBox(listRadioInput.get(2));
        verifyTick(listRadioInput.get(2));
        verifyUntick(listRadioInput.get(1));
        verifyUntick(listRadioInput.get(0));

        tickOnBox(listRadioInput.get(2));
        verifyTick(listRadioInput.get(2));
    }

    @Test
    public void Hw03_VerifyFieldStatus() {
        openPageSuccessful();

        WebElement textBox = driver.findElement(By.xpath("//input[@id='enabled-example-input']"));
        WebElement btnEnable = driver.findElement(By.xpath("//input[@id='enabled-button']"));
        WebElement btnDisable = driver.findElement(By.xpath("//input[@id='disabled-button']"));

        if (textBox.isEnabled()) {
            btnDisable.click();
            Assert.assertFalse(textBox.isEnabled());
        } else {
            btnEnable.click();
            Assert.assertTrue(textBox.isEnabled());
        }
    }

    public void tickOnBox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unTickOnBox(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void verifyTick(WebElement element) {

        Assert.assertTrue(element.isSelected());
    }

    public void verifyUntick(WebElement element) {

        Assert.assertFalse(element.isSelected());
    }

    public void openPageSuccessful() {
        driver.get("https://www.letskodeit.com/practice");
        Assert.assertEquals(driver.getTitle(), "Practice Page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.letskodeit.com/practice");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
