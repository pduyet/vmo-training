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

public class Topic04_B01_Example {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://skills.kynaenglish.vn/");
    }

    @Test
    public void ex1(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
        String text = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
        Assert.assertTrue(text.contains("166"));
        driver.switchTo().parentFrame();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='hotline']//second[1]")).getText(),"1900.6335.07");
    }

    @Test
    public void ex2() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        String parentID = driver.getWindowHandle();
        System.out.println("Current page when 1st navigate" + driver.getTitle());
//        driver.findElement(By.xpath("//div[@class='col-lg-4 col-xs-12 info '][1]/a[@href][1]")).click();
        WebElement facebook = driver.findElement(By.xpath("(//div[@class='social'])[1]/a[@href][1]"));
        facebook.click();
//        driver.findElement(By.xpath("(//div[@class='social'])[1]/a[@href][2]")).click();
        switchWindowByTitle("Kyna.vn | Ho Chi Minh City  | Facebook");
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
//        switchWindowByTitle("Kyna.vn - YouTube");
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
//        switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
//        Assert.assertEquals(driver.getCurrentUrl(),"http://online.gov.vn/Home/WebDetails/61473");
//        if (closeAllWindowsWithoutParent(parentID)){
//        Assert.assertEquals(driver.getWindowHandle(),parentID);}
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
    public boolean closeAllWindowsWithoutParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow:allWindows){
            if(!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                driver.close();
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
