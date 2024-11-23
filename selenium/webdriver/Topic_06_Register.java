package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String userName, password;
    String myEmail = "hung.ngoc.11556567@gmail.com";
    String urlCreateuserName = "https://demo.guru99.com/";
    String urlLogin = "https://www.demo.guru99.com/V4/";

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

    }

    @Test
    public void TC_01_Register() {
        // Access https://www.demo.guru99.com/
        driver.get(urlCreateuserName);
        //Input any email
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(myEmail);
        //Click submit button
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        //Get user, password, save in a value (username = ...)
        userName = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }
    @Test
    public void TC_02_Login() {
        //Access log in page https://www.demo.guru99.com/V4/
        driver.get(urlLogin);
        // Enter userName, password
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

    }
    @Test
    public void TC_03_() {

    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
