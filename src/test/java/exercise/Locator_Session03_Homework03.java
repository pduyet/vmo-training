package exercise;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Locator_Session03_Homework03 {

    WebDriver driver;
    String email;
    String firstName ="nguyen";
    String lastname ="chung";

    String password;
    String dateOfBirth;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get("http://www.automationpractice.pl/index.php");
    }

    @Test
    public void TC01_Register() throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"My Shop");
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
        email = generateEmail();
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        WebElement isValidate = driver.findElement(By.xpath("//input[@id='email_create']/parent::div"));
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        Assert.assertTrue(isValidate.getAttribute("class").contains("form-ok"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='uniform-id_gender2']//input")).click();
        driver.findElement(By.xpath("//label[@for='customer_firstname']/following-sibling::input")).sendKeys(firstName);
        driver.findElement(By.xpath("//label[@for='customer_lastname']/following-sibling::input")).sendKeys(lastname);
        System.out.println(email);

        WebElement txtEmail = driver.findElement(By.xpath("//label[@for='email']/following-sibling::input"));
        txtEmail.clear();
        txtEmail.sendKeys(email);
        password = generatePassword();
        driver.findElement(By.xpath("//label[@for='passwd']/following-sibling::input")).sendKeys(password);
        WebElement selectDate = driver.findElement(By.xpath("//div[@id='uniform-days']//option[4]"));
        String date = selectDate.getText();
        selectDate.click();
        WebElement selectMonth  = driver.findElement(By.xpath("//div[@id='uniform-months']//option[6]"));
        String month = selectMonth.getText();
        selectMonth.click();
        WebElement selectYear = driver.findElement(By.xpath("//div[@id='uniform-years']//option[29]"));
        String year = selectYear.getText();
        selectYear.click();
        dateOfBirth = date + "-" + month + "-" + year;
        System.out.println(dateOfBirth);


        WebElement validateFirstname = driver.findElement(By.xpath("//label[@for='customer_firstname']/parent::div"));
        Assert.assertTrue(validateFirstname.getAttribute("class").contains("form-ok"));
        WebElement validateLastname = driver.findElement(By.xpath("//label[@for='customer_lastname']/parent::div"));
        Assert.assertTrue(validateLastname.getAttribute("class").contains("form-ok"));
        WebElement validateEmail = driver.findElement(By.xpath("//label[@for='email']/parent::div"));
        Assert.assertTrue(validateEmail.getAttribute("class").contains("form-ok"));
        WebElement validatePasss = driver.findElement(By.xpath("//label[@for='passwd']/parent::div"));
        Assert.assertTrue(validatePasss.getAttribute("class").contains("form-ok"));

        driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
        WebElement validateCreate = driver.findElement(By.xpath("//p[contains(@class,'alert-success')]"));
        Assert.assertTrue(validateCreate.getText().contains("Your account has been created."));



    }
    public String generateEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
    public String generatePassword(){
        Faker faker = new Faker();
        return faker.internet().password();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
