package Training;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class Topic04_IframeWindowTab {
    WebDriver driver;
    public void switchWindowByID (String prentID){
        Set <String> allWindow = driver.getWindowHandles();
        for(String runWindow : allWindow ){
            if(!runWindow.equals(prentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void  switchWindowTitle (String title){
        Set <String> allWindow = driver.getWindowHandles();
        for(String runWindow : allWindow ){
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if(currentTitle.equals(title)){
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(String parentWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            if (!runWindow.equals(parentWindow)){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size()==1){
            return true;
        }else {
            return false;
        }
    }
    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();}

    @Test
    public void T01_verifyNumberPeople() throws InterruptedException{
        driver.get("https://skills.kynaenglish.vn/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='face-content']//iframe")));
        WebElement VerifyNumber = driver.findElement(By.xpath("//div[@class ='_1drq']"));
        Assert.assertEquals(VerifyNumber.getText(),"166K followers");
        driver.switchTo().defaultContent();
        WebElement hotLine = driver.findElement(By.xpath("//first[contains(text(),'Hotline')]/following-sibling::second"));
        Assert.assertEquals(hotLine.getText(),"1900.6335.07");
    }

    @Test

    public void T02_verifyTab() throws InterruptedException{
        String titleFB = "Kyna.vn | Ho Chi Minh City | Facebook";
        driver.get("https://skills.kynaenglish.vn/");
        String ID = driver.getWindowHandle();
        WebElement clickFb = driver.findElement(By.xpath("(//img[@class = 'img-lazy'])[1]"));
        clickFb.click();
        WebElement clickYou = driver.findElement(By.xpath("(//img[@class = 'img-lazy'])[2]"));
        clickYou.click();
        WebElement clickDK = driver.findElement(By.xpath("(//img[@class = 'img-fluid img-lazy'])[1]"));
        clickDK.click();

        switchWindowTitle(titleFB);
        Assert.assertEquals(driver.getTitle(),titleFB);
        closeAllWindowsWithoutParent(ID);
        //switchWindowByID(ID);





    }





    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

