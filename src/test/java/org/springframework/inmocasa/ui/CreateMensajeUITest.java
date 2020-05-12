package org.springframework.inmocasa.ui;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateMensajeUITest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() throws Exception {
	  String pathToGeckoDriver = "C:\\Users\\Santiago\\Downloads";
		System.setProperty("webdriver.chrome.driver", pathToGeckoDriver + "\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCrearMensajeOK() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.id("navbarDropdown")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/mensajes/new')]")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).clear();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).sendKeys("Hola");
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).clear();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).sendKeys("Esto es una prueba");
    new Select(driver.findElement(By.xpath("//form[@id='mensaje']/div/div[3]/select"))).selectByVisibleText("Alejandra");
    driver.findElement(By.xpath("//select[@id='client']/option[4]")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div[2]/div/button/h4")).click();
  }

  @Test
  public void testCreateMensajeNotOk() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.id("navbarDropdown")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/mensajes/new')]")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).clear();
    driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).sendKeys("Hola");
    new Select(driver.findElement(By.xpath("//form[@id='mensaje']/div/div[3]/select"))).selectByVisibleText("Alejandra");
    driver.findElement(By.xpath("//select[@id='client']/option[4]")).click();
    driver.findElement(By.xpath("//form[@id='mensaje']/div[2]/div/button/h4")).click();
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
