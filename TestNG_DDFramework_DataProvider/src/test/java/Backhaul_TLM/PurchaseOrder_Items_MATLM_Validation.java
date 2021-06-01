package Backhaul_TLM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PurchaseOrder_Items_MATLM_Validation {

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

	//@Parameters({ "BackhaulPuchaseOrder" })
	@Test(priority = 1)

	public void testBackhaulTestCase1() throws Exception {
		
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
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys("A-205897");// Enter PO number
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click(); // Click Apply
		WebDriverWait wait=new WebDriverWait(driver, 30);
		
		Thread.sleep(8000);
		boolean POResult = driver.findElement(By.xpath("//td/div[contains(text(),'A-205897')]")).isDisplayed();
		if(POResult)
		System.out.println("Purchase Order found in MAWM");
		else
		System.out.println("Purchase Order NOT found in MAWM");
		
		Thread.sleep(5000);
		
		
		String ExcelPOsQty="";
		String ExcelPOsitem="";
		
		String[] ExcelPOQty=ExcelPOsQty.split(",");
    	Thread.sleep(2000);
    	String[] ExcelPOitem=ExcelPOsitem.split(",");
    	Thread.sleep(2000);
    	
		List<WebElement> AllPOLines=driver.findElements(By.xpath("//table[@id='dataForm:lvLineItems1:dataTablePOLineItem_body']//tr]"));
	    Thread.sleep(2000);
	    
	    ArrayList<String> POLineQtyList = new ArrayList<String>();
	    ArrayList<String> POItemList = new ArrayList<String>();
	    
	    
    	
    	int LineFinder=1;
    
    	int i=0;

	    
	    for(WebElement POLine:AllPOLines  ) 
	    
	    {
    	    
	    	WebElement POLineItemElement=driver.findElement(By.xpath("//table[@id='dataForm:lvLineItems1:dataTablePOLineItem_body']//tr["+LineFinder+"]/td[2]"));
	    	String POLineItem=POLineItemElement.getText();
	    	POItemList.add(POLineItem);
	    	  	
	    	
	    	WebElement POLineQtyElement=driver.findElement(By.xpath("//table[@id='dataForm:lvLineItems1:dataTablePOLineItem_body']//tr["+LineFinder+"]/td[5]"));
	    	String POLineQtyRaw=POLineQtyElement.getText();
	    	String POLineQty=POLineQtyRaw.replaceAll("CASES", "");
	    	POLineQtyList.add(POLineQty);
	 
	    	LineFinder++;
	    
	    	
			Assert.assertEquals(POLineItem.trim(),ExcelPOitem[i].trim());
			Assert.assertEquals(POLineQty.trim(),ExcelPOQty[i].trim());
			
			i++;
	    	
	   
	    }
		
		
		
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



