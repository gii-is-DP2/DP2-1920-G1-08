package org.springframework.inmocasa.ui;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class CreateMensajeUITest {

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
	@DisplayName("Se envía el mensaje de forma correcta")
	public void testCrearMensajeOK() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("celiaherrero");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("celiaherrero");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("navbarDropdown")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/mensajes/new')]")).click();
		driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).click();
		driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).clear();
		driver.findElement(By.xpath("//form[@id='mensaje']/div/div/div/input")).sendKeys("Hola");
		driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).clear();
		driver.findElement(By.xpath("//form[@id='mensaje']/div/div[2]/div/input")).sendKeys("Esto es una prueba");
		new Select(driver.findElement(By.xpath("//form[@id='mensaje']/div/div[3]/select")))
				.selectByVisibleText("Alejandra");
		driver.findElement(By.xpath("//select[@id='client']/option[4]")).click();
		driver.findElement(By.xpath("//form[@id='mensaje']/div[2]/div/button/h4")).click();
		assertEquals("Hola", driver.findElement(By.xpath("//table[@id='MensajesTable']/tbody/tr[1]/td[1]")).getText());
	}


	@Test
	@DisplayName("El mensaje no se envía porque hay un campo vacío")
	public void testCreateMensajeNotOk() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("celiaherrero");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("celiaherrero");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Mis mensajes')]")).click();
		    driver.findElement(By.xpath("//a[contains(text(),'Enviar mensaje')]")).click();
		    driver.findElement(By.xpath("//input[@id='asunto']")).click();
		    driver.findElement(By.xpath("//input[@id='asunto']")).clear();
		    driver.findElement(By.xpath("//input[@id='asunto']")).sendKeys("");
		    driver.findElement(By.xpath("//input[@id='cuerpo']")).click();
		    driver.findElement(By.xpath("//input[@id='cuerpo']")).clear();
		    driver.findElement(By.xpath("//input[@id='cuerpo']")).sendKeys("Esto es una Prueba");
		    new Select(driver.findElement(By.xpath("//select[@id='client']"))).selectByVisibleText("Alonso Soler");
		    driver.findElement(By.xpath("//select[@id='client']/option[3]")).click();
		    driver.findElement(By.xpath("//form[@id='mensaje']/div[2]/div/button/h4")).click();
		    assertEquals("Enviar Mensaje", driver.findElement(By.xpath("//form[@id='mensaje']/div[2]/div/button/h4")).getText());
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
