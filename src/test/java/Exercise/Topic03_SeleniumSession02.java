package Exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic03_SeleniumSession02 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void HW03_VerifyText() throws InterruptedException {
        driver.get("https://kenh14.vn/");
        Assert.assertEquals(driver.getTitle(), "Kênh tin tức giải trí - Xã hội - Kenh14.vn");

        WebElement getTitleTheFirst = driver.findElement(By.xpath("//h2[@class ='klwfnl-title inited']/a"));
        String titleTheFirst = getTitleTheFirst.getAttribute("title");
        WebElement inputSearch = driver.findElement(By.xpath("//p[@id='searchinput']"));
        inputSearch.sendKeys(titleTheFirst);
        Thread.sleep(5000);
        String verifyTextSearch = inputSearch.getText();
        Assert.assertEquals(verifyTextSearch, titleTheFirst);
        driver.navigate().refresh();

        WebElement getTitleTheSecond = driver.findElement(By.xpath("//h2[@class ='klwfnr-title inited']/a"));
        String titleTheSecond = getTitleTheSecond.getAttribute("title");
        WebElement inputSearchTheSecond = driver.findElement(By.xpath("//p[@id='searchinput']"));
        inputSearchTheSecond.sendKeys(titleTheSecond);
        Thread.sleep(5000);
        String verifyTextSearchTheSecond = inputSearchTheSecond.getText();
        Assert.assertEquals(verifyTextSearchTheSecond, titleTheSecond);
    }


    @Test
    public void HW02_HandleDropdownListCustom() throws InterruptedException {
        driver.get("https://jqueryui.com/selectmenu/");
        Assert.assertEquals(driver.getTitle(), "Selectmenu | jQuery UI");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);

        WebElement selectSpeed = driver.findElement(By.xpath("//span[@id = 'speed-button']"));
        selectSpeed.click();
        WebElement selectSpeedOption = driver.findElement(By.xpath("//div[contains(text(),'Faster')]"));
        String getTextSpeed = selectSpeedOption.getText();
        selectSpeedOption.click();
        WebElement verifyOptionSelected = driver.findElement(By.xpath("(//span[@class = 'ui-selectmenu-text'])[1]"));
        String OptionSelected = verifyOptionSelected.getText();
        Assert.assertEquals(OptionSelected, getTextSpeed);

        WebElement selectFile = driver.findElement(By.xpath("//span[@id = 'files-button']"));
        selectFile.click();
        Thread.sleep(3000);
        WebElement selectFileOption = driver.findElement(By.xpath("//div[contains(text(),'Some unknown file')]"));
        String getTextFile = selectFileOption.getText();
        selectFileOption.click();
        WebElement verifyOptionFileSelected = driver.findElement(By.xpath("(//span[@class = 'ui-selectmenu-text'])[2]"));
        String OptionFileSelected = verifyOptionFileSelected.getText();
        Assert.assertEquals(OptionFileSelected, getTextFile);

        WebElement selectNumber = driver.findElement(By.xpath("//span[@id = 'number-button']"));
        selectNumber.click();
        WebElement selectNumberOption = driver.findElement(By.xpath("//li//div[text() = '5']"));
        String getTextselectNumberOption = selectNumberOption.getText();
        selectNumberOption.click();
        WebElement verifyOptionNumberSelected = driver.findElement(By.xpath("(//span[@class = 'ui-selectmenu-text'])[3]"));
        String OptionNumberSelected = verifyOptionNumberSelected.getText();
        Assert.assertEquals(OptionNumberSelected, getTextselectNumberOption);

        WebElement selectTitle = driver.findElement(By.xpath("//span[@id = 'salutation-button']"));
        selectTitle.click();
        WebElement selectTitleOption = driver.findElement(By.xpath("//li//div[text() = 'Mrs.']"));
        String getTextSelectTitleOption = selectTitleOption.getText();
        selectTitleOption.click();
        WebElement verifyOptionTitleSelected = driver.findElement(By.xpath("(//span[@class = 'ui-selectmenu-text'])[4]"));
        String OptionTitleSelected = verifyOptionTitleSelected.getText();
        Assert.assertEquals(getTextSelectTitleOption, OptionTitleSelected);


    }

    @Test
    public void HW01_HandleSelectDropdownList() throws InterruptedException {
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        Assert.assertEquals(driver.getTitle(), "W3Schools Tryit Editor");
        driver.switchTo().frame("iframeResult");
        WebElement selectOption = driver.findElement(By.xpath("//select[@id ='cars']"));
        Select selectList = new Select(selectOption);
        selectList.selectByIndex(1);
        selectList.selectByValue("opel");
        selectList.selectByVisibleText("Audi");
        WebElement btnSubmit = driver.findElement(By.xpath("//input[@type ='submit']"));
        btnSubmit.click();
        Thread.sleep(5000);
        WebElement verifyText = driver.findElement(By.xpath("//div[contains(text(),'cars=audi')]"));
        verifyText.isDisplayed();

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
