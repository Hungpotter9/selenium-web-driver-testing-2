package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic_21_javaScriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        //Ep kieu tuong minh: ly do webDriver va JavascriptExecutor cung la interface, khi cung loai interface thi can
        //ep tuong minh
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JavaExecutor() {
        executeForBrowser("window.location = 'https://live.techpanda.org/'");
        //Sleep de cac ham get document kip thuc thi
        sleepInSecond(3);

        String textPandaDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(textPandaDomain, "live.techpanda.org");

        String homeURL = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(homeURL, "https://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        sleepInSecond(3);
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        sleepInSecond(3);
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        scrollToElementOnTop("//a[text()='Customer Service']");
        hightlightElement("//a[text()='Customer Service']");
        sleepInSecond(3);
        clickToElementByJS("//a[text()='Customer Service']");
        String customerServiceTitle = (String) executeForBrowser("return document.title;");
        sleepInSecond(3);
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        scrollToElementOnTop("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']",generateEmail());
        hightlightElement("//span[text()='Subscribe']");
        sleepInSecond(3);
        clickToElementByJS("//span[text()='Subscribe']");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        sleepInSecond(3);
        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSecond(3);
        String facebookDomain = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(facebookDomain, "www.facebook.com");
    }
    @Test
    public void TC_02_() {

    }
    @Test
    public void TC_03_verifyHTML5Message() {
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
        String ubuntuValidationMessage = getElementValidationMessage("//div[contains(@id,'yui_3') and @class='p-form-validation email-input']//input");
        Assert.assertEquals(ubuntuValidationMessage,"Please fill out this field.");

        driver.switchTo().newWindow(WindowType.TAB).get("https://warranty.rode.com/login");
        sleepInSecond(5);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String rodeValidationMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(rodeValidationMessage,"Please enter an email address.");
    }

    @Test
    public void TC_04_creatAccount() {
        executeForBrowser("window.location='https://live.techpanda.org/';");

    }


    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String generateEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByTitle(String expectedTitle){
        //Lay het tat ca ID cua cac window
        Set<String> allID = driver.getWindowHandles();
        for(String id: allID){
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            sleepInSecond(1);
            if(actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    //Common JavaScriptExecution Function: Please take note to get it
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
