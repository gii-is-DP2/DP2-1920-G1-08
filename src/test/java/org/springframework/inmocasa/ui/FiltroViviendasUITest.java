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
public class FiltroViviendasUITest {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
		driver = new ChromeDriver();
		//baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@Test
	public void testFiltrarViviendasOK() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/allNew')]")).click();
	    driver.findElement(By.xpath("//input[@id='min']")).click();
	    driver.findElement(By.xpath("//input[@id='min']")).clear();
	    driver.findElement(By.xpath("//input[@id='min']")).sendKeys("100");
	    driver.findElement(By.xpath("//input[@id='max']")).click();
	    driver.findElement(By.xpath("//input[@id='max']")).click();
	    driver.findElement(By.xpath("//input[@id='max']")).clear();
	    driver.findElement(By.xpath("//input[@id='max']")).sendKeys("700000");
	    driver.findElement(By.xpath("//select[@id='zona']")).click();
	    new Select(driver.findElement(By.xpath("//select[@id='zona']"))).selectByVisibleText("Nervion");
	    driver.findElement(By.xpath("//select[@id='zona']")).click();
	    assertEquals("Avenida Buhaira, 30, Sevilla", driver.findElement(By.cssSelector("h3.panel-title")).getText());
	}

	@Test
	public void testFiltrarViviendasNotOK() throws Exception {
		driver.get("http://localhost:"+port);
		driver.findElement(By.xpath("//a[contains(@href, '/viviendas/allNew')]")).click();
		driver.findElement(By.id("min")).click();
	    driver.findElement(By.id("min")).clear();
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	    driver.findElement(By.id("min")).sendKeys("900");
	    driver.findElement(By.id("max")).click();
	    driver.findElement(By.id("max")).clear();
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	    driver.findElement(By.id("max")).sendKeys("80");
	    assertEquals("Fecha de publicacion: 18/09/2015", driver.findElement(By.xpath("//p")).getText());
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
