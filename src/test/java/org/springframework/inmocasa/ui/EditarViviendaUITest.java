package org.springframework.inmocasa.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class EditarViviendaUITest {
	
	@LocalServerPort
	private int port;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
//	private int allViviendas;


	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testEditViviendaOk() throws Exception {
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
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/mis-viviendas')]")).click();
		driver.findElement(By.xpath("//table[@id='viviendaOfertaTable']/tbody/tr/td[3]")).click();
		assertEquals("Centro",
				driver.findElement(By.xpath("//table[@id='viviendaOfertaTable']/tbody/tr/td[3]")).getText());
	    driver.findElement(By.xpath("//a[contains(@href, '/viviendas/1/edit')]")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).click();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='vivienda']/div/div[3]/div/input")).sendKeys("Bami");
	    driver.findElement(By.xpath("//form[@id='vivienda']/div")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
		assertEquals("Bami",
				driver.findElement(By.xpath("//table[@id='viviendaOfertaTable']/tbody/tr/td[3]")).getText());

	}

//	@Test
//	public void testEditViviendaNotOk() throws Exception {
//		driver.get("http://localhost:"+port);
//		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
//		driver.findElement(By.id("password")).clear();
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//		}
//		driver.findElement(By.id("password")).sendKeys("gilmar");
//		driver.findElement(By.id("username")).clear();
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//		}
//		driver.findElement(By.id("username")).sendKeys("gilmar");
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
//		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
//		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/mis-viviendas')]")).click();
//		
//		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/1/edit')]")).click();
//		driver.findElement(By.xpath("//form[@id='vivienda']/div/div[4]/div/input")).clear();
//		driver.findElement(By.xpath("//form[@id='vivienda']/div/div[4]/div/input")).sendKeys("");
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
//		allViviendas = viviendaCounter();
//		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
//		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/mis-viviendas')]")).click();
//	
//	}
//	
//	private int viviendaCounter() {
//		WebElement viviendas = driver.findElement(By.xpath("//table[@id='viviendaOfertaTable']"));
//		List<WebElement> listaViviendas = viviendas.findElements(By.tagName("tr"));
//		return listaViviendas.size() - 1;
//	}
//
//	public void checkEditVivienda() {
//		assertTrue(viviendaCounter() ==  allViviendas);
//		assertTrue(driver.findElement(By.xpath("//table[@id='viviendaOfertaTable']")).getText().contains("Bami"));
//	}


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
