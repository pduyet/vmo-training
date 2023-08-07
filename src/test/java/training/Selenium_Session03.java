package training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_Session03 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
      //  driver.get("https://jqueryui.com/selectmenu/");
    }
    @Test
    public void TC02_SelectCustom(){
        driver.get("https://jqueryui.com/selectmenu/");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        String parentXpath = "//span[@id='speed-button']";
        String childXpath = "//ul[@id='speed-menu']//div";
        selectItemDropdownList(parentXpath,childXpath,"Fast");

    }

    @Test
    public void TC03_VerifyButton(){
        driver.navigate().to("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("(//ul[@class='popup-login-tab']//a)[1]")).click();
        driver.findElement(By.id("login_username")).sendKeys("0369191087");
        driver.findElement(By.id("login_password")).sendKeys("154849489");

        Assert.assertTrue(isButtonEnable(By.xpath("(//button[@title='Đăng nhập']//span[text()='Đăng nhập'])[1]")));
    }
    public boolean isButtonEnable(By by){
        WebElement element = driver.findElement(by);
        if( element.isEnabled()) return true;
        else  return  false;
    }
    @Test
    public void TC04_VerifyCheckbox(){
        driver.navigate().to("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("(//li[contains(@class,'rct-node-parent')]//button)[1]")).click();
        driver.findElement(By.xpath("(//li[contains(@class,'rct-node-parent')]//button)[3]")).click();
        driver.findElement(By.xpath("(//button[contains(@class,'rct-collapse-btn')])[5]")).click();
        checkAllCheckbox(By.xpath("//span[text()='Office']//following::ol//label"));
        List<WebElement> listCheckbox = driver.findElements(By.xpath("//span[text()='Office']//following::ol//input"));
        for (WebElement childElement:listCheckbox){
            Assert.assertTrue(childElement.isSelected());
        }
    }
    public void checkAllCheckbox(By by){
        List<WebElement> listCheckbox = driver.findElements(by);
        for (WebElement childElement:listCheckbox){
            if(!childElement.isSelected()) childElement.click();
        }
    }
    @Test
    public void TC05_VerifyRadioButton(){
        driver.navigate().to("https://demoqa.com/radio-button");
        WebElement radioButton = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        String textRadio = radioButton.getText();
        System.out.println(textRadio);
        radioButton.click();
        Assert.assertEquals(textRadio,driver.findElement(By.xpath("//p[contains(text(),'You have selected')]/span")).getText());


    }
    public void selectItemDropdownList(String parentXpath, String childXpath, String expectValueItem){
        //1: click vào cái dropdown chonosos xổ hết tất cả các giá trị ra
        driver.findElement(By.xpath(parentXpath)).click();
        //2: chờ cho tất cả ác giá trị trong dropdown load ra thành công
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

        for (WebElement childElement: allItems){
            if (childElement.getText().equals(expectValueItem)) {
                //3: scroll đến item cần chọn ( nếu item cần chọn có thể nhìn thấy thì ko cần scroll)
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);

                explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(childXpath)));
                //click vào item cần chọn
                childElement.click();
            }
        }

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
