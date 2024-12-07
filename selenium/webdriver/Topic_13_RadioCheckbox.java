package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_RadioCheckbox {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_default() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Choose Extra Equipment']")));
        By dualZoneCheckBox = By.cssSelector("input#eq5");
        By RealSizeCheckBox = By.cssSelector("input#eq1");

        //Case1 neu mo ra ma dualZone da duoc chon thi sao
        checkToElement(RealSizeCheckBox);
        //Case 2, neu mo ra va checkbox chua duoc chon
        checkToElement(dualZoneCheckBox);
        //Verify checkbox
        Assert.assertTrue(driver.findElement(RealSizeCheckBox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());

    }
    @Test
    public void TC_02_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Select Car Engine']")));
        By twoPetroRadio = By.cssSelector("input#engine3");
        By twoDieselRadio = By.cssSelector("input#engine6");
        //Click chon 1 trong 2
        checkToElement(twoDieselRadio);
        Assert.assertFalse(driver.findElement(twoPetroRadio).isSelected());

    }
    @Test
    public void TC_03_AllCheckbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        By CheckboxElement = By.cssSelector("div.form-single-column input[type='checkbox']");
        List<WebElement> AllCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement item : AllCheckbox) {
            if(!item.isSelected()){
                item.click();
            }
        }
        for (WebElement checkBox: AllCheckbox){
            Assert.assertTrue(checkBox.isSelected());
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        //Sau khi refresh, AllCheckbox bi clear het, ko con nhan gia tri nao them
        AllCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for(WebElement checkBox: AllCheckbox){
            if(checkBox.getAttribute("value").equals("Heart Attack") && !checkBox.isSelected()){
                checkBox.click();
            }
        }
        for(WebElement checkBox: AllCheckbox){
            if(checkBox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkBox.isSelected());
            }else {
                Assert.assertFalse(checkBox.isSelected());
            }
        }
    }
    @Test
    public void TC_04_Custom_Checkbox() {
        By register = By.xpath("");
        //Khai bao va su dung Java Script
        ((JavascriptExecutor)driver).executeScript("argument[0].click()", driver.findElement(register));




    }

    @Test
    public void TC_05_Ubuntu_Checkbox_Radio() {
        driver.get("https://login.ubuntu.com/");
        Assert.assertFalse(driver.findElement(By.cssSelector("label.new-user")).isSelected());
        //driver.findElement(By.xpath("//span[text()='I don’t have an Ubuntu One account']")).click();
        driver.findElement(By.cssSelector("label.new-user")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='id_new_user']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Create account']")).isDisplayed());
        Assert.assertEquals(Color.fromString(driver.findElement(By
                .xpath("//button[@data-qa-id='login_button']")).getCssValue("background-color")).asHex(), "#0e8420");
        driver.findElement(By.cssSelector("input#id_accept_tos")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_accept_tos")).isSelected());

    }

    @Test
    public void TC_06_GoogleRadio() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        //Verify truoc khi chon
        //Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']"))
        //        .getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.
                        xpath("//div[@aria-label='Cần Thơ'and @aria-checked ='false']")).isDisplayed());
        driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
        //Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']"))
        //        .getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By
                .xpath("//div[@aria-label='Cần Thơ'and @aria-checked ='true']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By
                .xpath("//div[@aria-label='Quảng Ninh' and @role='checkbox']")).getAttribute("aria-checked"), "false");
        driver.findElement(By.xpath("//div[@aria-label='Quảng Ninh' and @role='checkbox']")).click();
        Assert.assertEquals(driver.findElement(By
                .xpath("//div[@aria-label='Quảng Ninh' and @role='checkbox']")).getAttribute("aria-checked"), "true");

    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            //driver.quit();
        }
    }
    //Ham check Element chua duoc check
    public void checkToElement(By element){
        if(!driver.findElement(element).isSelected()){
            driver.findElement(element).click();
        }
    }
    //Ham uncheck cho element da duoc check
    public void uncheckToElement(By element){
        if(driver.findElement(element).isSelected()){
            driver.findElement(element).click();
        }
    }
    public void SelectCheckbox(String parentCss, String childXpath, String ItemTexExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentCss)));
        //List<WebElement> AllCheckBox = explicitWait.until(ExpectedConditions.
         //       presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        List<WebElement> AllCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement item : AllCheckbox) {
            if(!item.isSelected()){
                item.click();
            }
        }
    }
}
