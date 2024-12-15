package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_PopUp_Fixed {
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
    public void TC_01_FixedPopupinDOM() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());
        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[text()='Đăng nhập' and @type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']"))
                .getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        driver.findElement(By.cssSelector("svg[data-testid='CloseIcon']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());

    }
    @Test
    public void TC_02_PopupInDOM() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("hung@gmail.net");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Assert.assertTrue(driver.findElement(By
                .xpath("//div[text()='Sai tên đăng nhập hoặc mật khẩu']")).isDisplayed());
        //driver.findElement(By.cssSelector("")).click();
        //Assert.assertFalse(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
    }
    @Test
    public void TC_03_Fixed_Popup_not_inDOM() {
        driver.get("https://tiki.vn/");
        if(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed()){
            Assert.assertTrue(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed());
        }
        driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        //driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Assert.assertTrue(driver.findElement(By
                .xpath("//div[contains(@class,'ReactModal__Content')]")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By
                .xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By
                .xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).isDisplayed());
        driver.findElement(By.cssSelector("img.close-img")).click();
        //Assert khi pop up ko con nam trong DOM (Document Object Model)
        //Nhung van de la se phai doi 30s do bi tac dong boi implicitit wait:  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
        //Do do, can set lai wait = cach:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By
                .xpath("//div[contains(@class,'ReactModal__Content')]")).size(), 0);


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
