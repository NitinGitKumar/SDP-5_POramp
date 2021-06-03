package MAWM_Validations;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import MATLM.SpreadSheetRdWRdSingleColumn2;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.testng.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Formatter;
import java.util.Iterator;


public class ASNCreation_MAWM

{

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static String ASN= "";
	static String NewASN= "";

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		WebDriverManager.chromedriver().setup();
	  
		driver= new ChromeDriver();
		driver.get("https://stshs.sce.manh.com");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.id("login-username")).sendKeys("vn10307");
		Thread.sleep(2000);
		driver.findElement(By.id("discover-user-submit")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("login-password")).sendKeys("Schneider0)");
		Thread.sleep(2000);
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(15000);
		
		driver.findElement(By.xpath("//navbar[@id='home-page-navbar']/ion-toolbar/ion-menu-toggle/ion-button")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys("ASNs");
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    driver.findElement(By.id("ASN")).click();
	    Thread.sleep(10000);
		

	}


	@Test(dataProvider="DataContainer")
	public void POValidationMAWM(String PuchaseOrder) throws Exception {
		
		
	    driver.findElement(By.xpath("//button[contains(text(),' Create ASN from PO ')]")).click();
	    Thread.sleep(3000);
/*
	    WebElement test=driver.findElement(By.xpath("(//input[@class='native-input sc-ion-input-md'])[8]"));

	    Thread.sleep(5000);
	  
	    JavascriptExecutor js = (JavascriptExecutor)driver;

	    //js.executeScript("arguments[0].click();", test);
	   
	    js.executeScript("arguments[0].value = '12/31/2021'", test);
*/	    
	    Thread.sleep(4000);
	    driver.findElement(By.xpath("//*[contains (text(),'SUBMIT')]")).click();
	    
	    Thread.sleep(7000);
	    driver.findElement(By.xpath("//input[@placeholder='Enter Purchase Order']")).sendKeys(PuchaseOrder);
	    
	    
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[contains (text(),' SEARCH')]")).click();
	    
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//div[@class='groupby-header']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//div[@class='datatable-header-cell-template-wrap'])[1]")).click();
	    
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[contains (text(),'ADD TO ASN')]")).click();
	    
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[contains (text(),'OK')]")).click();
	    
	    Thread.sleep(6000);
	    
	  ASN=driver.findElement(By.xpath("//div[1]/ion-row[2]/ion-col[1]/span")).getText();
	    
	  NewASN= ASN.substring(7);
	    System.out.println(NewASN);
	    Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[contains (text(),' Save And Finish')]")).click();
	    Thread.sleep(5000);
	    
	   
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
	
	
	
	@DataProvider(name="DataContainer")
	   public String[] myDataProvider() 
	   
	   
	   {
		
		
		 String[] data= SpreadSheetRdWRdSingleColumn2.extractExcelContentByColumnIndex("src/test/resources/MATLMTestData_TestDemo.xls","Sheet1",6);
	  
	      return data;
	   }
}



