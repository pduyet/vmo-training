package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
public class Topic04_IframeSession {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void TC01_VerifyTabCambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/ ");
        String parentID = driver.getWindowHandle();
        Assert.assertEquals(driver.getTitle(),"Cambridge Dictionary | English Dictionary, Translations & Thesaurus");

        driver.findElement(By.xpath("//span[@class='tb'][normalize-space()='Log in']")).click();
        switchWindowByTitle("Login");
        Assert.assertEquals(driver.getTitle(),"Login");
        Assert.assertTrue(driver.getCurrentUrl().contains("dictionary.cambridge.org"));
        Thread.sleep(5000);
        String parentIDLg = driver.getWindowHandle();

        driver.findElement(By.xpath("//button[@id='Facebook_btn']")).click();
        switchWindowByTitle("Facebook");
        Assert.assertEquals(driver.getTitle(),"Facebook");
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com/login"));
        String parentIDFb = driver.getWindowHandle();
        Thread.sleep(2000);

        switchWindowByTitle("Login");
        driver.findElement(By.xpath("//button[@id='Google_btn']")).click();
        switchWindowByTitle("Sign in");
        Assert.assertEquals(driver.getTitle(),"Sign in - Google Accounts");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/"));

        Set<String> IDWindows = driver.getWindowHandles();
        System.out.println(IDWindows);

        closeAllWindowsWithoutParent(parentID,parentIDLg);
        switchWindowByID("Cambridge Dictionary | English Dictionary, Translations & Thesaurus");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='lmb-12']//iframe[@id='twitter-widget-0']")));
        String titleFirstArticle = driver.findElement(By.xpath("(//div[@data-testid='tweetText']/span)[1]")).getText();
       WebElement firstArticle = driver.findElement(By.xpath("(//section[@class='css-1dbjc4n r-150rngu r-473msk r-1ljd8xs r-lyc4rv r-13l2t4g r-eqz5dr r-16y2uox r-1wbh5a2 r-11yh6sk r-1rnoaur r-1sncvnh']/div/div)[1]"));
       firstArticle.click();
       switchWindowByTitle("Cambridge Dictionary (@CambridgeWords) / X");
       Thread.sleep(5000);
       Assert.assertEquals(driver.findElement(By.xpath("//div[@data-testid='tweetText']/span")).getText(),titleFirstArticle);
    }
    @Test
    public void TC02_VerifyPageNested() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/nested_frames");
        List<WebElement> listFrame = driver.findElements(By.xpath("//frame"));
        System.out.println("Total number of parent iframe/frame: " + listFrame.size());
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "LEFT");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "MIDDLE");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "RIGHT");

        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-bottom']")));
        Assert.assertTrue(driver.findElement(By.xpath("//body")).getText().contains("BOTTOM"));
        driver.switchTo().defaultContent();

    }
    public void switchWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
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
    public boolean closeAllWindowsWithoutParent(String parentID1, String parentID2){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow:allWindows){
            if(!runWindow.equals(parentID1) & !runWindow.equals(parentID2) ){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID2);
        if(driver.getWindowHandles().size()==1){
            return true;
        } else {
            return false;
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
