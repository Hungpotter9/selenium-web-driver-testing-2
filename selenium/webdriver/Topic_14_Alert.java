package webdriver;

import org.bouncycastle.util.encoders.Base64;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
//import org.openqa.selenium.devtools.v133.network.Network; // Adjust version to match your Selenium version
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

//import static jdk.internal.org.jline.utils.Colors.s;

public class Topic_14_Alert {
    WebDriver driver;
    WebDriverWait Implicitwait;
    //Lay duong dan tuong doi trong prj, cau lenh nay se lay ra duoc den:
    // E:\Automation Testing\Selenium_Training\selenium-web-driver-testNG
    String prjlocation = System.getProperty("user.dir");


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        Implicitwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //important command
        //Alert alert = driver.switchTo().alert();
        //Ngoai ra, co the dung them ham wait alert de doi cho alert xuat hien
        //Ham nay nen duoc su dung
        Alert alert = Implicitwait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        //Khi accept or cancel thi alert se mat
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']"))
                .getText(),"You clicked an alert successfully");
        // Cac ham trong alert
        // cancel alert
        //void dismiss() ==> Cancel alert
        //void accept(); ==> accept alert
        //String getText(); ==> nhap text vao alert
        //void sendKeys(String keysToSend);


    }
    @Test
    public void TC_02_confirm_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = Implicitwait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']"))
                .getText(),"You clicked: Cancel");

    }
    @Test
    public void TC_03_promt_alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = Implicitwait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys("AutomationFC");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']"))
                .getText(),"You entered: AutomationFC");

    }

    @Test
    public void TC_04_authentication_alert() throws IOException {
        //ko su dung thu vien cua alert duoc do lien quan bao mat
        //Selenium 4 co the dung duoc Chrome devtool protocol (CDP): Chrome/Edge (Chromium)
        //FIrefox ko dung duoc CDPadmin admin

        // de chay TC nay thi moi lan thu lai thi xoa catch Ctrl+Shif+Delete
        //To hop phim nay cung co the support xoa Cookies neu da accept
        //Co cac cach test TC nay
        //Cach 1: nhap thang user/password vao link site
        //vi du user: admin,password: admin ==> https://admin:admin@the-internet.herokuapp.com/basic_auth
        //driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.getPageSource().contains("Congratulations! You must have the proper credentials."));

        //Cach 2: chi chay tren windows, dung thu vien ben ngoai autoIT, nhung dung cho tung loai browser
        //autoIT co the dung duoc cho web app, desktop app
        //Chay doan code autoIT de tro alert xuat hien
        // co the copy patch cua autoIT script roi paste vao
        //Runtime.getRuntime().exec(new String[]{"E:\\Automation Testing\\Selenium_Training\\selenium-web-driver-testNG\\AutoIT\\authen_firefox.exe", "admin", "admin"});
        //Hoac co the dung duong dan tuong doi bang bien prjlocation de tien hon (cach nay co the dung o nhieu PC khac
        //khi run code tren cac PC khac nhau.
        //khi add exec() thi can add them throws() IOException, cach add: de chuot o exec ==> add exception...
        Runtime.getRuntime().exec(new String[]{prjlocation+ "\\AutoIT\\authen_firefox.exe", "admin", "admin"});
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        SleepInSeconds(5);
        Assert.assertTrue(driver.
                findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @Test
    public void TC_05_authentication_alert_throw_via_URL() {
        //ko su dung thu vien cua alert duoc do lien quan bao mat
        //Selenium 4 co the dung duoc Chrome devtool protocol (CDP): Chrome/Edge (Chromium)
        //FIrefox ko dung duoc CDPadmin admin
        // de chay TC nay thi moi lan thu lai thi xoa catch Ctrl+Shif+Delete
        //To hop phim nay cung co the support xoa Cookies neu da accept
        //Co cac cach test TC nay
        //Cach 1: nhap thang user/password vao link site
        //vi du user: admin,password: admin ==> https://admin:admin@the-internet.herokuapp.com/basic_auth
        //String user = "admin", password = "admin";
        driver.get("https://the-internet.herokuapp.com");
        String authenLinkURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenURL(authenLinkURL, "admin", "admin"));
        Assert.assertTrue(driver.getPageSource().contains("Congratulations! You must have the proper credentials."));
    }

    /*
    @Test
    public void TC_06_authentication_alert_Bypass_selenium4() {
        //Selenium 4 co the dung duoc Chrome devtool protocol (CDP): Chrome/Edge (Chromium)
        //FIrefox ko dung duoc CDPadmin admin
        //Get devtool object
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        //Start new session
        devTools.createSession();
        //Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        //Encode username, password
        Map<String, Object> headers = new HashMap<String, Object>();
        //String basicAuthen = "Basic" + new String(new Base64().encode(String.format(%s:%s, "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);
        //Set to header
        devTools.send(org.openqa.selenium.devtools.v85.network.Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

    }

     */

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAuthenURL(String Url, String username, String password){
        String[] authenURL = Url.split("//");
        return authenURL[0]+"//"+username +":"+ password + "@"+ authenURL[1];

    }
}
