package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic06_DropdownListCustom {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void DropdownListCustom() {
        driver.get("https://jqueryui.com/selectmenu/");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']")).isDisplayed());

        selectOption("//span[@id='files-button']", "//ul[@id='files-menu']//li", "Some other file with a very long option text");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[2]")).getText(), "Some other file with a very long option text");

        selectOption("//span[@id='number-button']", "//ul[@id='number-menu']//li", "10");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[3]")).getText(), "10");

        selectOption("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li", "Other");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='ui-selectmenu-text'])[4]")).getText(), "Other");
    }

    @Test
    public void TC01_VerifyButton() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        WebElement bntLogin = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(bntLogin.isEnabled());

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0938628291");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("12345678");
        Assert.assertTrue(bntLogin.isEnabled());
    }

    @Test
    public void TC03_VerfiyCheckbox() {
        driver.get("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("//button[@class='rct-collapse rct-collapse-btn']")).click();
        driver.findElement(By.xpath("(//button[@class='rct-collapse rct-collapse-btn'])[3]")).click();
        driver.findElement(By.xpath("(//button[@class='rct-collapse rct-collapse-btn'])[5]")).click();

        checkListCheckbox("//span[text()='Office']//parent::label//parent::span/parent::li//ol//label");

        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tree-node-office']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tree-node-public']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tree-node-private']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tree-node-classified']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tree-node-general']")).isSelected());
        driver.quit();
    }

    @Test
    public void TC04_VerifyRadio() {
        driver.get("https://demoqa.com/radio-button");
        driver.findElement(By.xpath("//label[@for='impressiveRadio']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='text-success']")).getText(), "Impressive");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='impressiveRadio']")).isSelected());
    }

    @Test
    public void TC05_Textbox(){
        driver.get("https://demoqa.com/text-box");
        WebElement fullName = driver.findElement(By.xpath("//input[@id='userName']"));
        Assert.assertEquals(fullName.getAttribute("placeholder"),"Full Name");
        fullName.sendKeys("Linh Nguyen");
        fullName.clear();
        fullName.sendKeys("Nguyen Linh");
        Assert.assertEquals(fullName.getAttribute("value"),"Nguyen Linh");

        WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        Assert.assertEquals(email.getAttribute("placeholder"),"name@example.com");
        email.sendKeys("linhnguyen@gmail.com");
        email.clear();
        email.sendKeys("nguyenlinh@gmail.com");
        Assert.assertEquals(email.getAttribute("value"),"nguyenlinh@gmail.com");

        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddress.sendKeys("siltakulankuja" +
                                             "\nsiltamaki" +
                                             "\nHelsiki");
        Assert.assertEquals(currentAddress.getAttribute("value"),"siltakulankuja" +
                                                                                "\nsiltamaki" +
                                                                                "\nHelsiki");
        WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        permanentAddress.sendKeys("Hanoi" +
                "\nHanoi" +
                "\nVietnam");
        Assert.assertEquals(permanentAddress.getAttribute("value"),"Hanoi" +
                "\nHanoi" +
                "\nVietnam");

        driver.findElement(By.xpath("//button[@id='submit']")).click();

        List<WebElement> result = driver.findElements(By.xpath("//div[@class='border col-md-12 col-sm-12']//p"));
        Assert.assertEquals(result.get(0).getText(),"Name:Nguyen Linh");
    }
    public void checkListCheckbox(String xpath) {
        List<WebElement> allCheckBox = driver.findElements(By.xpath(xpath));
        for (WebElement check : allCheckBox) {
            if (!check.isSelected()) {
                check.click();
            }
        }
    }

    public void selectOption(String parentXpath, String childXpath, String selectedValue) {
        driver.findElement(By.xpath(parentXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(childXpath)));
        List<WebElement> allOptions = driver.findElements(By.xpath(childXpath));
        for (WebElement option : allOptions) {
            if (option.getText().contains(selectedValue)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
            }
        }

    }

    @Test
    public void PracticeDropDownList(){
        driver.get("https://www.letskodeit.com/practice");

        Select select = new Select(driver.findElement(By.xpath("//select[@id='carselect']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"BMW");
        select.selectByVisibleText("Honda");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Honda");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
