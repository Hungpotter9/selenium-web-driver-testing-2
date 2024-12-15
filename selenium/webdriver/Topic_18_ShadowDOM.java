package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_18_ShadowDOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ShadowDOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        // Khai bao 1 element moi de dung trong shadowDOM dung SeachContext va getShadowRoot()
        SearchContext shadowRootElement = shadowHostElement.getShadowRoot();
        //Gio co the thao tac nhu vs driver.
        Assert.assertEquals(shadowRootElement.findElement(By.cssSelector("span[class='info']")).getText(), "some text");
        WebElement shadowNestHost = shadowRootElement.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowNestElement = shadowNestHost.getShadowRoot();
        Assert.assertEquals(shadowNestElement.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }
    @Test
    public void TC_02_ShadowNotInDOM() {
        driver.get("https://shopee.vn/");
        SleepInSeconds(5);
        WebElement shadowHost = driver.findElement(By.cssSelector("div.home-page>shopee-banner-popup-stateful"));
        SearchContext shadowElement = shadowHost.getShadowRoot();
        if(shadowElement.findElements(By.cssSelector("div.home-popup__content>shopee-banner-simple")).size()>0
                && shadowElement.findElements(By.cssSelector("div.home-popup__content>shopee-banner-simple")).get(0).isDisplayed()){
            SearchContext shadowNestElement = shadowElement.findElement(By.cssSelector("div.home-popup__content>shopee-banner-simple"));
            shadowNestElement.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        }
        SleepInSeconds(2);
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 15 pro");
        Assert.assertEquals(driver.findElement(By.cssSelector("span.shopee-search-result-header__text-highlight")).getText(), "Kết quả tìm kiếm cho từ khoá");
    }
    @Test
    public void TC_03_shadowDOM() {
        driver.get("https://books-pwakit.appspot.com/");
        SearchContext shadowHost = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();
        SearchContext shadowNest = shadowHost.findElement(By.cssSelector("app-header[effects='waterfall']")).getShadowRoot();
        SearchContext shadowNest2 = shadowNest.findElement(By.cssSelector("app-toolbar[class='toolbar-bottom']")).getShadowRoot();
        shadowNest2.findElement(By.cssSelector("book-input-decorator")).sendKeys("Harry Potter");
        //driver.switchTo().frame(driver.findElement(By.xpath("")));
        //driver.switchTo().defaultContent();


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
}
