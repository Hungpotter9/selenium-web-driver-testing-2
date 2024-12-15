package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_WindowandTab {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        }

    @Test
    public void TC_01_Window() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentID = driver.getWindowHandle();

        //Handle goole tab
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchToWindowByID(parentID);
        wait.until(ExpectedConditions.urlToBe("https://www.google.com.vn/"));
        Assert.assertEquals(driver.getTitle(), "Google");

        //Come back basic form
        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        //Handle facebook tab
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Facebook – log in or sign up");
        SleepInSeconds(3);
        //wait.until(ExpectedConditions.urlToBe("https://www.facebook.com/"));
        //Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");

        //come back basic form
        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");

        //handle Tiki
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        SleepInSeconds(3);
        //Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        switchToWindowByTitle("Selenium WebDriver");
        Set<String> allID = driver.getWindowHandles();
        for(String id : allID){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        switchToWindowByTitle("Selenium WebDriver");
        Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");
    }

    @Test
    public void TC_02_Window() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        //switchToWindowByTitle("Mobile");
        driver.findElement(By
                .xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']"))
                        .click();
        //switchToWindowByTitle("Shopping Cart");
        Assert.assertTrue(driver.findElement(By
                .xpath("//span[text() = 'The product Sony Xperia has been added to comparison list.']"))
                .isDisplayed());
        driver.findElement(By
                .xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']"))
                .click();
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();
        switchToWindowByTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        SleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By
                .xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

    }
    @Test
    public void TC_03_Window() {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//span[@class='tb' and text()='Đăng nhập']")).click();
        switchToWindowByTitle("Login");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        Assert.assertEquals(driver.findElements(By
                .xpath("//span[text()='This field is required']")).size(),2);
        driver.close();
        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("family");
        driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='family']")).isDisplayed());
    }
    @Test
    public void TC_04_Window() {
        driver.get("https://courses.dce.harvard.edu/");
        driver.findElement(By.xpath("//a[@data-action='login']")).click();
        SleepInSeconds(5);
        switchToWindowByID(driver.getWindowHandle());
        //switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Harvard DCE']")));
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Harvard DCE']")).isDisplayed());
        driver.close();
        switchToWindowByTitle("DCE Course Search");
        Assert.assertTrue(driver.findElement(By.cssSelector("div.sam-wait")).isDisplayed());

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
    public void switchToWindowByID(String expectedID){
        Set<String> allID = driver.getWindowHandles();
        for (String id: allID){
            if(!id.equals(expectedID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }
    public void switchToWindowByTitle(String expectedTitle){
        //Lay het tat ca ID cua cac window
        Set<String> allID = driver.getWindowHandles();
        for(String id: allID){
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            SleepInSeconds(1);
            if(actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }
}
