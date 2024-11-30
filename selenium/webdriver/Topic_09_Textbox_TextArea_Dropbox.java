package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Textbox_TextArea_Dropbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String url1 = "https://live.techpanda.org/";
    String url2 = "https://automationfc.github.io/basic-form/index.html";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_LogIn() {
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

        //Handle and verify Alert Java Script
        SleepInSeconds(2);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertTrue(alertText.
                contains("The information you have entered on this page will be sent over an insecure connection and could be read by a third party."));
        alert.accept();

        //Verify
        //SleepInSeconds(3);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='My Dashboard']")));
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My Dashboard']/parent::div/following-sibling::ul/li")).
                getText(), "Thank you for registering with Main Website Store.");
        String contactInfo = driver.findElement(By.
                xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(lastName));
        Assert.assertTrue(contactInfo.contains(emailAddress));
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Samsung Galaxy']")));
        driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Add Your Review']")));
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        // wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//h4[text()='How do you rate this product? ']")));
        driver.findElement(By.cssSelector("input#Quality\\ 1_5")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good quality, great performance");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best choice");
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("AutomationFC");
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
        SleepInSeconds(2);
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.
               xpath("//span[text()='Your review has been accepted for moderation.']"))
                .getText(),"Your review has been accepted for moderation.");

    }

    public String generateEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }
    @Test
    public void TC_02_Textbox(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Login']")));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Full Name']")));
        //To handle error when click submit with reason an element obscures but can't find this element
        SleepInSeconds(3);
        //Khai bao va khoi tao firstname, lastName
        String firstName = "Automation"+ new Random().nextInt(999);
        String lastName = "FC"+ new Random().nextInt(99);
        driver.findElement(By.xpath("//input[@name='firstName']"))
                .sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']"))
                .sendKeys(lastName );
        //khoi tao 1 bien customerID
        String customerID = driver.findElement(By
                .xpath("//label[text() ='Employee Id']/parent::div/following-sibling::div/input"))
                .getDomProperty("value");
        //System.out.println(customerID);
        driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
                .sendKeys("abcdefgh"+customerID);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
                .sendKeys("Abcd@12345");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
                .sendKeys("Abcd@12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
        //Verify firstName, lastName va customer ID

        //Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']/parent::div"))
         //       .getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']"))
                .getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
                .getAttribute("value"), customerID);
        // Step 8 click immigration and do more
        //driver.findElement(By.xpath("//a[text()='Immigration']")).click();





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
