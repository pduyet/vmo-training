package Topic04;
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
public class Topic04_B01_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void tc01() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://dictionary.cambridge.org/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//span[contains(text(),'Log in') and @class='tb']/parent::span")).click();
        switchWindowByTitle("Login");
        Assert.assertEquals(driver.getTitle(),"Login");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id='Facebook_btn']")).click();
        switchWindowByTitle("Facebook");
        Assert.assertEquals(driver.getTitle(),"Facebook");
        switchWindowByTitle("Login");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id='Google_btn']")).click();
        Thread.sleep(5000);
        switchWindowByTitle("Sign in - Google Accounts");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(),"Sign in - Google Accounts");

        closeAllWindowsWithoutParent(parentID);
        driver.switchTo().window(parentID);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,1750)", "");

        driver.switchTo().frame("twitter-widget-0");
        String firstArticleText = driver.findElement(By.xpath("(//div[@data-testid='tweetText']/span)[1]")).getText();
        driver.findElement(By.xpath("(//div[@data-testid='tweetText']/span)[1]")).click();

        Thread.sleep(5000);
        switchWindowByTitle("Cambridge Dictionary (@CambridgeWords) / X");

        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@data-testid='tweetText']/span")).getText(), firstArticleText);




    }
    public void switchWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow:allWindows){
            driver.switchTo().window(runWindow);
            if (driver.getTitle().equals(title)){
                break;
            }
        }
    }
    public void closeWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow:allWindows){
            if (driver.getTitle().equals(title)){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
    }
    public boolean closeAllWindowsWithoutParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow:allWindows){
            if(!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(parentID);
        if(driver.getWindowHandles().size()==1){
            return true;
        } else {
            return false;
        }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
