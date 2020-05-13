package org.springframework.inmocasa.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditarPerfilUITest {
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
	public void testEditarPerfilOk() throws Exception {
		driver.get("http://localhost:8080/");
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
		driver.findElement(By.xpath("//a[contains(@href, '/usuario/miPerfil')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/propietarios/1/edit')]")).click();
		driver.findElement(By.xpath("//form[@id='propietario']/div/input")).click();
		driver.findElement(By.xpath("//form[@id='propietario']/div/input")).clear();
		driver.findElement(By.xpath("//form[@id='propietario']/div/input")).sendKeys("gilmar");
		driver.findElement(By.xpath("//form[@id='propietario']/div[2]/div/button")).click();
	}

	@Test
	public void testEditarPerfilNotOk() throws Exception {
		driver.get("http://localhost:8080/");
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("password")).sendKeys("12345678");
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.id("username")).sendKeys("gilmar");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/usuario/miPerfil')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/propietarios/1/edit')]")).click();
		driver.findElement(By.xpath("//form[@id='propietario']/div/div/div/input")).sendKeys("77929029S");
		driver.findElement(By.xpath("//form[@id='propietario']/div[2]/div/button")).click();
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
