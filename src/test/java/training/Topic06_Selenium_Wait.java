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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic06_Selenium_Wait {
    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }
    public void TC01_VerifyDictionary() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/");
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Cambridge Dictionary']/ancestor::header")).isDisplayed());
        String parentTitle = driver.getTitle();
        driver.findElement(By.xpath("//span[contains(@class,'cdo-login-button')]")).click();
        String expectTitleLogin = "Login";
        switchWindowByTitle(expectTitleLogin);
        System.out.println("Title Login: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Login");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@title='Facebook']")).click();
        String expectTitleFB = "Facebook";
        switchWindowByTitle(expectTitleFB);
        System.out.println("Title fb: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), expectTitleFB);
        switchWindowByTitle(expectTitleLogin);
        Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Google']")).isDisplayed());
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), expectTitleLogin);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@title='Google']")).click();
        String expectTitleGoogle = "Sign in - Google Accounts";
        switchWindowByTitle(expectTitleGoogle);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), expectTitleGoogle);
        switchWindowByTitle(expectTitleFB);
        driver.close();
        switchWindowByTitle(expectTitleGoogle);
        driver.close();
        switchWindowByTitle(parentTitle);
        Assert.assertEquals(driver.getTitle(), parentTitle);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1800)", "");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Twitter Timeline']")));
        WebElement articleIn1stPage = driver.findElement(By.xpath("(//article//div[@data-testid='tweetText'])[1]"));
        String articleIn1stPageText = articleIn1stPage.getText();
        articleIn1stPage.click();
        String nextPage = "Cambridge Dictionary on Twitter: \"@kryes Thank you for raising this with us. We’ve passed the information on to our editors for review";
        switchWindowByTitle(nextPage);

        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains(nextPage));
        WebElement articleIn2ndPage = driver.findElement(By.xpath("//div[@data-testid='tweetText']/span"));
        Assert.assertEquals(articleIn2ndPage.getText(), articleIn1stPageText);

    }


    /*
    Check element khong hien thi
     */
    public boolean isElementNotDisplayed(By by, int timeout) {

        List<WebElement> elementList = driver.findElements(by);
        if (elementList.isEmpty()) {
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return true;
    }
    public void switchWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title))
                break;
        }
    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }
}
