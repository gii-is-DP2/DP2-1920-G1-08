package org.springframework.test.inmocasa.ui;

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

public class AceptarOfertaUITest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
//		String pathToGeckoDriver = "C:\\Users\\Santiago\\Downloads";
//		System.setProperty("webdriver.chrome.driver", pathToGeckoDriver + "\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAceptarOfertaOk() throws Exception {
		driver.get("http://localhost:8080/");
		driver.findElement(By.xpath("//div[@id='navbarResponsive']/ul/div/button[2]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/ofertadas')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/compras/8')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/compras/8/aceptar')]")).click();
	}
	
	
	  @Test
	  public void testAceptarOfertaNoOk() throws Exception {
	    driver.get("http://localhost:8080/");
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/viviendas/ofertadas')]")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/compras/1')]")).click();
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
