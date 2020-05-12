package org.springframework.inmocasa.ui;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BorrarUsuarioUITest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

  @Test
  public void testBorrarUsuarioOK() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
    driver.findElement(By.id("username")).clear();
    try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
    driver.findElement(By.id("username")).sendKeys("inmocasa");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
    driver.findElement(By.id("password")).sendKeys("inmocasa");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='menu-vertical']/button[2]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/usuario/miPerfil')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/usuario/delete/2')]")).click();
    assertEquals("Log Out", driver.findElement(By.xpath("//button[@type='submit']")).getText());
  }
  
  @Test
  public void testBorrarUsuarioNotOK() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
    driver.findElement(By.id("username")).clear();
    try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
    driver.findElement(By.id("username")).sendKeys("gilmar");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
    driver.findElement(By.id("password")).sendKeys("gilmar");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.get("http://localhost:8080/usuario/delete/3");
    assertEquals("gilmar", driver.findElement(By.xpath("//div[@id='menu-vertical']/button")).getText());
  }

  @AfterEach
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
