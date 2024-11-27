package javaTester;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Test02_Assert {
    @Test
    public void VerifyTestNG(){

        WebDriver driver;
        driver = new EdgeDriver(); //**
        driver.get("https://www.facebook.com/"); //**

        //Lay ra id cua window, dung de handle Window/Tab
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Cookies - Framework
        //driver.manage().addCookie();

        //Get log trong dev tool
        driver.manage().logs().get(LogType.DRIVER);

        //time out, apply cho findElement or findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Page load time out: tro cho page load done
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //Dung truoc khi dung thu vien Java Script Executer
        //Inject 1 doan JS vao trong brower or element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        //selenium ver 4.0 moi co ham getScripttime out
        driver.manage().timeouts().getScriptTimeout();

        //Chay full man hinh
        driver.manage().window().maximize(); //**
        driver.manage().window().fullscreen();

        // test GUI
        // set size, dung de test reponsive (resolution cua man hinh)
        driver.manage().window().setSize(new Dimension(500, 700));
        // lay gia tri dimension or position
        driver.manage().window().getSize();
        //set cho browser open vs vi tri nao
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Naviator website
        driver.navigate().back();
        driver.navigate().forward();
        // navigate mo trang page moi, same vs get, nhung .to() ho tro history con get() thi ko
        driver.navigate().to("");
        //driver.navigate().to(new URL(""));

        // Thao tac vs cac loai: Alert/window (Tab)/Frame (iFrame)
        driver.switchTo().alert();
        driver.switchTo().window("tesst");
        driver.switchTo().frame("anfapa");
        driver.switchTo().defaultContent();



        // Nhan du lieu vao kieu mong muon la dung
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));
        Assert.assertFalse(driver.getPageSource().contains("Harry Nguyen Ngoc Hung"));
        driver.quit(); //**

        // Ham Tim Elememt
        driver.findElement(By.id("")).getText();//**
        //Tra ve du lieu la WebElement
        WebElement element = driver.findElement(By.id(""));
        //Ham clear du lieu, dung cho cac truong Dropdown/textbox/texArea, va thuong dung truoc .sendkey()
        //cac ham tuong tac len element deu la void : sendkey(), clear()...
        driver.findElement(By.xpath("")).clear();//*
        driver.findElement(By.cssSelector("")).sendKeys();//**
        List<WebElement> elements = driver.findElements(By.cssSelector(""));

        //Dung de verify 1 checkbox/radio/dropbox(default) da duoc chon hay chua
        driver.findElement(By.id("")).isSelected();

        //dropdown (default/custom)
        Select select = new Select(driver.findElement(By.xpath("")));

        //Dung de verify bat ky element co hien thi hay ko
        Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());//**

        // Cac method de test GUI
        //lay attribute cua 1 element
        driver.findElement(By.xpath("")).getAttribute("title");
        //Lay accessible name trong tab accessiblity
        driver.findElement(By.xpath("")).getAccessibleName();
        //Lay property trong tab property
        driver.findElement(By.xpath("")).getDomProperty("property");
        //lay CSS element trong tab styles
        driver.findElement(By.xpath("")).getCssValue("font-style");
        //lay location cua element (truc tung, truc hoanh, tinh tu goc tren cung ben phai
        Point location= driver.findElement(By.xpath("")).getLocation();
        location.getX();
        location.getY();
        // location + size
        Rectangle nameTextbox = driver.findElement(By.cssSelector("")).getRect();

        Dimension dimension = driver.findElement(By.cssSelector("")).getSize();

        //lay Shadow Element, vi du trong trang shopee co shadow, dung method nay de get
        driver.findElement(By.cssSelector("")).getShadowRoot();

        // tu id, xpath. name, class co the truy nguoc lai tagname trong html
        driver.findElement(By.cssSelector("")).getTagName();

        //get screenshot: chup hinh bi fail
        //BYTE
        //FILE: luu thanh 1 hinh trong o cung duoi dang .png, .jpeg..
        //BASE64: hinh duoi dang text
        driver.findElement(By.cssSelector("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.xpath("")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.cssSelector("")).getScreenshotAs(OutputType.BYTES);

        // Ham submit: dung cho form or nam trong the form
        driver.findElement(By.cssSelector("")).submit();





    }
}
