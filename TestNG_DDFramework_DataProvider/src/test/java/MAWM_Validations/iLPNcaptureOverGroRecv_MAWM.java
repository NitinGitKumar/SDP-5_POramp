package MAWM_Validations;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

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


public class iLPNcaptureOverGroRecv_MAWM {

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
	public void POValidationMAWM(@Optional("A-206379")String PuchaseOrder) throws Exception {
		 
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
	    
	    for(WebElement iLPN:AlliLPN  ) 
	    
	    {
    	
	    	WebElement iLPNNumElement=driver.findElement(By.xpath("(//div[@class='dm-flex-row-layout field-row ng-star-inserted'])["+finderNum+"]/span[2]"));
	    	String iLPNNum=iLPNNumElement.getText();
	    	System.out.println(iLPNNum);
	    	ilpnNumList.add(iLPNNum);
	    	System.out.println(ilpnNumList);
	    	
	    	WebElement iLPNTotQtyElement=driver.findElement(By.xpath("(//div[@class='dm-flex-row-layout field-row ng-star-inserted'])["+finderQty+"]/span[2]"));
	    	String ilpnTotalQty=iLPNTotQtyElement.getText();
	        int x=Integer.parseInt(ilpnTotalQty);
	        int y=x+2;   										// Decrease iLPN Qty by 2
	        String ilpnTotalQty2=String.valueOf(y);
	    	System.out.println(ilpnTotalQty2);
	    	ilpnTotalQtyList.add(ilpnTotalQty2);
	    	System.out.println(ilpnTotalQtyList);
	 
	    	finderNum=finderNum+12;
	    	finderQty=finderQty+12;
	    }
	    

	    Thread.sleep(3000);
	    
		driver.findElement(By.xpath("//ion-menu-toggle[@class='toggle md hydrated']/ion-button")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys("WM Mobile");
	    Thread.sleep(2000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    driver.findElement(By.id("wmMobile")).click();
	    Thread.sleep(10000);
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    Thread.sleep(2000);
	    driver.switchTo().window(tabs.get(1));
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//ion-label[text()='Receiving']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@data-component-id='groceryreceiving']")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.xpath("//input[@placeholder='Scan Dock Door']")).sendKeys("DOR005");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Scan Dock Door']")).sendKeys(Keys.ENTER);    
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Asn']")).clear();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Asn']")).sendKeys(MAWM_Validations.ASNCreation_MAWM.NewASN);
	    //driver.findElement(By.xpath("//input[@placeholder='Asn']")).sendKeys("ASN912021050100611");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Asn']")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    
	    
	    for (int i=0;i<AlliLPN.size();i++) 
	    { 

	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//ion-col[text()='ASN']")).click();
	    Thread.sleep(2000);
    	driver.findElement(By.xpath("//input[@placeholder='Scan LPN']")).sendKeys(ilpnNumList.get(i));
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Scan LPN']")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("(//ion-col[@class='value md hydrated'])[3]")).getText();	    
	    Thread.sleep(2000);
	    String Item_Receiving=driver.findElement(By.xpath("(//ion-col[@class='value md hydrated'])[3]")).getText();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Scan Item']")).sendKeys(Item_Receiving);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@placeholder='Scan Item']")).sendKeys(Keys.ENTER);
	    Thread.sleep(2000);
	    
	    // if Country of Origin step is present

	    if(driver.findElements(By.xpath("//input[contains(@data-component-id,'countryoforigin')]")).size()>0)
	    {
	    WebElement countryOfOrigin=driver.findElement(By.xpath("//input[contains(@data-component-id,'countryoforigin')]"));   
	    countryOfOrigin.sendKeys("US");
	    Thread.sleep(2000);
	    countryOfOrigin.sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    }
	    
	    // if Manufactured Date step is present
	    if(driver.findElements(By.xpath("//ion-col[contains(text(),'Manufactured Date')]")).size()>0)
	    {
	    
	    WebElement ExpDate=driver.findElement(By.xpath("//ion-datetime[@class='datetime-field md in-item hydrated']"));   
	    Thread.sleep(2000);
	    ExpDate.click();
	    Thread.sleep(2000);
	    WebElement NextYearSelect=driver.findElement(By.xpath("(//div[@class='picker-opts']/button[@class='picker-opt picker-opt-selected'])[3]/following-sibling::*[1]"));
	    Thread.sleep(2000);
	    NextYearSelect.click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//ion-button[contains(text(),'Go')]")).click();
	    Thread.sleep(2000);
	    }
	    
	   // if Expire Date step is present
	    if(driver.findElements(By.xpath("//ion-datetime[@class='datetime-field md in-item hydrated']")).size()>0)
	    {
	    
	    WebElement ExpDate=driver.findElement(By.xpath("//ion-datetime[@class='datetime-field md in-item hydrated']"));   
	    Thread.sleep(2000);
	    ExpDate.click();
	    Thread.sleep(2000);
	    WebElement NextYearSelect=driver.findElement(By.xpath("(//div[@class='picker-opts']/button[@class='picker-opt picker-opt-selected'])[3]/preceding-sibling::*[1]"));
	    Thread.sleep(2000);
	    NextYearSelect.click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[contains(text(),'Done')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//ion-button[contains(text(),'Go')]")).click();
	    Thread.sleep(3000);
	    }
	    
	        
	    
	    // sending LPN Qty to input box
	    WebElement lpnQTY= (WebElement)js.executeScript("return document.querySelector('quantity-field > div > ion-item > ion-grid > ion-row:nth-child(3) > ion-col:nth-child(1) > input')");
	    Thread.sleep(2000);

	    js.executeScript("arguments[0].value = '"+ilpnTotalQtyList.get(i)+"'", lpnQTY);
	    Thread.sleep(2000);
	    lpnQTY.sendKeys(Keys.ENTER);
	    
	    
	    

	    }
	    
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("(//ion-col[text()='ASN'])[1]")).click(); // ACTIVATING WEB PAGE
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//ion-fab-button[@class='md ion-activatable ion-focusable hydrated']")).click();// OPTION BUTTON  TO SELECT VERY ASN
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("//span[contains (text(),'Verify ASN')]")).click();
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("//ion-button[contains (text(),' Yes ')]")).click();
	    Thread.sleep(6000);
	    
	    driver.findElement(By.xpath("//span[contains (text(),'Ok')]")).click();
	    

	    Thread.sleep(6000);
	    driver.switchTo().window(tabs.get(0));
	    Thread.sleep(4000);
		driver.findElement(By.xpath("//ion-menu-toggle[@class='toggle md hydrated']/ion-button")).click();
		Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys("ASNs");
	    Thread.sleep(3000);
	    driver.findElement(By.name("ion-input-0")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    driver.findElement(By.id("ASN")).click();
	    Thread.sleep(4000);	    
	    driver.findElement(By.xpath("(//ion-row[@class='ng-star-inserted md hydrated']//input)[1]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("(//ion-row[@class='ng-star-inserted md hydrated']//input)[1]")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("(//ion-row[@class='ng-star-inserted md hydrated']//input)[1]")).sendKeys(MAWM_Validations.ASNCreation_MAWM.NewASN);
	    //driver.findElement(By.xpath("(//ion-row[@class='ng-star-inserted md hydrated']//input)[1]")).sendKeys("ASN912021050100611");
	    //driver.findElement(By.name("ion-input-6")).sendKeys("ASN912021050100611");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//ion-row[@class='ng-star-inserted md hydrated']//input)[1]")).sendKeys(Keys.ENTER);
	    Thread.sleep(10000);
	  
	    String ShippedQty=driver.findElement(By.xpath("//span[contains(text(),'Shipped quantity')]/following-sibling::*[1]")).getText();
	    Thread.sleep(2000);
	    int ShippedQuantity=Integer.parseInt(ShippedQty);
	    String ReceivedQty=driver.findElement(By.xpath("//span[contains(text(),'Received quantity')]/following-sibling::*[1]")).getText();
	    Thread.sleep(2000);
	    int ReceivedQuatity=Integer.parseInt(ReceivedQty);
	    Assert.assertTrue(ShippedQuantity<ReceivedQuatity, "OverReceiving is successful");
	   
	    String ASNPostRecvStatus=driver.findElement(By.xpath("//div[@class='priorityCircle ng-star-inserted']")).getText();
	    Thread.sleep(2000);
	    Assert.assertEquals(ASNPostRecvStatus,"Verified");
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



