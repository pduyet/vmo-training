package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class Topic04_IframeWindowTab {
    WebDriver driver;

    public void switchWindowByID(String prentID) {
        Set<String> allWindow = driver.getWindowHandles();
        for (String runWindow : allWindow) {
            if (!runWindow.equals(prentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchWindowTitle(String title) {
        Set<String> allWindow = driver.getWindowHandles();
        for (String runWindow : allWindow) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(String parentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentWindow)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        return driver.getWindowHandles().size() == 1;
    }

    public void closeTitle(String titleClose) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(titleClose)) {
                driver.close();
            }
        }
    }


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void T01() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/ ");
        String prentID = driver.getWindowHandle();
        String titleLogin = "Login";
        String titleFB = "Facebook";
        String titleGG = "Sign in - Google Accounts";
        String titleParent = "Cambridge Dictionary | English Dictionary, Translations & Thesaurus";
        Assert.assertEquals(driver.getTitle(),"Cambridge Dictionary | English Dictionary, Translations & Thesaurus");
        WebElement clickLogin = driver.findElement(By.xpath("(//span[text()= 'Log in'])[1]"));

        clickLogin.click();
        switchWindowTitle(titleLogin);
        Assert.assertEquals(driver.getTitle(),titleLogin);
        Thread.sleep(10000);

        WebElement clickFaceBook = driver.findElement(By.xpath("//button[@alt ='Facebook']"));
        clickFaceBook.click();
        switchWindowTitle(titleFB);
        Assert.assertEquals(driver.getTitle(),titleFB);
        Thread.sleep(10000);
        closeTitle(titleFB);
        switchWindowTitle(titleLogin);

        WebElement clickGoogle = driver.findElement(By.xpath("//button[@alt ='Google']"));
        clickGoogle.click();
        switchWindowTitle(titleGG);
        Assert.assertEquals(driver.getTitle(),titleGG);


        closeTitle(titleGG);
        switchWindowTitle(titleParent);
        Assert.assertEquals(driver.getTitle(),"Cambridge Dictionary | English Dictionary, Translations & Thesaurus");
        Thread.sleep(5000);

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title ='Twitter Timeline']")));
        Thread.sleep(5000);
        WebElement getText = driver.findElement(By.xpath("(//div[@data-testid='tweetText']//span)[1]"));
        String text = getText.getText();
        WebElement clickFirst = driver.findElement(By.xpath("(//article[@role ='article'])[1]"));
        Assert.assertTrue(clickFirst.isDisplayed());
        String pageCheck ="Cambridge Dictionary on Twitter: \"@kryes Thank you for raising this with us. We’ve passed the information on to our editors for review.\" / X";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", clickFirst);
        clickFirst.click();
        switchWindowTitle(pageCheck);
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(),pageCheck);

        WebElement pageFirst = driver.findElement(By.xpath("(//div[@data-testid='tweetText']//span)[1]"));
        String verifyText = pageFirst.getText();

        Assert.assertEquals(verifyText,text);

    }

    @Test
    public void T02() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/nested_frames");
        List <WebElement> frame = driver.findElements(By.xpath("//frame"));
        System.out.println("Total:" +frame.size());
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-top']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-left']")));
        WebElement textLEFT = driver.findElement(By.xpath("//body"));
        Assert.assertEquals(textLEFT.getText(),"LEFT");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-middle']")));
        WebElement textMIDDLE = driver.findElement(By.xpath("//body"));
        Assert.assertEquals(textMIDDLE.getText(),"MIDDLE");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-right']")));
        WebElement textRIGHT = driver.findElement(By.xpath("//body"));
        Assert.assertEquals(textRIGHT.getText(),"RIGHT");
        driver.switchTo().parentFrame();

        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-bottom']")));
        WebElement textBOTTOM= driver.findElement(By.xpath("//body"));
        Assert.assertEquals(textBOTTOM.getText(),"BOTTOM");



    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
