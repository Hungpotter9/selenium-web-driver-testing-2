package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Topic_15_Actions {
    WebDriver driver;
    Actions actions;
    WebDriverWait Implicitwait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        //Neu dung cac methods trong Action, thi ko the dung chuot va ban phim duoc
        // nhung ham thuong dung trong actions: scrollToElement(),
        //perform(): bat ky 1 ham nao muon chay thi deu phai co ham perform() o cuoi cung
        Implicitwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTexbox = driver.findElement(By.xpath("//input[@id='age']"));
        actions.moveToElement(ageTexbox).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'ui-tooltip-content']")));
        Assert.assertEquals(driver.findElement(By
                .xpath("//div[@class = 'ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");

    }
    @Test
    public void TC_02_hover() {
        driver.get("https://www.fahasa.com/");
        //Alert alert = Implicitwait.until(ExpectedConditions.alertIsPresent());
        //alert.dismiss();
        WebElement holdButton = driver.findElement(By.xpath("//span[@class='icon_menu']"));
        actions.moveToElement(holdButton).perform();
        //SleepInSeconds(2);
        actions.moveToElement(driver.findElement(By
                .xpath("//a[@title='Sách Trong Nước']"))).perform();
        SleepInSeconds(2);
        //element close iframe: //div[@class='brz-popup2__close']
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
                //.xpath("//li[@class='parent dropdown aligned-left active']//span[text()='Sách Trong Nước']")));
        actions.click(driver.findElement(By
                .xpath("//div[@class='fhs_column_stretch']//span[text()='TIỂU SỬ - HỒI KÝ']"))).perform();
        //SleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By
                .xpath("//strong[text()='Tiểu Sử Hồi Ký']")).isDisplayed());

    }
    @Test
    public void TC_02_hover2() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        actions.moveToElement(driver
                .findElement(By.xpath("//span[@class='menu-title' and text()='FOREIGN BOOKS']"))).perform();
        SleepInSeconds(2);
        actions.click(driver.
                findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Japanese Books']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Japanese Books']")).isDisplayed());

    }
    @Test
    public void TC_03_clickandhold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
        Assert.assertEquals(allNumber.size(), 20);
        actions.clickAndHold(allNumber.get(0))
                .pause(2000)
                .moveToElement(allNumber.get(14))
                .pause(2000)
                .release().perform();
        List<WebElement> allNumberSelected = driver.findElements(By.
                xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(allNumberSelected.size(),12);
        //So sanh xem gia tri duoc select co dung ko
        /*
        List<String> allNumberExpected = new ArrayList<>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");
        allNumberExpected.add("5");
        allNumberExpected.add("6");
        allNumberExpected.add("7");
        allNumberExpected.add("9");
        allNumberExpected.add("10");
        allNumberExpected.add("11");
        allNumberExpected.add("13");
        allNumberExpected.add("14");
        allNumberExpected.add("15");

         */
        String[] allNumberTextArray = {"1", "2","3", "5", "6", "7","9", "10", "11", "13", "14", "15"};
        List<String> allNumberExpected = Arrays.asList(allNumberTextArray);
        List<String> allNumberActual = new ArrayList<>();
        for (WebElement element: allNumberSelected){
            allNumberActual.add(element.getText());
        }
        Assert.assertEquals(allNumberActual,allNumberExpected);

    }
    @Test
    public void TC_03_clickAndHoldCombineCtrl() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
        Assert.assertEquals(allNumber.size(), 20);
        //Select 1 -> 11, nha chuot, hold ctrl roi select 13-> 20, nha chuot
        actions.clickAndHold(allNumber.get(0))
                .moveToElement(allNumber.get(10))
                .release().perform();
        actions.keyDown(Keys.CONTROL)
                .clickAndHold(allNumber.get(12))
                .moveToElement(allNumber.get(19))
                .release().keyUp(Keys.CONTROL).perform();
        List<WebElement> allNumnberSelected = driver.findElements(By
                 .xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
        String[] allNumberExpectedArray = {"1", "2", "3", "5", "6", "7", "9"
                , "10", "11", "13", "14", "15", "16","17", "18", "19", "20"};
        List<String> allNumberExpected = Arrays.asList(allNumberExpectedArray);
        List<String> allNumberActual = new ArrayList<>();
        for(WebElement element: allNumnberSelected){
            allNumberActual.add(element.getText());
        }
        Assert.assertEquals(allNumberActual,allNumberExpected);

    }

    @Test
    public void TC_04_clickRandom() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
        Assert.assertEquals(allNumber.size(), 20);
        //Make sure the randomIndex is under all element

        // Check if items are available
        Random random = new Random();
        int randomIndex = random.nextInt(allNumber.size());
        // Select the random item
        actions.keyDown(Keys.CONTROL).perform();
        for(int index = 0; index < 4; index++){
            WebElement randomItem = allNumber.get(random.nextInt(allNumber.size()));
            //SleepInSeconds(1);
            actions.click(randomItem).perform();
        }
        actions.keyUp(Keys.CONTROL).perform();
        Assert.assertEquals(driver
                .findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);

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
