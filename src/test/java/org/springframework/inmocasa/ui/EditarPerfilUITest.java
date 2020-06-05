package org.springframework.inmocasa.ui;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class EditarPerfilUITest {
	
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
	@Order(1)
	@DisplayName("Se edita el perfil correctamente")
	public void testEditarPerfilOk() throws Exception {
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
		  driver.findElement(By.id("menu-vertical")).click();
		    driver.findElement(By.xpath("//a[contains(@href, '/propietarios/miPerfil')]")).click();
		    assertEquals("Sanchez", driver.findElement(By.xpath("//tr[2]/td")).getText());
		    driver.findElement(By.xpath("//tr[2]/td")).click();
		    driver.findElement(By.xpath("//a[contains(@href, '/propietarios/1/edit')]")).click();
		    driver.findElement(By.xpath("//form[@id='propietario']/div")).click();
		    driver.findElement(By.xpath("//form[@id='propietario']/div/div[3]/div/input")).clear();
		    driver.findElement(By.xpath("//form[@id='propietario']/div/div[3]/div/input")).sendKeys("Marquez");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    assertNotEquals("Sanchez",driver.findElement(By.xpath("//tr[2]/td")).getText());
	}



	@Test
	@Order(2)
	@DisplayName("No se puede editar el perfil porque deja un campo vacío que no puede estarlo")
	public void testEditarPerfilNotOk() throws Exception {
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
	    driver.findElement(By.id("menu-vertical")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/propietarios/miPerfil')]")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/propietarios/1/edit')]")).click();
	    driver.findElement(By.xpath("//form[@id='propietario']/div/div")).click();
	    driver.findElement(By.xpath("//form[@id='propietario']/div/div/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='propietario']/div/div/div/input")).sendKeys("");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    assertEquals("DNI incorrecto",driver.findElement(By.xpath("//form[@id='propietario']/div/div/div/span[2]")).getText());

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
