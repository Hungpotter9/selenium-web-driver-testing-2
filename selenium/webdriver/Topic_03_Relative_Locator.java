package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
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
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");

        //Login button
        By LoginButton = By.cssSelector("button.login-button");
        WebElement LoginButtonElement = driver.findElement(By.cssSelector("button.login-button"));
        //Rememnber Me checknbox
        By RememberCheckBox = By.cssSelector("input[type='checkbox']");
        WebElement RememberCheckBoxElement = driver.findElement(By.cssSelector("input[type='checkbox']"));
        WebElement RememberMeElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(LoginButton)
                .toRightOf(RememberCheckBoxElement));
        System.out.println(RememberMeElement.getText());
        //List <WebElement> ALlLink = WebElement RelativeLocator.with(By.linkText("a")).above(LoginButtonElement);



    }
    @Test
    public void TC_02_() {

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
