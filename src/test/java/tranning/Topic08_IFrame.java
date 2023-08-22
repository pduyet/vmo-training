package tranning;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic08_IFrame {
  WebDriver driver;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://skills.kynaenglish.vn/");

  }

  @Test
  public void TC01_HandelIframe() {
    driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_1drq']")).getText().contains("166"));
    driver.switchTo().defaultContent();
    Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='hotline']//second)[1]")).getText().contains("1900.6335.07"));
  }

  @Test
  public void TC02_HandelWindow() {
    String windowParentId = driver.getWindowHandle();
    driver.findElement(By.xpath("(//img[@alt='facebook']/parent::a)[1]")).click();
    driver.findElement(By.xpath("(//img[@alt='youtube']/parent::a)[1]")).click();
    driver.findElement(By.xpath("(//img[@alt='Khóa học trực tuyến']/parent::a)[4]")).click();

    switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
    switchToWindowByTitle("Kyna.vn - YouTube");
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
    switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
    Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/61473");

    closeAllWindowWithoutParent(windowParentId);
    Assert.assertEquals(driver.getCurrentUrl(), "https://skills.kynaenglish.vn/");
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
