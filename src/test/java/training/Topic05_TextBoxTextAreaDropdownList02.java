package training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic05_TextBoxTextAreaDropdownList02 {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void DropDownListDemo() {
        driver.get("https://demoqa.com/select-menu");
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByIndex(4);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Purple");
        select.selectByVisibleText("Magenta");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Magenta");
        select.selectByValue("3");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Yellow");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
