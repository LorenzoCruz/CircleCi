package com.example.TestLogin;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class TestCredencialesCorrectas {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	  WebDriverManager.chromedriver().setup();
    //System.setProperty("webdriver.chrome.driver", "");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void TestCredencialesIncorrectas() throws Exception {
	    driver.get("https://es.uadyvirtual.uady.mx/login/index.php");
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("a14003176");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("abcder");
	    driver.findElement(By.id("loginbtn")).click();
	    String textoejec=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/section/div/div[2]/div/div/div/div/div[1]/div")).getText();
	    String textoesperado="Datos erróneos. Por favor, inténtelo otra vez.";
	    assertThat(textoesperado,is(textoejec));
	  }
  @Test
  public void TestCredencialesCorrectas() throws Exception {
    driver.get("https://es.uadyvirtual.uady.mx/login/index.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("a14003176");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
	  //contraseña eliminada
    driver.findElement(By.id("password")).sendKeys("");
    driver.findElement(By.id("loginbtn")).click();
    
    String textoejec=driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[2]/div/div/div/div/div/a/span/span[1]")).getText();
    String textoesperado="LORENZO DE JESUS CRUZ HUCHIM";
    assertThat(textoesperado,is(textoejec));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
