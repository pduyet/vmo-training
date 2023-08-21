package exercise;

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

public class Topic08_HandleWindowAndIframe {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_VerifyOpenNewWindowOfCambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://dictionary.cambridge.org/");
        Assert.assertEquals(driver.getTitle(), "Cambridge Dictionary | English Dictionary, Translations & Thesaurus");
        String parentId = driver.getWindowHandle();

        driver.findElement(By.xpath("(//span[@class='tb']/parent::span)[1]")).click();
        switchToNewWindowByTitle("Login");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://dictionary.cambridge.org/auth/signin?"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='Facebook_btn']")));

        driver.findElement(By.xpath("//button[@id='Facebook_btn']")).click();
        switchToNewWindowByTitle("Facebook");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/login.php?"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='loginform']")).isDisplayed());

        switchToNewWindowByTitle("Login");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@id='Google_btn']")).click();
        switchToNewWindowByTitle("Sign in - Google Accounts");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://accounts.google.com/"));

        closeTabsExceptParentWindow(parentId);
        switchToNewWindowByTitle("Cambridge Dictionary | English Dictionary, Translations & Thesaurus");

        Thread.sleep(5000);
        WebElement tweeterIframe = driver.findElement(By.xpath("//iframe[@title='Twitter Timeline']"));
        driver.switchTo().frame(tweeterIframe);
        Thread.sleep(5000);
        WebElement firstTweet = driver.findElement(By.xpath("(//div[@data-testid='tweetText']//span)[1]"));
        String firstTweetContent = firstTweet.getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstTweet);
        driver.findElement(By.xpath("(//article[@role ='article'])[1]")).click();

        String tweetDetailPageTitle = "Cambridge Dictionary on Twitter: \"@kryes Thank you for raising this with us. We’ve passed the information on to our editors for review.\" / X";
        switchToNewWindowByTitle(tweetDetailPageTitle);
        Thread.sleep(5000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://twitter.com/CambridgeWords"));
        String tweetContent = driver.findElement(By.xpath("(//div[@data-testid='tweetText']//span)[1]")).getText();
        Assert.assertEquals(tweetContent, firstTweetContent);
    }

    @Test
    public void TC02_VerifyNestFrame() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/nested_frames");

        List<WebElement> allParentFrame = driver.findElements(By.xpath("//frame"));

        int totalParentFrame = allParentFrame.size();
        System.out.println("Total Frame of page are: " + totalParentFrame);

        WebElement topFrame = driver.findElement(By.xpath("//frame[@name ='frame-top']"));
        driver.switchTo().frame(topFrame);

        WebElement leftFrame = driver.findElement(By.xpath("//frame[@name ='frame-left']"));
        driver.switchTo().frame(leftFrame);
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(),"LEFT");
        driver.switchTo().parentFrame();

        WebElement middleFrame = driver.findElement(By.xpath("//frame[@name ='frame-middle']"));
        driver.switchTo().frame(middleFrame);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']")).getText(),"MIDDLE");
        driver.switchTo().parentFrame();

        WebElement rightFrame = driver.findElement(By.xpath("//frame[@name ='frame-right']"));
        driver.switchTo().frame(rightFrame);
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(),"RIGHT");
        driver.switchTo().defaultContent();

        WebElement bottomFrame = driver.findElement(By.xpath("//frame[@name ='frame-bottom']"));
        driver.switchTo().frame(bottomFrame);
        driver.switchTo().defaultContent();
    }

    public void switchToNewWindowByTitle(String title) {
        Set<String> listWindowId = driver.getWindowHandles();
        for (String currentId : listWindowId) {
            driver.switchTo().window(currentId);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)) {
                break;
            }
        }
    }

    public boolean closeTabsExceptParentWindow(String parentId) {
        Set<String> listWindowId = driver.getWindowHandles();
        for (String currectId : listWindowId) {
            if (!currectId.equals(parentId)) {
                driver.switchTo().window(currectId);
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
        return driver.getWindowHandles().size() == 1;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
