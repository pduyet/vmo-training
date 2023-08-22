package Pratices;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic08_IFrame_Windows_Tab_Handles {

  WebDriver driver;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @Test
  public void TC01_HandelWindowsAndIFrame() throws InterruptedException {
    driver.get("https://dictionary.cambridge.org/");
    String CambridgeWindowId = driver.getWindowHandle();
    Assert.assertEquals(driver.getTitle(),
        "Cambridge Dictionary | English Dictionary, Translations & Thesaurus");

    driver.findElement(By.xpath("//div[@role='button']//span[text()='Log in']")).click();
    switchToWindowByTitle("Login");
    Assert.assertEquals(driver.getTitle(), "Login");

    Thread.sleep(5000);
    driver.findElement(By.xpath("//button[@id='Facebook_btn']")).click();
    switchToWindowByTitle("Facebook");
    Assert.assertEquals(driver.getTitle(), "Facebook");

    switchToWindowByTitle("Login");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@id='Google_btn']")).click();
    switchToWindowByTitle("Sign in - Google Accounts");
    Thread.sleep(2000);
    Assert.assertEquals(driver.getTitle(), "Sign in - Google Accounts");

    closeAllWindowWithoutParent(CambridgeWindowId);
    Thread.sleep(5000);
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Twitter Timeline']")));
    driver.findElement(By.xpath("(//article[@role='article'])[1]"));
    String cambridgeTweet = driver.findElement(By.xpath("(//div[@data-testid='tweetText'])[1]//span")).getText();
    switchToWindowByIdDifferent(CambridgeWindowId);
    Thread.sleep(2000);
    Assert.assertEquals(driver.findElement(By.xpath("//div[@data-testid='tweetText']/span")).getText(), cambridgeTweet);
  }

  @Test
  public void b2() {
    String url = "https://the-internet.herokuapp.com/nested_frames";
    driver.get(url);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    Assert.assertEquals(driver.getCurrentUrl(), url);
    List<WebElement> parentFrame = driver.findElements(By.xpath("//frame"));
    Assert.assertEquals(parentFrame.size(), 2);

    for (int i = 0; i < parentFrame.size(); i++) {

      if (i == 0) {
        driver.switchTo().frame(parentFrame.get(i));
        Assert.assertTrue(driver.findElement(By.xpath("//frameset[@name='frameset-middle']")).isDisplayed());
        List<WebElement> childFrameTop = driver.findElements(By.xpath("//frame"));
        Assert.assertEquals(childFrameTop.size(), 3);
        for (int j = 0; j < childFrameTop.size(); j++) {
          List<WebElement> refreshedFrames = driver.findElements(By.xpath("//frame"));
          driver.switchTo().frame(refreshedFrames.get(j));
          String expectedText = "";
          if (j == 0) {
            expectedText = "LEFT";
          } else if (j == 1) {
            expectedText = "MIDDLE";
          } else {
            expectedText = "RIGHT";
          }
          Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), expectedText);
          driver.switchTo().parentFrame();
        }
        driver.switchTo().defaultContent();
      } else {
        driver.switchTo().frame(parentFrame.get(i));
        String expectedText = "BOTTOM";
        Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), expectedText);
        driver.switchTo().parentFrame();
      }
    }
  }
  public void switchToWindowByIdDifferent(String windowID) {
    Set<String> allWindowIDs = driver.getWindowHandles();
    for (String runWindow : allWindowIDs) {
      if (!runWindow.equals(windowID)) {
        driver.switchTo().window(runWindow);
        break;
      }
    }
  }
  public void switchToWindowByTitle(String expectedTitle) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindow : allWindows) {
      driver.switchTo().window(runWindow);
      String currentTitle = driver.getTitle();
      if (currentTitle.equals(expectedTitle)) {
        break;
      }
    }
  }

  public boolean closeAllWindowWithoutParent(String parentId) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindow : allWindows) {
      if (!runWindow.equals(parentId)) {
        driver.switchTo().window(runWindow);
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
