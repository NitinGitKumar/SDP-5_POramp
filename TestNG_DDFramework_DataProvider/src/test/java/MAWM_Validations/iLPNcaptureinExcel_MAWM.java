package MAWM_Validations;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import MATLM.SpreadSheetRdWRdSingleColumn2;

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


public class iLPNcaptureinExcel_MAWM {

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


	@Parameters({"PuchaseOrder"})
	@Test
	public void POValidationMAWM(@Optional("A-206658")String PuchaseOrder) throws Exception {
		 
		JavascriptExecutor js = (JavascriptExecutor)driver;
	

		driver.get("https://stshs.sce.manh.com");
		driver.findElement(By.id("login-username")).sendKeys("vn10307");
		Thread.sleep(3000);
		driver.findElement(By.id("discover-user-submit")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("login-password")).sendKeys("Schneider0)");
		Thread.sleep(3000);
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(12000);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//navbar[@id='home-page-navbar']/ion-toolbar/ion-menu-toggle/ion-button")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys("ILPNs");
	    Thread.sleep(1000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.id("iLpn")).click();
	    Thread.sleep(7000);
	   
	    WebElement PurchaseOrder=driver.findElement(By.name("ion-input-9"));
	    Thread.sleep(2000);
    	js.executeScript("arguments[0].scrollIntoView();", PurchaseOrder);
    	Thread.sleep(3000);  
    	PurchaseOrder.click();
	    Thread.sleep(1000);
	    PurchaseOrder.clear();
	    Thread.sleep(1000);
	    PurchaseOrder.sendKeys(PuchaseOrder);
	    Thread.sleep(2000);
	    PurchaseOrder.sendKeys(Keys.ENTER);
	    Thread.sleep(10000);
	    List<WebElement> AlliLPN=driver.findElements(By.xpath("//div[@class='dm-flex-col-layout dm-fill-space ng-star-inserted']"));
	    Thread.sleep(2000);
	    
	    ArrayList<String> ilpnTotalQtyList = new ArrayList<String>();
	    ArrayList<String> ilpnNumList = new ArrayList<String>();
    	int finderQty=7;
    	int finderNum=2;
    	String QtyFORExcel="";
	    for(WebElement iLPN:AlliLPN  ) 
	    
	    {
    	
	    	WebElement iLPNNumElement=driver.findElement(By.xpath("(//div[@class='dm-flex-row-layout field-row ng-star-inserted'])["+finderNum+"]/span[2]"));
	    	String iLPNNum=iLPNNumElement.getText();
	    	System.out.println(iLPNNum);
	    	ilpnNumList.add(iLPNNum);
	    	System.out.println(ilpnNumList);
	    	
	    	WebElement iLPNTotQtyElement=driver.findElement(By.xpath("(//div[@class='dm-flex-row-layout field-row ng-star-inserted'])["+finderQty+"]/span[2]"));
	    	String ilpnTotalQty=iLPNTotQtyElement.getText();
	    	System.out.println(ilpnTotalQty);
	    	ilpnTotalQtyList.add(ilpnTotalQty);
	    	System.out.println(ilpnTotalQtyList);
	    	
	    	QtyFORExcel=ilpnTotalQtyList.toString();
	    	finderNum=finderNum+12;
	    	finderQty=finderQty+12;
	    }
	    
	    MATLM.SpreadSheetRdWRdSingleColumn2.writeExcelByColumnRowIndex("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1",QtyFORExcel,1,12);
	    Thread.sleep(3000);
	    

	    Thread.sleep(6000);
	}


	private boolean findElement(By xpath) {
		// TODO Auto-generated method stub
		return false;
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



