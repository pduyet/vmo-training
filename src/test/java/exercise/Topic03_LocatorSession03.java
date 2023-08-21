package exercise;

import com.github.javafaker.Faker;
import org.apache.commons.text.RandomStringGenerator;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic03_LocatorSession03 {
    WebDriver driver;
    String firstName = "Alissa";
    String lastName = "Petteri";
    String password = "Ali1234#";
    String dob = "1977-07-07";
    String address = "34 Le Duc Tho";
    String city = "Ha Noi";
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void RegisterUser() throws InterruptedException {
        driver.get("http://www.automationpractice.pl/index.php");
        Assert.assertEquals(driver.getTitle(),"My Shop");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_create']")));
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//h3[contains(text(),'Create an account')]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group form-ok']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group form-ok']")).isDisplayed());
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3/following-sibling::div[@class='clearfix']//label)[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(.,'Your personal information')]")).isDisplayed());
        String lblTitle = driver.findElement(By.xpath("(//h3/following-sibling::div[@class='clearfix']//label)[1]")).getText();
        Assert.assertEquals(lblTitle,"Title");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id_gender1']/parent::span")));
        driver.findElement(By.xpath("//input[@id='id_gender1']/parent::span")).click();

        WebElement lblFirstName = driver.findElement(By.xpath("(//label[contains(text(),'Title')]/following::label)[3]"));
        Assert.assertEquals(lblFirstName.getText(),"First name *");
        driver.findElement(By.xpath("//div[@class='required form-group']//input[@id='customer_firstname']")).sendKeys(firstName);
        lblFirstName.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok']")).isDisplayed());

        WebElement lblLastName = driver.findElement(By.xpath("(//input[@id='customer_firstname']/following::label)[1]"));
        Assert.assertEquals(lblLastName.getText(),"Last name *");
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lastName);
        lblLastName.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok'][2]")).isDisplayed());

        WebElement lblEmail = driver.findElement(By.xpath("(//input[@id='customer_lastname']/following::label)[1]"));
        Assert.assertEquals(lblEmail.getText(),"Email *");
        WebElement txtEmail = driver.findElement(By.xpath("//input[@id='email']"));
        txtEmail.clear();
        txtEmail.sendKeys(generateEmail());
        lblEmail.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required form-group form-ok'][3]")).isDisplayed());

        WebElement lblPass = driver.findElement(By.xpath("(//input[@id='email']/following::label)[1]"));
        Assert.assertEquals(lblPass.getText(),"Password *");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
        lblPass.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='required password form-group form-ok']")).isDisplayed());

        String lblDob = driver.findElement(By.xpath("(//div[@id='uniform-newsletter']/preceding::label)[8]")).getText();
        Assert.assertEquals(lblDob,"Date of Birth");
        driver.findElement(By.xpath("//select[@id='days']//option[11]")).click();
        driver.findElement(By.xpath("//select[@id='months']//option[3]")).click();
        driver.findElement(By.xpath("//select[@id='years']//option[21]")).click();
        driver.findElement(By.xpath("//input[@id='newsletter']/parent::span")).click();
        driver.findElement(By.xpath("//span[text()[contains(.,'Register')]]")).click();
        Thread.sleep(3000);
        String msgSuccess = driver.findElement(By.xpath("//p[@class='info-account']/preceding-sibling::p")).getText();

        Assert.assertEquals(msgSuccess,"Your account has been created.");
    }

    @Test (dataProvider = "dataProvider")
    public void CreateANewCustomer(String id, String password){
        driver.get("https://demo.guru99.com/");
        Assert.assertEquals(driver.getTitle(),"Guru99 Bank Home Page");

        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        String userId = driver.findElement(By.xpath("//td[contains(text(),'User ID')]/following-sibling::td")).getText();
        String passId = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();

        driver.get("https://demo.guru99.com/v4/index.php");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(id);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.guru99.com/v4/manager/Managerhomepage.php");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText(),"Manger Id : "+userId);

        driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@value='f']")).click();

        WebElement dobInput = driver.findElement(By.id("dob"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('type', 'text');", dobInput);
        dobInput.sendKeys(dob);

        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
        String phone = generatePhoneNo();
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phone);
        String email = generateEmail();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(generatePass(10));
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//p[text()]")).getText(),"Customer Registered Successfully!!!");

        WebElement lblCustomerId = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td"));
        Assert.assertTrue(lblCustomerId.isDisplayed());

        String lblCustomerName = driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText();
        Assert.assertEquals(lblCustomerName,firstName);

        String lblGerder = driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText();
        Assert.assertEquals(lblGerder,"female");

        String lblDob = driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText();
        Assert.assertEquals(lblDob,dob);

        String lblAddr = driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText();
        Assert.assertEquals(lblAddr,address);

        String lblCity = driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText();
        Assert.assertEquals(lblCity,city);

        String lblPin = driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText();
        Assert.assertEquals(lblPin,"123456");

        String lblMobile = driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText();
        Assert.assertEquals(lblMobile,phone);

        String lblEmail = driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText();
        Assert.assertEquals(lblEmail,email);
    }

    @DataProvider (name = "dataProvider")
    public Object [][] getDataFromDataProvider () {
        return new Object[][]
                {
                        {"bajshs", "hajsjsh"},
                        {"jhdadd", "jshdusd"},
                        {"jhsdusuds", "jsdhusds"}
                };
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public String generateEmail(){
        Faker fake = new Faker();
        return fake.internet().emailAddress();
    }
    public String generatePhoneNo(){
        StringBuilder phone = new StringBuilder(9);
        Random rd = new Random();
        while (phone.length() < 10){
            phone.append(rd.nextInt(9));
        }
        return phone.toString();
    }
    public String generatePass(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(length);
    }

}
