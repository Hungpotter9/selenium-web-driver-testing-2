package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//Add to git as revise
public class Topic_05_Run_Multiple_Browser {
    WebDriver driver;
    String test_Url = "https://www.facebook.com/";
    @Test
    public void TC_01_Firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(test_Url);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_02_Chrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(test_Url);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_03_Edge() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(test_Url);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        driver.quit();
    }

}

