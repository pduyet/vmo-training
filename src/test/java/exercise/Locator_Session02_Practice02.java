package exercise;

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

public class Locator_Session02_Practice02 {
    WebDriver driver;
    String url = "https://www.globalsqa.com/samplepagetest/";
    String urlExpect = "https://www.globalsqa.com/samplepagetest/";

    //Fill information: Name, Email, Website, Expertise, Education, Comment
    String name = "chung";
    String email = "chungnt@vmogroup.com";
    String website = "";
    String experience = "1-3";

    String comment = "Very goood";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC02_ValidationInformationInGlobalSQA() throws InterruptedException {
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Assert.assertEquals(driver.getCurrentUrl(), urlExpect);
        WebElement txtName = driver.findElement(By.xpath("//input[@name='g2599-name']"));
        txtName.sendKeys(name);
        WebElement txtEmail = driver.findElement(By.xpath("//input[@name='g2599-email']"));
        txtEmail.sendKeys(email);
        WebElement txtWebsite = driver.findElement(By.xpath("//input[@name='g2599-website']"));
        txtWebsite.sendKeys(website);

        WebElement selectExperience = driver.findElement(By.xpath("//select[@name='g2599-experienceinyears']"));
        Select select = new Select(selectExperience);
       // select.selectByIndex(3);
        select.selectByValue(experience);
        WebElement checkboxFunctionTesting = driver.findElement(By.xpath("//input[@value='Functional Testing']"));
        js.executeScript("window.scrollBy(0,350)", "");
        checkboxFunctionTesting.click();
        String inputExpertise = checkboxFunctionTesting.getText();
        //System.out.println("expertise: " + checkboxFunctionTesting.getText());
        WebElement radiobtnGraduate = driver.findElement(By.xpath(" //input[@value='Graduate']"));
        String inputEducation = radiobtnGraduate.getText();
        js.executeScript("window.scrollBy(0,350)", "");
        radiobtnGraduate.click();
        WebElement textareaComment = driver.findElement(By.xpath("//textarea[@name='g2599-comment']"));
        textareaComment.sendKeys(comment);
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        js.executeScript("window.scrollBy(0,350)", "");
        btnSubmit.click();

        WebElement labelSent = driver.findElement(By.xpath("//h3[contains(text(),'Message Sent')]"));
        Assert.assertTrue(labelSent.getText().contains("Message Sent"), "Message is " + labelSent.getText() + " but expected is MESSAGE SENT");
        WebElement actualName = driver.findElement(By.xpath("//p[contains(text(),'Name')]"));
        Assert.assertTrue(actualName.getText().contains(name));
        WebElement actualEmail = driver.findElement(By.xpath("//p[contains(text(),'Email')]"));
        Assert.assertTrue(actualEmail.getText().contains(email));
        WebElement actualWebsite = driver.findElement(By.xpath("//p[contains(text(),'Website')]"));
        Assert.assertTrue(actualWebsite.getText().contains(website));
        WebElement actualExperience = driver.findElement(By.xpath("//p[contains(text(),'Experience ')]"));
        Assert.assertTrue(actualExperience.getText().contains(experience));
        WebElement actualExpertise = driver.findElement(By.xpath("//p[contains(text(),'Expertise')]"));
        Assert.assertTrue(actualExpertise.getText().contains(inputExpertise));
        // c muốn hỏi là sau khi submit xong c getText của checkbox đã chọn ở màn trước thì bị lỗi
        // -> có phải là do sang trang mới rồi nên ko get được nữa ko
        WebElement actualGraduation = driver.findElement(By.xpath("//p[contains(text(),'Education')]"));
        Assert.assertTrue(actualGraduation.getText().contains(inputEducation));


        WebElement actualComment = driver.findElement(By.xpath("//p[contains(text(),'Comment')]"));
        Assert.assertTrue(actualComment.getText().contains(comment.replace("\n"," ")));

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
