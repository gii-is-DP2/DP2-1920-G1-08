package org.springframework.inmocasa.ui;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class CrearViviendaUITest {
  @LocalServerPort
  private int port;
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("Prueba en la que se añade una vivienda")
  public void testCreateViviendaOk() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("gilmar");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("gilmar");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'Crear Vivienda')]")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div/div/input")).sendKeys("Calle inventada");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).sendKeys("Calle Castillo de Alcalá de Guadaira, Sevilla");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).sendKeys("Heliopolis");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[2]/div/input")).sendKeys("Dirección falsa, Sevilla");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[4]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[4]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[4]/div/input")).sendKeys("60000");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[5]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[5]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[5]/div/input")).sendKeys("60");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[6]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[6]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[6]/div/input")).sendKeys("Septima Planta");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[7]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[7]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[7]/div/input")).sendKeys("Muy buena");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div[2]/div/button")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/viviendas/10')]")).click();
	    driver.findElement(By.xpath("//td")).click();
	    assertEquals("Calle inventada", driver.findElement(By.xpath("//td")).getText());
  }
  
  @Test
  @DisplayName("Prueba en la que se intenta crear una vivienda con atributos obligatorios vacios")
  public void testCreateViviendaNotOk() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("gilmar");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("gilmar");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/viviendas/new')]")).click();
		    driver.findElement(By.xpath("//form[@id='vivienda']/div/div/div/input")).click();
		    driver.findElement(By.xpath("//input[@id='titulo']")).clear();
		    driver.findElement(By.xpath("//input[@id='titulo']")).sendKeys("Calle inventada");
		    driver.findElement(By.xpath("//form[@id='vivienda']/div[2]/div/button")).click();
		    assertEquals("", driver.findElement(By.xpath("//form[@id='vivienda']/div[2]/div/button")).getAttribute("value"));
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

