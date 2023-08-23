package Topic03;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Locale;

public class Topic03_B01_Example {
    String firstName = "Uyen";
    String lastName = "Nguyen";
    String passwrd = "Vmoholding23@";
    String address = "cau giay";
    String city = "ha noi";
    String state = "abc";
    String pin = "061020";
    String phoneNumber = generatePhoneNumber();
    String emailGenerated = generateEmail();


    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailGenerated);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwrd);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Name')]/following-sibling::td")).getText(),firstName+" "+lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(),"female");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(),"2000-12-06");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Add')]/following-sibling::td")).getText(),address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td")).getText(),phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),emailGenerated);

        String customerID = driver.findElement(By.xpath("//td[contains(text(),'ID')]/following-sibling::td")).getText();
        driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
        Thread.sleep(5000);
        WebElement customerSearchBox = driver.findElement(By.xpath("//input[@name='cusid']"));
        customerSearchBox.clear();
        customerSearchBox.sendKeys(customerID);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement nameField= driver.findElement(By.xpath("//input[@name='name']"));
        Assert.assertEquals(nameField.getAttribute("value"),firstName+" "+lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='gender']")).getAttribute("value"),"female");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='dob']")).getAttribute("value"),"2000-12-06");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(),address);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value"),city);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='state']")).getAttribute("value"),state);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='pinno']")).getAttribute("value"),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value"),phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='emailid']")).getAttribute("value"),emailGenerated);

        js.executeScript("arguments[0].removeAttribute('disabled');", nameField);
        nameField.clear();
        nameField.sendKeys("Lam Nguyen");
        Assert.assertEquals(nameField.getAttribute("value"),"Lam Nguyen");






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
