package MAWM_Validations;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import Backhaul_TLM.SpreadSheetRdWRdSingleColumn2;

import static org.testng.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;


public class iLPNcreation_MAWM {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		
	  System.setProperty("webdriver.chrome.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/Chrome Driver/chromedriver");
	  driver= new ChromeDriver();
		
		 /*
		System.setProperty("webdriver.gecko.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/geckodriver/geckodriver");
		driver= new FirefoxDriver(); 
 */

	}


	@Test
	public void POValidationMAWM() throws Exception {
		 
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
   	 	Actions actions = new Actions(driver); 
		driver.get("https://stshs.sce.manh.com");
		driver.findElement(By.id("login-username")).sendKeys("vn10307");
		Thread.sleep(3000);
		driver.findElement(By.id("discover-user-submit")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("login-password")).sendKeys("Schneider0)");
		Thread.sleep(3000);
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(12000);
		driver.findElement(By.xpath("//navbar[@id='home-page-navbar']/ion-toolbar/ion-menu-toggle/ion-button")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys("ASNs");
	    Thread.sleep(2000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.id("ASN")).click();
	    Thread.sleep(15000);
	    
	    driver.findElement(By.xpath("//ion-grid/div/ion-col/ion-button")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//filter-field-header/ion-button")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("ion-input-6")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.name("ion-input-6")).clear();
	    Thread.sleep(1000);
	  driver.findElement(By.name("ion-input-6")).sendKeys(MAWM_Validations.ASNCreation_MAWM.NewASN);
	   // driver.findElement(By.name("ion-input-6")).sendKeys("ASN912021050100619");
	    
	    Thread.sleep(2000);
	    driver.findElement(By.name("ion-input-6")).sendKeys(Keys.ENTER);
	    Thread.sleep(10000);
	
	    driver.findElement(By.xpath("//div[@class='dm-flex-row-layout dm-fill-space card-row primary']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@class='dmuiIcon filter-arrow md hydrated']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[contains(text(),'Create iLPNs')]")).click();
	    Thread.sleep(7000);
	    
	    
	    driver.findElement(By.xpath("(//div[@class='datatable-header-cell-template-wrap'])[1]")).click();
	    Thread.sleep(3000);
	    
	    List<WebElement> QtyToCartonizeElements=driver.findElements(By.xpath("//input[@name='QuantityToCartonize']"));
	    Thread.sleep(2000);
	     
		driver.findElement(By.xpath("(//div[@class='datatable-header-cell-template-wrap'])[1]")).click();
		Thread.sleep(3000);
	     int j=7;
	   	   
		    for (int i=0;i<QtyToCartonizeElements.size();i++) 
		    
		    { 
		    	int k=j+2;
		    	Thread.sleep(2000);
		    	String AvlQty=driver.findElement(By.xpath("(//div[@class='datatable-body-cell-label'])["+j+"]")).getText();
		    	System.out.println(AvlQty);
		    	//int AvlQtyint=Integer.parseInt(AvlQty);
		    	//int LPNQty= AvlQtyint/2;
		    	//String sLPNQty=String.valueOf(LPNQty);
		    	//System.out.println(sLPNQty);
		    	WebElement LPNElement=driver.findElement(By.xpath("(//div[@class='datatable-body-cell-label'])["+k+"]/span[1]"));
		    	Thread.sleep(2000);
		    	js.executeScript("arguments[0].scrollIntoView();", LPNElement);
		    	Thread.sleep(3000);   	 
		    	 //Double Click the button 
		    	actions.doubleClick(LPNElement).perform(); 
		    	Thread.sleep(3000);
		    	WebElement LPNElement2=driver.findElement(By.xpath("(//div[@class='datatable-body-cell-label'])["+k+"]//span"));
		    	Thread.sleep(3000);
		    	actions.doubleClick(LPNElement2).perform();
		    	actions.sendKeys(Keys.BACK_SPACE);
		    	actions.sendKeys(Keys.BACK_SPACE);
		    	actions.sendKeys(Keys.BACK_SPACE);
		    	actions.sendKeys(Keys.BACK_SPACE);
		    	actions.sendKeys(Keys.BACK_SPACE);
		    	actions.sendKeys(LPNElement2, AvlQty).perform();
		    	Thread.sleep(2000);
		    	j=j+12;

		    }

		WebElement CreateiLPNs=driver.findElement(By.xpath("(//*[contains(text(),' CREATE iLPNS')])[1]"));
	    CreateiLPNs.click();
	    
	    Thread.sleep(15000);
	    
	  
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



