package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Xpath_Css {
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
    }

    @Test
    public void TC_01_Register_Empty_data() {
        //Access the website: https://alada.vn/tai-khoan/dang-ky.html
        // Click "Dang ky"
        //Check error message
        //Actions
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.cssSelector("input#txtEmail")).clear();
        driver.findElement(By.cssSelector("input#txtCEmail")).clear();
        driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ'and@type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']"))
                .getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']"))
                .getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']"))
                .getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']"))
                .getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']"))
                .getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']"))
                .getText(),"Vui lòng nhập số điện thoại.");

        //String error_message= driver.findElement(By.xpath("//div[@class='form frmRegister']")).getText();
        //System.out.println(error_message);
    }
    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("Harry Nguyen");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("123@123@123");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("123@123@123");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");

        //Verify TC
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']"))
                .getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']"))
                .getText(),"Email nhập lại không đúng");

    }
    @Test
    public void TC_03_Register_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }
    @Test
    public void TC_04_Register_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }
    @Test
    public void TC_05_Register_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }
    @Test
    public void TC_06_Register_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
