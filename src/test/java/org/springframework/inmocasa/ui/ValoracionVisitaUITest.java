package org.springframework.inmocasa.ui;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ValoracionVisitaUITest {
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

  //HU-13: Como cliente quiero añadir una puntuación y comentarios a las visitas que he 
  //realizado para valorar mi experiencia y compartirla con otros usuarios.
  @Test
  public void testValoracionVisita() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("bravo9");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("bravo9");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/usuario/misVisitas')]")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/valoracion/3/new')]")).click();
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div/div/input")).sendKeys("4");
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div[2]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div[2]/div/input")).sendKeys("Muy buena");
	    driver.findElement(By.xpath("//form[@id='valoracion']/div/div[2]/div/input")).click();
	    driver.findElement(By.id("valoracion")).submit();
	    assertEquals("Valoracion guardada correctamente", driver.findElement(By.xpath("//p")).getText());
  }
  
  //HU-13: En este caso intenta realizar una valoración en una visita que ya ha sido valorada, lo cual no es válido.
  @Test
  public void testValoracionVisitaNotOk() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
	driver.findElement(By.id("password")).clear();
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
	driver.findElement(By.id("password")).sendKeys("bravo9");
	driver.findElement(By.id("username")).clear();
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
	}
	driver.findElement(By.id("username")).sendKeys("bravo9");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/usuario/misVisitas')]")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Valorar visita')])[2]")).click();
    assertEquals("Ya ha realizado una valoración a esta vivienda.", driver.findElement(By.xpath("//p")).getText());
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
