package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic03_TextboxSession01 {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void TC01_HandleSelectDropdownWithSelectListDefault() throws InterruptedException {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        Assert.assertEquals(driver.getTitle(),"W3Schools Tryit Editor");
        driver.switchTo().frame("iframeResult");
        Select objSelect =new Select(driver.findElement(By.id("cars")));
        objSelect.selectByIndex(1);
        objSelect.selectByValue("opel");
        objSelect.selectByVisibleText("Audi");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'cars=audi')]")).isDisplayed());
        driver.findElement(By.cssSelector(".w3-container.w3-large.w3-border")).getText().contains("audi");

    }
    @Test
    public void TC02_HandleDropdownListCustom() throws InterruptedException {
        driver.get("https://jqueryui.com/selectmenu/");
        Assert.assertEquals(driver.getTitle(),"Selectmenu | jQuery UI");
        Thread.sleep(2000);
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);

        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
        driver.findElement(By.xpath("//ul[@id='speed-menu']//li[4]")).click();

        driver.findElement(By.xpath("//span[@id='files-button']")).click();
        driver.findElement(By.xpath("//ul[@id='files-menu']//li[6]")).click();

        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        driver.findElement(By.xpath("//ul[@id='number-menu']//li[3]")).click();

        driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
        driver.findElement(By.xpath("//ul[@id='salutation-menu']//li[3]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']")).getText().contains("Fast"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']")).getText().contains("Some other file"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']")).getText().contains("3"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']")).getText().contains("Mrs."));

    }

    @Test
    public void TC03_VerifyText(){
        driver.get("https://kenh14.vn/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://kenh14.vn/");
        WebElement textBoxSearch = driver.findElement(By.xpath("//p[@id='searchinput']"));
        String labelFirst = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']")).getText();
        textBoxSearch.sendKeys(labelFirst);
        Assert.assertEquals(textBoxSearch.getText(),labelFirst);

        String  labelSecond = driver.findElement(By.xpath("//h2[@class='klwfnr-title inited']/a")).getText();
        textBoxSearch.clear();
        textBoxSearch.sendKeys(labelSecond);
        Assert.assertEquals(textBoxSearch.getText(),labelSecond);
    }



   @AfterMethod
   public void tearDown(){
        driver.quit();
    }

}
