package training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Topic02_CheckboxSession02 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void TC01_VerifyDropdown() {
        driver.get("https://jqueryui.com/selectmenu/");
        Assert.assertEquals(driver.getTitle(),"Selectmenu | jQuery UI");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
        String parentSpeed = "//span[@id='speed-button']";
        String childSpeed = "//ul[@id='speed-menu']/li";
        String valueSpeed = "Slow";
        selectItemDropdown(parentSpeed,childSpeed,valueSpeed);
        String verifySpeed = driver.findElement(By.cssSelector("span[id='speed-button'] span[class='ui-selectmenu-text']")).getText();
        Assert.assertEquals(verifySpeed, "Slow");
    }
    @Test
    public void TC02_CheckButton() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement btnLogin = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(btnLogin.isEnabled());
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0892376364");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Pw123456");
        Assert.assertTrue(btnLogin.isEnabled());
    }
    @Test
    public void TC03_VerifyCheckBox() {
        driver.get("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("//button[@title='Toggle']//*[name()='svg']")).click();
        driver.findElement(By.xpath("//label[@for='tree-node-documents']//preceding-sibling::button")).click();
        driver.findElement(By.xpath("//label[@for='tree-node-office']//preceding-sibling::button")).click();
        List<WebElement> listEl = driver.findElements(By.xpath("//span[text() = 'Office']//parent::label//parent::span/parent::li//ol//label"));
        for (WebElement childElm : listEl) {
                childElm.click();
                childElm.isSelected();
        }

    }

    @Test
    public void TC03_VerifyRadioButton() {
        driver.navigate().to("https://demoqa.com/radio-button");
        WebElement btnImpressive = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        btnImpressive.click();
        String verifyText = driver.findElement(By.xpath("//span[@class='text-success']")).getText();
        Assert.assertEquals(verifyText,"Impressive");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='impressiveRadio']")).isSelected());

    }

    public void selectItemDropdown(String parentXpath, String childXpath, String expectedValueItem) {
        List<WebElement> listSpeed = driver.findElements(By.xpath(childXpath));
        for (WebElement childElm : listSpeed) {
            if (childElm.getText().equals(expectedValueItem)){
                childElm.click();
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
