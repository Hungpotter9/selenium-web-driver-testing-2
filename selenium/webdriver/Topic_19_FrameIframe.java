package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_19_FrameIframe {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_iframe() {
        driver.get("https://toidicodedao.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("div.fb-page")).isDisplayed());
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page span iframe")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div._1drq")).getText(),"405,920 followers");
        driver.switchTo().defaultContent();
        actions.moveToElement(driver.findElement(By.cssSelector("li#menu-item-12")))
                .pause(2000)
                .click(driver.findElement(By.xpath("//a[text()='Series JavaScript sida']"))).perform();
        SleepInSeconds(3);
        //Assert.assertEquals(driver.findElement(By.cssSelector("h1.archive-title")).getText(), "Tag Archives: Javascript sida");
    }
    @Test
    public void TC_02_frame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.details__form-preview-wrapper")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer iframe")));
        Select year = new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2")));
        year.selectByVisibleText("Sophomore");
        Select residence = new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3")));
        residence.selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.switchTo().defaultContent();
        if(driver.findElement(By.cssSelector("div.osano-cm-window__dialog")).isDisplayed()){
            driver.findElement(By.cssSelector("button.osano-cm-button--type_accept")).click();
        }
        SleepInSeconds(3);
        driver.findElement(By.xpath("//nav[contains(@class,'header--desktop-floater')]//a[@title='Log in']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//h1[@class='auth-title hidden-xs']/parent::div//div[@class='auth-card']")));
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

    }
    @Test
    public void TC_03_FrameInEdge() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
        driver.findElement(By.cssSelector("input.form-control")).sendKeys("hung.ngoc");
        driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.urlToBe("https://netportal.hdfcbank.com/nb-login/login.jsp"));
        SleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
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
