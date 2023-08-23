package Topic01_Locator;

import com.github.javafaker.Faker;
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

import java.util.Locale;

public class Topic02_B03_Homework {
    String firstName = "Uyen";
    String lastName = "Nguyen";
    String passwrd = "Vmoholding23@";
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Topic02_B03_Homework3() throws InterruptedException {
        String emailGenerated = generateEmail();
        driver.get("http://www.automationpractice.pl/index.php");
        Assert.assertEquals(driver.getTitle(), "My Shop");
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(emailGenerated);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailGenerated);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group form-ok']")).isDisplayed());
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(),"Your personal information".toUpperCase());
        driver.findElement(By.xpath("//div[@class='radio-inline']/following-sibling::div//input[@id='id_gender2']")).click();
        driver.findElement(By.xpath("//div[@class='clearfix']/following-sibling::div[@class='required form-group'][1]//input")).sendKeys(firstName);
        driver.findElement(By.xpath("//div[contains(@class,'required form-group')][1]/following-sibling::div//input[@id='customer_lastname']")).sendKeys(lastName);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok']")).isDisplayed());
        driver.findElement(By.xpath("//div[contains(@class,'required form-group')][2]/following-sibling::div//input[@id='email']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok'][2]")).isDisplayed());
        driver.findElement(By.xpath("//div[contains(@class,'required form-group')][3]/following-sibling::div//input[@id='passwd']")).sendKeys(passwrd);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok'][3]")).isDisplayed());
        Select daysDropdown = new Select(driver.findElement(By.xpath("//div[@class='col-xs-4'][3]/preceding-sibling::div/div/select[@id='days']")));
        daysDropdown.selectByValue("6");
        Select monthsDropdown = new Select(driver.findElement(By.xpath("//div[@class='col-xs-4'][3]/preceding-sibling::div/div/select[@id='months']")));
        monthsDropdown.selectByIndex(10);
        Select yearsDropdown = new Select(driver.findElement(By.xpath("//div[@class='checkbox']//preceding::select[@id='years']")));
        yearsDropdown.selectByValue("2000");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required password form-group form-ok']")).isDisplayed());
        driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='alert alert-success']")).isDisplayed());
    }
    @Test
    public void Topic02_B03_Homework4() throws InterruptedException {
        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String userID = driver.findElement(By.xpath("//td[@class='accpage' and contains(text(),'User ID')]/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[@class='accpage' and contains(text(),'Password')]/following-sibling::td")).getText();
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//td[@align='right' and contains(text(),'UserID')]/following-sibling::td//input")).sendKeys(userID);
        driver.findElement(By.xpath("//td[@align='right' and contains(text(),'Password')]/following-sibling::td//input")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String welcomeText = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
        Assert.assertEquals(welcomeText, "Welcome To Manager's Page of Guru99 Bank");
        driver.findElement(By.xpath("//li[@class='orange']/following-sibling::li/a[contains(text(),'New Customer')]")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(firstName+" "+lastName);
        driver.findElement(By.xpath("//input[@type='radio' and @value ='f']")).click();
        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys("06-12-2000");
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("cau giay");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("ha noi");
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys("abc");
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("061020");
        String phoneNumber = generatePhoneNumber();
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phoneNumber);
        String emailGenerated = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailGenerated);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwrd);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Name')]/following-sibling::td")).getText(),"Uyen Nguyen");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(),"female");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(),"2000-12-06");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Add')]/following-sibling::td")).getText(),"cau giay");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),"ha noi");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),"abc");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),"061020");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td")).getText(),phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),emailGenerated);
    }
    public String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
    public String generatePhoneNumber() {
        Locale locale = new Locale("vi_VN");
        Faker faker = new Faker(locale);
        return faker.phoneNumber().phoneNumber().replace(" ","");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
