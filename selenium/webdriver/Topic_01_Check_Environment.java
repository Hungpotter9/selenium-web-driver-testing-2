package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver; // Import EdgeDriver
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//Add to git as revise
public class Topic_01_Check_Environment {
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
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Url() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
    }

    @Test
    public void TC_02_Logo() {
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
    }

    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }

    // New test method for running on Edge
    @Test
    public void TC_04_Run_On_Edge() {
        // Set Edge driver path (adjust as needed)
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
        } else {
            System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
        }

        WebDriver edgeDriver = new EdgeDriver();  // Create a new instance of EdgeDriver
        edgeDriver.get("https://www.facebook.com/");
        Assert.assertEquals(edgeDriver.getCurrentUrl(), "https://www.facebook.com/");
        edgeDriver.quit();  // Quit EdgeDriver
    }
    @Test
    public void TC_05_Run_On_Chrome() {
        //Set Chromedriver path
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else{
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }
        WebDriver chromeDriver = new ChromeDriver(); // Create a new instance of ChromeDriver
        chromeDriver.get("https://www.facebook.com/");
        Assert.assertEquals(chromeDriver.getCurrentUrl(), "https://www.facebook.com/");
        chromeDriver.quit(); //quit chromeDriver
    }
    //Define TC01, TC02, TC03 to run in Chrome and Edge why can commit and push?
    // Pull ok, now edit and push to Git Hub

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}

