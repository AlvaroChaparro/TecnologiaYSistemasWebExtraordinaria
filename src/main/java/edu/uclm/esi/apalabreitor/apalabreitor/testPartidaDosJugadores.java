package edu.uclm.esi.apalabreitor.apalabreitor;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testPartidaDosJugadores {
	private WebDriver driverP1; // Navegador
	private WebDriver driverP2;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:/Documentos/Informatica/4-Cuarto/Cuatrimestre 1/Tecnologias y Sistemas Web/chromedriver.exe");
		
		driverP1 = new ChromeDriver();
		baseUrl = "https://www.katalon.com/";
		driverP1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driverP2 = new ChromeDriver();
		driverP2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		login(driverP1, "alvaro", "123");
		WebDriverWait wait = new WebDriverWait(driverP1, 10);
		WebElement message = driverP1.findElement(By.id("messageSalaDeEspera"));
		wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
		
		login(driverP2, "whatever", "123");
		wait = new WebDriverWait(driverP2, 10);
		message = driverP2.findElement(By.id("messageSalaDeEspera"));
		wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
		
		WebElement btnP1 = driverP1.findElement(By.id("btnNuevaPartida"));
		btnP1.click();
		Thread.sleep(500);
		WebElement btnP2 = driverP2.findElement(By.id("btnUnirAPartida"));
		btnP2.click();
		Thread.sleep(500);
		
		poner(driverP1,8,8,1);
		poner(driverP1,8,9,1);
		poner(driverP1,8,10,1);
		poner(driverP1,8,11,1);
		poner(driverP1,8,12,1);
		poner(driverP1,8,13,1);
		poner(driverP1,8,14,1);
		
		driverP1.findElement(By.id("btnJugar")).click();
	}


	private void poner(WebDriver driver, int fila, int col, int letra) {
		WebElement casilla = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr["+fila+"]/td["+col+"]"));
		WebElement boton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/button["+letra+"]"));
		casilla.click();
		boton.click();
	}

	private void login(WebDriver driver, String user, String pass) {
		//driver.get("http://172.19.196.66:8080/");
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("loginUserName")).click();
		driver.findElement(By.id("loginUserName")).clear();
		driver.findElement(By.id("loginUserName")).sendKeys(user);
		driver.findElement(By.id("loginPwd")).clear();
		driver.findElement(By.id("loginPwd")).sendKeys(pass);
		driver.findElement(By.id("btnLogin")).click();
	}

	@After
	public void tearDown() throws Exception {
//		driverP1.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverP1.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverP1.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverP1.switchTo().alert();
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
