package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Selenium_Session04_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();

    }

    @Test
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

    @Test
    public void TC02() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/nested_frames");
        List<WebElement> frame = driver.findElements(By.xpath("//frame"));
        System.out.println("Number parent frame: " + frame.size());
       // driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
        driver.switchTo().frame(frame.get(0));

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
        System.out.println(driver.findElement(By.xpath("//body")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "LEFT");

        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
        System.out.println(driver.findElement(By.xpath("//body")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "MIDDLE");

        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));
        System.out.println(driver.findElement(By.xpath("//body")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "RIGHT");

        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame.get(1));
        System.out.println(driver.findElement(By.xpath("//body")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//body")).getText().contains("BOTTOM"));

    }

    public void closeWindowByTitle(String title) {
        if (driver.getTitle().equals(title)) {

        }
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
//    public void tearDown() {
//        driver.quit();
//    }
}
