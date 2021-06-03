package MAWM_Validations;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import MATLM.SpreadSheetRdWRdSingleColumn2;

import static org.testng.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Formatter;


public class PuchaseOrder_MAWM_Validation {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		/*
	  System.setProperty("webdriver.chrome.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/Chrome Driver/chromedriver");
	  driver= new ChromeDriver();
		 */

		System.setProperty("webdriver.gecko.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/geckodriver/geckodriver");
		driver= new FirefoxDriver(); 


	}


	@Parameters({"BackhaulPuchaseOrder" })
	@Test
	public void POValidationMAWM() throws Exception {
		 
		

		driver.get("https://stshs.sce.manh.com");
		driver.findElement(By.id("login-username")).sendKeys("vn10307");
		Thread.sleep(3000);
		driver.findElement(By.id("discover-user-submit")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("login-password")).sendKeys("Schneider0)");
		Thread.sleep(3000);
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//navbar[@id='home-page-navbar']/ion-toolbar/ion-menu-toggle/ion-button")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("ion-input-0")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).clear();
	    driver.findElement(By.name("ion-input-0")).sendKeys("Purchase Orders");
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    driver.findElement(By.id("PurchaseOrders")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//ion-grid/div/ion-col/ion-button")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//filter-field-header/ion-button")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-4")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-4")).clear();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-4")).sendKeys("A-205840");
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-4")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		String ResultRecord=driver.findElement(By.xpath("//ion-label/span")).getText();
		String POValidated="Purchase Order created in BICEPS is successfully validated in MAWM";
		if(ResultRecord.equalsIgnoreCase("Showing 1 - 1 of 1 Record") )
		{System.out.println(POValidated);
		SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1",POValidated, 5);
		
		}
	}


	@AfterClass(alwaysRun = true)
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



