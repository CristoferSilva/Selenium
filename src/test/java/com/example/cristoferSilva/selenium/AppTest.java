package com.example.cristoferSilva.selenium;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.*;

public class AppTest 
{
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    @BeforeClass
    public static void beforeClass()
    {
    	System.out.println("[BEFORECLASS]");
    	System.setProperty("webdriver.chrome.driver",
                "seleniumDrivers/chromedriver.exe");
        assertTrue( true );
    }
    @Before
    public void setUp() {
      System.out.println("[SETUP]");
      driver = new ChromeDriver();
      js = (JavascriptExecutor) driver;
      vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
      System.out.println("[TEARDOWN]");
      driver.quit();
    }
    
    @Test
    public void openTudoCelularSite() {
    	driver.get("https://www.tudocelular.com/");
    	Assert.assertEquals("https://www.tudocelular.com/", driver.getCurrentUrl());
    }
    
    @Test
    public void openAndroidNews() {
      driver.get("https://www.tudocelular.com/");
      driver.manage().window().setSize(new Dimension(1050, 708));
      driver.findElement(By.cssSelector("#link_android span")).click();
      Assert.assertEquals("https://www.tudocelular.com/android/", driver.getCurrentUrl());
    }
    
    @Test
    public void sendAndroidKeyInSearch() {
      driver.get("https://www.tudocelular.com/");
      driver.manage().window().setSize(new Dimension(1050, 708));
      driver.findElement(By.id("topsearch-text")).click();
      js.executeScript("window.scrollTo(0,0)");
      driver.findElement(By.id("topsearch-text")).sendKeys("android");
      driver.findElement(By.id("topsearch-text")).sendKeys(Keys.ENTER);
      Assert.assertEquals("https://www.tudocelular.com/?sName=android", driver.getCurrentUrl());
    }
    
    @Test
    public void clickOnRegisterBtn() {
      driver.get("https://www.tudocelular.com/");
      driver.manage().window().setSize(new Dimension(1050, 708));
      {
        WebElement element = driver.findElement(By.id("iclogin"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
        driver.findElement(By.id("register")).click();
        Assert.assertEquals("https://www.tudocelular.com/forum/register.php", driver.getCurrentUrl());
      }
    }
}

