package Topic03;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Topic03_B01_Homework {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void hw1() throws InterruptedException {
        String hw1_url = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select";
        driver.get(hw1_url);
        driver.switchTo().frame("iframeResult");
        Assert.assertEquals(driver.getCurrentUrl(),hw1_url);
        Select select = new Select(driver.findElement(By.xpath("//select[@id='cars']")));
        select.selectByIndex(1);
        select.selectByValue("opel");
        select.selectByVisibleText("Audi");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'cars=audi')]")).isDisplayed());
    }

    @Test
    public void hw2() throws InterruptedException {
        String hw2_url = "https://jqueryui.com/selectmenu/";
        driver.get(hw2_url);
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        Assert.assertEquals(driver.getCurrentUrl(),hw2_url);
        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@id='speed-menu']//li[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']/span[text()='Slow']")).isDisplayed());

        driver.findElement(By.xpath("//span[@id='files-button']")).click();
        driver.findElement(By.xpath("//ul[@id='files-menu']//li[3]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']/span[text()='ui.jQuery.js']")).isDisplayed());

        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        driver.findElement(By.xpath("//ul[@id='number-menu']//li[4]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='4']")).isDisplayed());

        driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
        driver.findElement(By.xpath("//ul[@id='salutation-menu']//li[2]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']/span[text()='Mr.']")).isDisplayed());
    }

    @Test
    public void hw3(){
        String hw3_url = "https://kenh14.vn/";
        driver.get(hw3_url);
        Assert.assertEquals(driver.getCurrentUrl(),hw3_url);
        String titleFirstArticle = driver.findElement(By.xpath("//h2[@class='klwfnl-title inited']")).getText();
        String titleSecondArticle = driver.findElement(By.xpath("//h2[@class='klwfnr-title inited']")).getText();
        WebElement searchBox = driver.findElement(By.xpath("//p[@id='searchinput']"));
        searchBox.clear();
        searchBox.sendKeys(titleFirstArticle);
        Assert.assertEquals(searchBox.getText(),titleFirstArticle);
        searchBox.clear();
        searchBox.sendKeys(titleSecondArticle);
        Assert.assertEquals(searchBox.getText(),titleSecondArticle);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
