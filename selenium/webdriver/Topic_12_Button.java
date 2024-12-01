package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_12_Button {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://egov.danang.gov.vn/reg");
    }

    @Test
    public void TC_01_Button() {
        // Verify button bi disable
        Assert.assertFalse(driver.findElement(By.cssSelector("input.egov-button")).isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input.egov-button")).isEnabled());
        //Lay ma mau len cua button
        //driver.findElement(By.cssSelector("")).getCssValue("background-color");
        String registerBackgroundRGB = driver.findElement(By.cssSelector("input.egov-button"))
                .getCssValue("background-color");
        //Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);
        //String registerBackgroundHexa = registerBackgroundColour.asHex();
        //String registerBackgroundHexa = Color.fromString(registerBackgroundRGB).asHex();
        //Assert.assertEquals(registerBackgroundHexa, "#ef5a00");
        Assert.assertEquals(Color.fromString(registerBackgroundRGB).asHex(),"#ef5a00");
    }
    @Test
    public void TC_02_Button2() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        //Verify Log in button (color and disable)
        Assert.assertFalse(driver.findElement(By.cssSelector("button[class='fhs-btn-login']")).isEnabled());
        String loginBackgroundRGB = driver.findElement(By.cssSelector("button[class='fhs-btn-login']"))
                .getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBackgroundRGB).asHex().toLowerCase(),"#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys(generateEmail());
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(By.cssSelector("button[class='fhs-btn-login']")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button[class='fhs-btn-login']"))
                .getCssValue("background-color")).asHex(),"#c92127");




    }
    @Test
    public void TC_03_() {

    }

    public String generateEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
