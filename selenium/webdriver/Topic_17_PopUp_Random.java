package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_PopUp_Random {
    WebDriver driver;
    WebDriverWait wait;



    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_RandomInDOM1() {
        driver.get("https://vnk.edu.vn/");
        SleepInSeconds(3);
        if(driver.findElement(By.xpath("//div[@class='pum-content popmake-content']")).isDisplayed()){
            driver.findElement(By.xpath("//button[@class='pum-close popmake-close']")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='pum-content popmake-content']")).isDisplayed());
    }

    @Test
    public void TC_01_RandomInDOM2() {
        driver.get("https://www.kmplayer.com/home");
        SleepInSeconds(3);
        if(driver.findElement(By.xpath("//img[@alt='Play to Earn']")).isDisplayed()){
            driver.findElement(By.cssSelector("div.close")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Play to Earn']")).isDisplayed());
    }
    @Test
    public void TC_02_PopupNotInDOM() {
        driver.get("https://www.javacodegeeks.com/");
        if(driver.findElements(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])"))
                .size() > 0 && driver.findElements(By
                .cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])"))
                .get(0).isDisplayed()){
            System.out.println("Pop up display");
            driver.findElement(By
                    .xpath("//a[@onclick='return lepopup_close();' and not(text()='No Thanks!')]")).click();
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//h1[text()='Search Results for: ']")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
    }
    @Test
    public void TC_03_Popup_not_inDOM() {
        driver.get("https://dehieu.vn/");
        if(driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']")).isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']")).isDisplayed());

    }

    @Test
    public void TC_04_Fixed_Popup_not_inDOM() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        Assert.assertTrue(driver.findElement(By
                .xpath("//img[@alt='Facebook']/parent::div/following-sibling::div/div")).isDisplayed());
        //driver.findElement(By.cssSelector("")).click();
        //Assert.assertEquals(driver
         //       .findElements(By.xpath("//img[@alt='Facebook']/parent::div/following-sibling::div/div")), 0);
    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            //driver.quit();
        }
    }
    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
