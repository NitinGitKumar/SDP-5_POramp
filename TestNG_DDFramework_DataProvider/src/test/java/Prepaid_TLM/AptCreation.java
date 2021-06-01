package Prepaid_TLM;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AptCreation {

  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  //System.setProperty("webdriver.chrome.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/Chrome Driver/chromedriver");
	  //driver= new ChromeDriver();
	  System.setProperty("webdriver.gecko.driver", "/Users/nitinkumar/eclipse-workspace/Project2/Library/geckodriver/geckodriver");
	  driver= new FirefoxDriver(); 

  }

  @Test
  public void testUntitledTestCase() throws Exception {
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
	driver.get("https://ahold-tlm-qa.logistics.com/manh/index.html?i=85");
	Thread.sleep(10000);
    driver.findElement(By.id("button-1013-btnIconEl")).click();
    Thread.sleep(4000);
    driver.findElement(By.xpath("//div[@id='component-1070']/div[3]/div[2]/span")).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys("Purchase Order");
    Thread.sleep(3000);
    driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[6]")).sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys("A-205942");
    Thread.sleep(5000);
    driver.findElement(By.xpath("(//Input[contains(@id,'inputEl')])[8]")).sendKeys(Keys.ENTER);
    Thread.sleep(5000);
    driver.findElement(By.xpath("(//*[contains(text(),'Apply')])[3]")).click();
    Thread.sleep(5000);
    WebDriverWait wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'A-205942')]")));
	String CreateAppointment = driver.findElement(By.xpath( "(//a[contains(text(),'Create Appointment')])")).getText();
	System.out.println(CreateAppointment);
	Thread.sleep(5000);
	if (CreateAppointment.equalsIgnoreCase("Create Appointment")) // Conditional Check if Create Appointment option is available
	{
	driver.findElement(By.xpath( "(//a[contains(text(),'Create Appointment')])")).click(); // Click Create Appointment
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    int size = driver.findElements(By.tagName("iframe")).size();
    System.out.println("Total Frames --" + size);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.switchTo().frame(0);
    Thread.sleep(5000);
    driver.findElement(By.id("dataForm:selectBtn")).click(); // Click Select button for system recommended appointment time
    Thread.sleep(5000);
    WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.id("apptList_btn_12")));
    Thread.sleep(5000);
    Save.click(); // Save
    Thread.sleep(5000);
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



