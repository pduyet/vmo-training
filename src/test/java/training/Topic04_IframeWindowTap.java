package training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class Topic04_IframeWindowTap {
    WebDriver driver;
    public void switchWindowByID(String parentWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            if (!runWindow.equals(parentWindow)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow:allWindows){
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)){
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
    @Test
    public void VerifySoNguoiDangKy(){
        String titleYoutube = "Kyna.vn - YouTube";
        driver = new ChromeDriver();
        driver.get("https://skills.kynaenglish.vn/");
        driver.manage().window().maximize();
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("(//img[@class='img-lazy'])[1]")).click();
        driver.findElement(By.xpath("(//img[@class='img-lazy'])[2]")).click();
        driver.findElement(By.xpath("(//img[@alt='Khóa học trực tuyến'])[5]")).click();
        switchWindowByTitle(titleYoutube);
        Assert.assertEquals(driver.getTitle(),titleYoutube);
        switchWindowByID(parentWindow);
        closeAllWindowsWithoutParent(parentWindow);
        Assert.assertEquals(driver.getTitle(),"Kyna.vn - Học online cùng chuyên gia");
        driver.quit();
    }
}
