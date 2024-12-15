package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_21_uploadFile {
    WebDriver driver;
    //Khai bao bien de phan biet khi nao dung window thi \\ con MAC thi /
    String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\": "/";
    String projectPath = System.getProperty("user.dir");
    String fileName1 = "Css cheat sheet.jpg";
    String fileName1Path = projectPath + character+ "upLoadFiles" + character + fileName1;
    String fileName2 = "Xpath-cheat-sheet.png";
    String fileName2Path = projectPath + character+ "upLoadFiles" + character + fileName2;

    //Hoac dung file.seperator
    //String fileName1Path = projectPath + File.separator + "upLoadFiles" + File.separator + fileName1;
    @BeforeClass
    public void beforeClass() {
        //driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_singleFile() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(fileName1Path);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName1+ "']")).isDisplayed());

        driver.findElement(By.xpath("//span[text() = 'Start']")).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By
                .xpath("//p[@class='name']//a[@title='" +fileName1+ "']")).isDisplayed());
    }
    @Test
    public void TC_02_multipleFiles() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //voi upload multiple file: key la add them "\n" cho nhieu files
        driver.findElement(By.xpath("//input[@type='file']"))
                .sendKeys(fileName1Path + "\n"+ fileName2Path);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName2+ "']")).isDisplayed());

        List<WebElement> allElements = driver.findElements(By.xpath("//span[text()='Start']"));
        for(WebElement element: allElements){
            element.click();
            sleepInSecond(3);
        }

        Assert.assertTrue(driver.findElement(By
                .xpath("//p[@class='name']//a[@title='" +fileName1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By
                .xpath("//p[@class='name']//a[@title='" +fileName2+ "']")).isDisplayed());

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

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
