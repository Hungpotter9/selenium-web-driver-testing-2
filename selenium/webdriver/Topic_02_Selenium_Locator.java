package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
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
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        System.out.println(driver.findElement(By.id("FirstName")));
        driver.findElement(By.id("FirstName")).sendKeys("Harry Nguyen Ngoc Hung");

    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));

    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("LastName"));

    }

    @Test
    public void TC_04_TagName() {
        System.out.println(driver.findElement(By.tagName("input")));

    }

    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Sitemap"));

    }

    @Test
    public void TC_06_Patial_LinkText() {
        driver.findElement(By.partialLinkText("Shipping &"));
    }

    @Test
    public void TC_07_Css() {
        driver.findElement(By.cssSelector("input[id=\"FirstName\"]"));
        //driver.findElement(By.cssSelector("#FirstName")).click();
        //driver.findElement((By.cssSelector("input#FirstName"))).click();
        //class
        driver.findElement(By.cssSelector("div[class=\"page-title\"]"));
        driver.findElement((By.cssSelector(".page-title")));
        //Name
        driver.findElement(By.cssSelector("input[name='LastName']"));
        //link
        driver.findElement(By.cssSelector("a[href='/sitemap']"));
        //partial link
        driver.findElement(By.cssSelector("a[href*='/sitemap']"));

    }

    @Test
    public void TC_08_Xpath() {
        driver.findElement(By.xpath("//input[@id=\"FirstName\"]"));
        //class
        driver.findElement(By.xpath("//div[@class=\"page-title\"]"));
        //Name
        driver.findElement(By.xpath("//input[@name='LastName']"));
        //link
        driver.findElement(By.xpath("//a[@href='/sitemap']"));
        //partial link
        //driver.findElement(By.xpath("//a[@href*='/sitemap']"));

    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
