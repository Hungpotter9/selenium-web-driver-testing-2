package webdriver;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Webbrowser_Practices {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String url1 = "https://live.techpanda.org/";
    String url2 = "https://automationfc.github.io/basic-form/index.html";

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    //Test Browser

    @Test
    public void TC_01_getCurrentURL() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.
                getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.
                getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void TC_02_getTittle() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");


    }
    @Test
    public void TC_03_verifyNavigation() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.
                getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        SleepInSeconds(3);
        Assert.assertEquals(driver.
                getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        SleepInSeconds(3);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_04_getPageSource() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        SleepInSeconds(3);
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }
    // Test Element
    @Test
    public void TC_05_isDisplayed() {
        driver.get(url2);
        if(driver.findElement(By.cssSelector("input#email")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).clear();
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).clear();
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if(driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed()){
            driver.findElement(By.xpath("//label[text()='Under 18']")).click();
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input#email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
    }

    @Test
    public void TC_06_isEnabled() {
        driver.get(url2);
        if (driver.findElement(By.cssSelector("input#email")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.xpath("//label[text()='Under 18']")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.xpath("//label[text()='Interests:']")).isEnabled()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    @Test
    public void TC_07_isSelected() {
        driver.get(url2);
        driver.findElement(By.xpath("//label[text()='Java']")).click();
        if(driver.findElement(By.xpath("//label[text()='Under 18']")).isSelected()){
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        //Step 2: isSelected or not
        driver.findElement(By.xpath("//label[text()='Under 18']")).click();
        if(driver.findElement(By.xpath("//label[text()='Under 18']")).isSelected()){
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
        driver.findElement(By.xpath("//label[text()='Under 18']")).click();

    }

    @Test
    public void TC_08_Register() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("hung@gmail.com");

        //case 1
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isDisplayed());
        SleepInSeconds(2);

        //case 2
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isDisplayed());
        SleepInSeconds(2);
        //case 3
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isDisplayed());
        SleepInSeconds(2);
        //case 4
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#$");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isDisplayed());
        SleepInSeconds(2);
        //case 5
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isDisplayed());
        SleepInSeconds(2);
        //case 6
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("A@bc1357");
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='lowercase-char completed']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='uppercase-char completed']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='number-char completed']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='special-char completed']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='8-char completed']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.
                xpath("//li[@class ='username-check completed']")).isEnabled());
        SleepInSeconds(2);
    }
    //TC check Login
    @Test
    public void TC_09_LoginEmpty() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).
                getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).
                getText(), "This is a required field.");
    }

    @Test
    public void TC_11_LoginInvalidEmail() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234567@1234567");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234567");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).
                getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_12_LoginInvalidPass() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).
                getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_13_LoginInvalidPass() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        SleepInSeconds(3);
        Assert.assertEquals(driver.
                getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/loginPost/");
    }

    @Test
    public void TC_14_Success() {
        driver.get(url1);
        driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title = 'My Account']"))
                .click();
        SleepInSeconds(3);
        // Dang ky tai khoan tu dong
        driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
        SleepInSeconds(2);
        String firstName = "Automation" , lastName = "FC", emailAddress = generateEmail(), password = "123456789";
        String fullName = firstName + " " + lastName;
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#middlename")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.xpath("//span[text()='Register']")).click();

        //Login


        //Verify
        String contactInfo = driver.findElement(By.
                xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));




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
