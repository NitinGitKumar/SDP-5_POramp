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


public class TLM_Appointment_MAWM_Validation {

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
	public void AppointmentValidationMAWM(String PurchaseOrder) throws Exception {
		 
		driver.get("https://ahold-mip-qa.logistics.com/login.jsp");
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys("vn10307");
		Thread.sleep(3000);
		driver.findElement(By.id("password")).sendKeys("Schneider0)");
		Thread.sleep(3000);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(3000);
		driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
		Thread.sleep(15000);
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement TLMMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-1013-btnIconEl")));
		TLMMenu.click();
		Thread.sleep(5000);
		WebElement PurchaseOrderLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@part='Purchase Orders' and @name='55679']/span")));
		PurchaseOrderLink.click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys("Purchase Order");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(PurchaseOrder);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
		Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-columnid ='appointmentTime']/div/a")));
		WebElement Appointment_Time=driver.findElement(By.xpath( "//td[@data-columnid ='appointmentTime']/div/a"));
		String AppointmentTime = driver.findElement(By.xpath( "//td[@data-columnid ='appointmentTime']/div/a")).getText();
		System.out.println(AppointmentTime);
		
		Thread.sleep(5000);
		Appointment_Time.click();
		Thread.sleep(45000);
		driver.switchTo().frame(0);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='dataForm:appointmentId_d']")));
		String AppointmentID=driver.findElement(By.xpath( "//table[@id='dataForm:gl2']//span[@id='dataForm:appointmentId_d']")).getText();
		System.out.println(AppointmentID);
		Thread.sleep(3000);
		SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1", AppointmentID,3);
		Thread.sleep(3000);
		SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1", AppointmentTime,4);
		Thread.sleep(5000);
		

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
	    driver.findElement(By.name("ion-input-0")).sendKeys("Appointments");
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    driver.findElement(By.id("appointment")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//ion-grid/div/ion-col/ion-button")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//filter-field-header/ion-button")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-6")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-6")).clear();
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-6")).sendKeys(AppointmentID);
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-6")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		String ResultRecord=driver.findElement(By.xpath("//ion-label/span")).getText();
		String AppointmentValidated="Appointment created in MATLM is successfully validated in MAWM";
		if(ResultRecord.equalsIgnoreCase("Showing 1 - 1 of 1 Record") )
		{System.out.println(AppointmentValidated);
		SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1", AppointmentValidated,5);}
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

//String AptId_Pad = StringUtils.leftPad(AppointmentID, 9, "0");
//System.out.println(AptId_Pad);	
//String AptIdMAWM="Apt-".concat(AptId_Pad);
//System.out.println(AptIdMAWM);

