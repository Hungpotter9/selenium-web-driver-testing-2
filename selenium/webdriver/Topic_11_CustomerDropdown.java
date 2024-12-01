package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_CustomerDropdown {
    WebDriver driver;
    String firstName = "Automation" + new Random().nextInt(99), lastName = "FC" + new Random().nextInt(99), userEmail = generateEmail(), passWord = "12345678";
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //driver = new ChromeDriver();
        //driver = new EdgeDriver();
        //Wait ngam dinh, ko ro rang cho 1 trang thai nao cua element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();



    }

    @Test
    public void TC_01_Register() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //1. Click vao 1 the de cho no xo het cac item ben trong dropdown
        driver.findElement(By.cssSelector("span#number-button")).click();
        //2. No se show ra:
        //  2.1. show ra chua het tat ca cac item
        //2.2. show ra nhung chi chua 1 phan va dang load them
        // wait cho tat ca cac item show ra trong drop down
        //3. Click:
        //  3.1. Neu nhu item can chon hien thi thi click vao
        //3.2. Ne nhu itme can chon nam ben duoi thi 1 so truong hop can scroll xuong de hien thi them roi moi chon (E.g: Angular/React/VueJS..)
        //4.Truoc khi click can kiem tra neu nhu text cua item bang vs item can chon thi click vao
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.entry-title")));
        //wait explicitwait: wait tuong minh, cho trang thai cu the cua element
        //cho cac trang thai: visible/invisible/present/number/clickable...
        //method cua wait: neu thoa man dieu kien => action, neu chua thoa man thi wait tiep theo thoi gian roi lai lap lai
        // method wait luon phai khoi tao sau khoi tao driver
        //visibilityOfAllElementsLocatedBy : bat buoc co tren html va bat buoc phai visible
        //explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));
        // presenceOfAllElementsLocatedBy ==> bat buoc co tren html, co or ko co tren UI deu duoc
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
        //lay cssSelector nay : ul#number-menu div vi 1. no representative cho 19 option va 2. trong tag div co text cua tung option
        List<WebElement> AllItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        for (WebElement item : AllItems) {
            // Neu item kieu reference type (E.g: WebElement or String thi phai dung) .equal. Con neu la primitive type
            //Dung ==/!= la ok
            if (item.getText().equals("8")) {
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_02_jQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        SelectDropDown("span#speed-button", "//ul[@id='speed-menu']//div", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button")).getText(), "Medium");
        //Refresh site
        driver.navigate().refresh();
        SelectDropDown("span#speed-button", "//ul[@id='speed-menu']//div", "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button")).getText(), "Slower");
        driver.navigate().refresh();
        SelectDropDown("span#speed-button", "//ul[@id='speed-menu']//div", "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button")).getText(), "Faster");

    }

    @Test
    public void TC_03_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectDropDown("div#root", "//div[@style='pointer-events:all']", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Elliot Fu");
        //Refresh site
        driver.navigate().refresh();
        SelectDropDown("div#root", "//div[@style='pointer-events:all']", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Stevie Feliciano");
        driver.navigate().refresh();
        SelectDropDown("div#root", "//div[@style='pointer-events:all']", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Matt");

    }
    @Test
    public void TC_04_VueJs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        SelectDropDown("div.btn-group", "//ul[@class='dropdown-menu']//a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group")).getText(), "First Option");
        //Refresh site
        driver.navigate().refresh();
        SelectDropDown("div.btn-group", "//ul[@class='dropdown-menu']//a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group")).getText(), "Second Option");
        driver.navigate().refresh();
        SelectDropDown("div.btn-group", "//ul[@class='dropdown-menu']//a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group")).getText(), "Third Option");

    }
    @Test
    public void TC_05_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        SelectDropDownEditable("input.search", "//div[@style='pointer-events:all']", "Andorra");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Andorra");
        //Refresh site
        driver.navigate().refresh();
        SelectDropDownEditable("input.search", "//div[@style='pointer-events:all']", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

    }

    public String generateEmail() {
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

    public void SelectDropDown(String parentCss, String childXpath, String ItemTexExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(ItemTexExpected);

        //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentCss)));
        List<WebElement> AllItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : AllItems) {
            if (item.getText().equals(ItemTexExpected)) {
                item.click();
                break;
            }
        }
    }

    public void SelectDropDownEditable(String parentCss, String childXpath, String ItemTexExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentCss)));
        List<WebElement> AllItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item : AllItems) {
            if (item.getText().equals(ItemTexExpected)) {
                item.click();
                break;
            }
        }
    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
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
