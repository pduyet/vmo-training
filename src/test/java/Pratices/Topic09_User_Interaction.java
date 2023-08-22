package Pratices;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic09_User_Interaction {

  WebDriver driver;

  @BeforeMethod
  public void setUp() {
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("download.prompt_for_download", true);
    options.setExperimentalOption("prefs", prefs);
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
  }

  @Test
  public void TC01_UsingRobotClass() throws Exception {
    Robot rb = new Robot();
    driver.get("https://spreadsheetpage.com/accounting/account-receivable/");
    Assert.assertEquals(driver.getCurrentUrl(), "https://spreadsheetpage.com/accounting/account-receivable/");
    driver.findElement(By.xpath("//a[contains(text(),'Download this template')]")).click();
    Thread.sleep(3000);
    rb.keyPress(KeyEvent.VK_T);
    rb.keyPress(KeyEvent.VK_I);
    rb.keyPress(KeyEvent.VK_E);
    rb.keyPress(KeyEvent.VK_N);
    Thread.sleep(3000);
    rb.keyPress(KeyEvent.VK_ENTER);
    Thread.sleep(3000);
  }

  @Test
  public void TC02_UsingActionClass1() {
    Actions actions = new Actions(driver);
    driver.get("https://demoqa.com/droppable");
    Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");
    WebElement DragMeBox = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='draggable']"));
    WebElement DropHereBox = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));
    actions.dragAndDrop(DragMeBox, DropHereBox).build().perform();
    Assert.assertEquals(DropHereBox.getText(), "Dropped!");
  }

  @Test
  public void TC02_UsingActionClass2() {
    Actions actions = new Actions(driver);
    driver.get("https://demoqa.com/droppable");
    Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/droppable");
    WebElement DragMeBox = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='draggable']"));
    WebElement DropHereBox = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));
    actions.clickAndHold(DragMeBox).moveToElement(DropHereBox).release().build().perform();
    Assert.assertEquals(DropHereBox.getText(), "Dropped!");
  }

  @Test
  public void TC03_UsingJavascriptExecutor() throws InterruptedException {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    driver.get("https://demoqa.com/text-box");
    Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/text-box");
    String fullName = "Thuy Tien";
    String email = "tiennt4@gmail.com";
    String currentAddress = "Ha Noi";
    String permanentAddress = "Viet Nam";

    WebElement fullNameTextBox = driver.findElement(By.id("userName"));
    jsExecutor.executeScript("arguments[0].value = arguments[1];", fullNameTextBox, fullName);
    WebElement emailTextBox = driver.findElement(By.id("userEmail"));
    jsExecutor.executeScript("arguments[0].value = arguments[1];", emailTextBox, email);
    WebElement currentAddressTextBox = driver.findElement(By.xpath("//textarea[@id = 'currentAddress']"));
    jsExecutor.executeScript("arguments[0].value = arguments[1];", currentAddressTextBox, currentAddress);
    WebElement permanentAddressTextBox = driver.findElement(By.xpath("//textarea[@id = 'permanentAddress']"));
    jsExecutor.executeScript("arguments[0].value = arguments[1];", permanentAddressTextBox, permanentAddress);

    jsExecutor.executeScript("document.getElementById('submit').click();");
    Assert.assertTrue(driver.findElement(By.id("name")).getText().contains(fullName));
    Assert.assertTrue(driver.findElement(By.id("email")).getText().contains(email));
    Assert.assertTrue(driver.findElement(By.xpath("//p[@id = 'currentAddress']")).getText().contains(currentAddress));
    Assert.assertTrue(driver.findElement(By.xpath("//p[@id = 'permanentAddress']")).getText().contains(permanentAddress));
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

}


