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

public class testPartida {
	private WebDriver driverP1; // Navegador
	private WebDriver driverP2;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
		
		driverP1 = new ChromeDriver();
		baseUrl = "https://www.katalon.com/";
		driverP1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driverP2 = new ChromeDriver();
		driverP2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("1");
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		login(driverP1, "a", "a");
		WebDriverWait wait = new WebDriverWait(driverP1, 10);
		WebElement message = driverP1.findElement(By.id("messageSalaDeEspera"));
		wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
		
		login(driverP2, "b", "b");
		wait = new WebDriverWait(driverP2, 10);
		message = driverP2.findElement(By.id("messageSalaDeEspera"));
		wait.until(ExpectedConditions.textToBePresentInElement(message, "Bienvenid@"));
		
		WebElement btnP1 = driverP1.findElement(By.id("btnNuevaPartida"));
		btnP1.click();
		Thread.sleep(500);
		WebElement btnP2 = driverP2.findElement(By.id("btnUnirAPartida"));
		btnP2.click();
		Thread.sleep(500);
					
		//Jugada 1 PRUEBAS
		poner(driverP1,8,5,1);
		poner(driverP1,8,6,1);
		poner(driverP1,8,7,1);
		poner(driverP1,8,8,1);
		poner(driverP1,8,9,1);
		poner(driverP1,8,10,1);
		poner(driverP1,8,11,1);
		driverP1.findElement(By.id("btnJugar")).click();
		Thread.sleep(200);
		
		//Jugada 2 (P)UBLICAR
		poner(driverP2,9,5,1);
		poner(driverP2,10,5,1);
		poner(driverP2,11,5,1);
		poner(driverP2,12,5,1);
		poner(driverP2,13,5,1);
		poner(driverP2,14,5,1);
		poner(driverP2,15,5,1);
		driverP2.findElement(By.id("btnJugar")).click();
		Thread.sleep(200);
		
		//Jugada 3 ABU(B)ILLA
		poner(driverP1,10,2,1);
		poner(driverP1,10,3,1);
		poner(driverP1,10,4,1);
		poner(driverP1,10,6,1);
		poner(driverP1,10,7,1);
		poner(driverP1,10,8,1);
		poner(driverP1,10,9,1);
		driverP1.findElement(By.id("btnJugar")).click();
		Thread.sleep(200);
		
		//Jugada 4 AGUA(R)LOS
		poner(driverP2,15,1,1);
		poner(driverP2,15,2,1);
		poner(driverP2,15,3,1);
		poner(driverP2,15,4,1);
		poner(driverP2,15,6,1);
		poner(driverP2,15,7,1);
		poner(driverP2,15,8,1);
		driverP2.findElement(By.id("btnJugar")).click();
		Thread.sleep(200);
		
//		driverP1.findElement(By.id("btnPasarTurno")).click();
//		Thread.sleep(200);
//		
//		driverP2.findElement(By.id("btnPasarTurno")).click();
//		Thread.sleep(200);
		
		//Jugada 5 ZANJEMO(S)
		poner(driverP1,1,11,1);
		poner(driverP1,2,11,1);
		poner(driverP1,3,11,1);
		poner(driverP1,4,11,1);
		poner(driverP1,5,11,1);
		poner(driverP1,6,11,1);
		poner(driverP1,7,11,1);
		driverP1.findElement(By.id("btnJugar")).click();
		Thread.sleep(2200);
		
		//Aviso sin letras
		
		//Jugada 6 DEST(E)L(L)OS
		poner(driverP2,4,8,1);
		poner(driverP2,5,8,1);
		poner(driverP2,6,8,1);
		poner(driverP2,7,8,1);
		poner(driverP2,9,8,1);
		poner(driverP2,11,8,1);
		poner(driverP2,12,8,1);
		driverP2.findElement(By.id("btnJugar")).click();
		Thread.sleep(200);
		
		driverP1.findElement(By.id("btnPasarTurno")).click();
		Thread.sleep(1000);
		
	}


	private void poner(WebDriver driver, int fila, int col, int letra) {
//		WebElement casilla = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr["+fila+"]/td["+col+"]"));
		fila = fila-1;
		col = col-1;
		WebElement casilla = driver.findElement(By.id(fila+","+col));
		WebElement boton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/button["+letra+"]"));
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
		driverP1.quit();
		driverP2.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverP1.findElement(by);
			driverP2.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverP1.switchTo().alert();
			driverP2.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert1 = driverP1.switchTo().alert();
			Alert alert2 = driverP2.switchTo().alert();
			String alertText = alert1.getText() + alert2.getText();
			if (acceptNextAlert) {
				alert1.accept();
				alert2.accept();
			} else {
				alert1.dismiss();
				alert2.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
