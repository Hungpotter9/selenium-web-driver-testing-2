package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Dropdrop_default {
    WebDriver driver;
    String firstName = "Automation" + new Random().nextInt(99), lastName = "FC" + new Random().nextInt(99)
            ,userEmail = generateEmail(), passWord = "12345678";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");

    }

    @Test
    public void TC_01_Register() {

        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        //xu ly dropdown
        //Verify multiple dropdown? and number of dropdown option
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertFalse(day.isMultiple());
        Assert.assertEquals(day.getOptions().size(), 32);
        day.selectByVisibleText("1");

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertFalse(month.isMultiple());
        Assert.assertEquals(month.getOptions().size(), 13);
        month.selectByVisibleText("May");

        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertFalse(year.isMultiple());
        Assert.assertEquals(year.getOptions().size(),112);
        year.selectByVisibleText("1980");

        //input info
        driver.findElement(By.cssSelector("input#Email")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("button#register-button")).click();
        driver.findElement(By.cssSelector("input#Password")).sendKeys(passWord);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(passWord);
        driver.findElement(By.cssSelector("button#register-button")).click();

        //Verify home page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Register']")));
        SleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")), "Your registration completed");


    }


    public String generateEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

    public void SleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
