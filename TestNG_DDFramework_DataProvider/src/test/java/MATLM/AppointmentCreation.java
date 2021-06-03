package MATLM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AppointmentCreation {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/Chrome Driver/chromedriver");
		//driver= new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/geckodriver/geckodriver");
		driver= new FirefoxDriver();
		driver.get("https://ahold-mip-qa.logistics.com/login.jsp");
		Thread.sleep(1000);
		driver.findElement(By.id("username")).sendKeys("vn10307");
		Thread.sleep(3000);
		driver.findElement(By.id("password")).sendKeys("Schneider0)");
		Thread.sleep(3000);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		
	}

	@Parameters({ "BackhaulPuchaseOrder" })
	@Test(priority = 1)

	public void testBackhaulTestCase1(String PurchaseOrder) throws Exception {
		
		driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
		Thread.sleep(10000);
		driver.findElement(By.id("button-1013-btnIconEl")).click(); // Main Menu
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='component-1070']/div[3]/div[2]/span")).click(); //select option Purchase Order
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys("Purchase Order"); //Enter primary Field as Purchase Order
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(PurchaseOrder);// Enter PO number
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click(); // Click Apply
		WebDriverWait wait=new WebDriverWait(driver, 30);
		
		Thread.sleep(8000);
		//WebElement POResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'A-205958')]")));
		String CreateAppointment = driver.findElement(By.xpath( "(//a[contains(text(),'Create Appointment')])")).getText();
		System.out.println(CreateAppointment);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//img[@class='x-tool-img x-tool-close'])[2]")).click(); //Close Purchase Order Dialog
		Thread.sleep(5000);
		
		
		
if (CreateAppointment.equalsIgnoreCase("Create Appointment")) // Conditional Check if Create Appointment option is available
{
		driver.findElement(By.id("button-1013-btnIconEl")).click(); // main menu
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@part='Distribution Orders' and @name='55681']/span")).click(); // select option distribution order
		Thread.sleep(8000);
		WebElement Search_DO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//Input[contains(@id,'inputEl')])[6]"))); //find Primary field
		Search_DO.sendKeys("Distribution Order"); ////Enter primary Field as distribution Order
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
		String DistOrder=PurchaseOrder.concat("_*");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(DistOrder); //Enter distribution order
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[contains(@id,'gridview')]//tbody/tr/td/div/div")).click(); // Click Checkbox TO select searched DO
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[contains(@id,'mpsactionbar')]//span[contains(@id,'btnInnerEl')])[1]")).click(); // Click VIEW
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total Frames --" + size);
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		//driver.findElement(By.xpath("//input[@id='dataForm:DODetailsMainHeader_ATS_button' and @value='Assign to Shipment']")).click();
		driver.findElement(By.xpath("//input[@id='dataForm:DODetailsMainHeader_ATS_button']")).click(); // Click- Assign to Shipment
		Thread.sleep(5000);
		driver.findElement(By.name("boolDraftShipmentType1")).click(); //select radio- Draft shipment
		Thread.sleep(5000);
		driver.findElement(By.id("dataForm:go")).click(); //Click GO
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='Actions']/span[3]")).click(); //Click on Action Button
		Thread.sleep(5000);
		driver.findElement(By.linkText("Save")).click(); 
		Thread.sleep(5000);
		String TransportStatus =driver.findElement(By.xpath("//div/span[contains (text(),'Planned')]")).getText();
		if (TransportStatus.equalsIgnoreCase("Planned")) 
		{System.out.println("DO Transportation Status moved from Unplanned to Planned");
		SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1", TransportStatus,1);}
		
		
}



else
		{System.out.println("Appointment already created");}
Thread.sleep(4000);


	}
	
	
@Parameters({ "BackhaulPuchaseOrder" })
@Test (priority=2,invocationCount = 1)
public void testBackhaulTestCase2(String PurchaseOrder) throws Exception {


	driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
	Thread.sleep(5000);
	driver.findElement(By.id("button-1013-btnIconEl")).click(); // Main Menu
	Thread.sleep(4000);		  
	driver.findElement(By.xpath("//div[@part='Distribution Orders' and @name='55681']/span")).click(); // select option distribution order
	Thread.sleep(8000);
	WebDriverWait wait=new WebDriverWait(driver, 30);
	WebElement Search_DO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//Input[contains(@id,'inputEl')])[6]"))); //find Primary field
	Search_DO.sendKeys("Distribution Order"); ////Enter primary Field as distribution Order
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	String DistOrder=PurchaseOrder.concat("_*");
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(DistOrder); //Enter distribution order
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//table[contains(@id,'gridview')]//tbody/tr/td/div/div")).click(); // Click Checkbox TO select searched DO
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[contains(@id,'mpsactionbar')]//span[contains(@id,'btnInnerEl')])[1]")).click(); // Click VIEW
	Thread.sleep(5000);

	driver.switchTo().frame(0);
	String ShipmentID=driver.findElement(By.id("dataForm:shipmentIdRepeat1:0:DODtlHdr_ShpId_OpLnk_Txt__")).getText();
	Thread.sleep(2000);
	driver.findElement(By.id("dataForm:shipmentIdRepeat1:0:DODtlHdr_ShpId_OpLnk_Txt__")).click(); //Click on shipment ID

	SpreadSheetRdWRdSingleColumn2.writeExcel("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1", ShipmentID,2);
	Thread.sleep(5000);
	driver.findElement(By.id("dataForm:ShpDtl_PromoteBtn")).click();  //Click on Promote button
	Thread.sleep(5000);
	driver.findElement(By.xpath("//button[@type='button']")).click(); //Again Click on Promote button on promote shipment window
	Thread.sleep(5000);

	int NumberFrames = driver.findElements(By.tagName("iframe")).size();
	System.out.println(NumberFrames);
	driver.switchTo().defaultContent();
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div/img[@class='x-tool-img x-tool-close'])[3]")).click();		
	Thread.sleep(5000);




}


@Parameters({ "BackhaulPuchaseOrder" })
@Test(priority = 3)
public void testBackhaulTestCase3(String PurchaseOrder) throws Exception {


	driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
	Thread.sleep(5000);
	driver.findElement(By.id("button-1013-btnIconEl")).click(); // Main Menu
	Thread.sleep(4000);		  
	driver.findElement(By.xpath("//div[@part='Distribution Orders' and @name='55681']/span")).click(); // select option distribution order
	Thread.sleep(8000);
	WebDriverWait wait=new WebDriverWait(driver, 30);
	WebElement Search_DO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//Input[contains(@id,'inputEl')])[6]"))); //find Primary field
	Search_DO.sendKeys("Distribution Order"); ////Enter primary Field as distribution Order
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	String DistOrder=PurchaseOrder.concat("_*");
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(DistOrder); //Enter distribution order
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//table[contains(@id,'gridview')]//tbody/tr/td/div/div")).click(); // Click Checkbox TO select searched DO
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[contains(@id,'mpsactionbar')]//span[contains(@id,'btnInnerEl')])[1]")).click(); // Click VIEW
	Thread.sleep(5000);

	driver.switchTo().frame(0);
	driver.findElement(By.id("dataForm:shipmentIdRepeat1:0:DODtlHdr_ShpId_OpLnk_Txt__")).click(); //Click on shipment ID
	Thread.sleep(5000);
	Actions act = new Actions(driver);
	driver.findElement(By.id("ShpDtl_TenderDetailsBtn")).click(); //Click on Tender details on new window- DO Details- shipment
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();//Click on Manual assign button on DO Details- shipment Tender
	Thread.sleep(5000);
	driver.switchTo().frame(0);
	Thread.sleep(5000);
	driver.findElement(By.id("assignedTPString")).sendKeys("ADUC"); //enter Carrier code on manual assign window  //input[@id='find_carrier_code']

	Thread.sleep(3000);
	new Select(driver.findElement(By.name("assignedMode"))).selectByVisibleText("TL"); //Select mode on manual assign window
	Thread.sleep(3000);
	new Select(driver.findElement(By.name("assignedSLId"))).selectByVisibleText("OTR");  //Select Service Level on manual assign window
	Thread.sleep(3000);
	new Select(driver.findElement(By.name("assignedEquip"))).selectByVisibleText("53-Dry"); //Select Equipment Type on manual assign window
	Thread.sleep(3000);
	driver.findElement(By.xpath("//table[@class='jpopftr']//*[contains(text(),'Assign')]")).sendKeys(Keys.ENTER); //Click assign on Manual Assign Window

	Thread.sleep(5000);

}


@Parameters({ "BackhaulPuchaseOrder" })
@Test(priority = 4)
public void testBackhaulTestCase4(String PurchaseOrder) throws Exception {


	driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
	Thread.sleep(5000);
	driver.findElement(By.id("button-1013-btnIconEl")).click(); // Main Menu
	Thread.sleep(4000);		  
	driver.findElement(By.xpath("//div[@part='Distribution Orders' and @name='55681']/span")).click(); // select option distribution order
	Thread.sleep(8000);
	WebDriverWait wait=new WebDriverWait(driver, 30);
	WebElement Search_DO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//Input[contains(@id,'inputEl')])[6]"))); //find Primary field
	Search_DO.sendKeys("Distribution Order"); ////Enter primary Field as distribution Order
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	String DistOrder=PurchaseOrder.concat("_*");
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(DistOrder); //Enter distribution order
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//table[contains(@id,'gridview')]//tbody/tr/td/div/div")).click(); // Click Checkbox TO select searched DO
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[contains(@id,'mpsactionbar')]//span[contains(@id,'btnInnerEl')])[1]")).click(); // Click VIEW
	Thread.sleep(5000);

	driver.switchTo().frame(0);
	driver.findElement(By.id("dataForm:shipmentIdRepeat1:0:DODtlHdr_ShpId_OpLnk_Txt__")).click(); //Click on shipment ID
	Thread.sleep(5000);
	Actions act = new Actions(driver);
	driver.findElement(By.id("ShpDtl_TenderDetailsBtn")).click(); //Click on Tender details on new window- DO Details- shipment
	
	Thread.sleep(5000);
	WebElement EditBtn = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//a[@class='datalink' and text()='Edit']"))));
	Thread.sleep(5000);
	EditBtn.click();  //Click on Edit button to edit cost  DO Details- shipment Tender
	Thread.sleep(5000);
	driver.switchTo().frame(0);
	Thread.sleep(5000);
	driver.findElement(By.name("spotCharge")).sendKeys("200"); //Enter spot charges on dialogue window
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); //Enter Save Button on dialogue window
	Thread.sleep(5000);

}


@Parameters({ "BackhaulPuchaseOrder" })
@Test(priority = 5)
public void testBackhaulTestCase5(String PurchaseOrder) throws Exception {


	driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
	Thread.sleep(5000);
	driver.findElement(By.id("button-1013-btnIconEl")).click(); // Main Menu
	Thread.sleep(4000);		  
	driver.findElement(By.xpath("//div[@part='Distribution Orders' and @name='55681']/span")).click(); // select option distribution order
	Thread.sleep(8000);
	WebDriverWait wait=new WebDriverWait(driver, 30);
	WebElement Search_DO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//Input[contains(@id,'inputEl')])[6]"))); //find Primary field
	Search_DO.sendKeys("Distribution Order"); ////Enter primary Field as distribution Order
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	String DistOrder=PurchaseOrder.concat("_*");
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(DistOrder); //Enter distribution order
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//table[contains(@id,'gridview')]//tbody/tr/td/div/div")).click(); // Click Checkbox TO select searched DO
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[contains(@id,'mpsactionbar')]//span[contains(@id,'btnInnerEl')])[1]")).click(); // Click VIEW
	Thread.sleep(5000);

	driver.switchTo().frame(0);
	driver.findElement(By.id("dataForm:shipmentIdRepeat1:0:DODtlHdr_ShpId_OpLnk_Txt__")).click(); //Click on shipment ID
	Thread.sleep(5000);
	
	driver.findElement(By.id("ShpDtl_TenderDetailsBtn")).click(); //Click on Tender details on new window- DO Details- shipment
	Thread.sleep(6000);
	
	Thread.sleep(5000);
	WebElement tenderbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "(//button[@type='button'])[2]")));
	Thread.sleep(5000);
	tenderbutton.click();      //Click on tender button on DO Details- shipment Tender
	Thread.sleep(5000);
	assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to tender this shipment to the assigned carrier[\\s\\S]$"));
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@id='Actions']/span[3]")).click(); //Click on Action Button
	Thread.sleep(5000);
	driver.findElement(By.linkText("Accept")).click(); //Click Accept button
	Thread.sleep(5000);
	assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to accept this shipment[\\s\\S]$"));
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
	

}



