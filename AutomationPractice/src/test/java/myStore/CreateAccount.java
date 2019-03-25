package myStore;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseFunctions.MyDriverClass;
import baseFunctions.Utilities;
import junit.framework.Assert;
import pageObject.Authentication;
import pageObject.HomePage;
import pageObject.LoginMyStore;
import pageObject.MyAccount;
import pageObject.MyAddress;

public class CreateAccount{
	private static Logger log = LogManager.getLogger(CreateAccount.class.getName());
	private WebDriver driver;
	static String fPath = "C:\\Work\\eclipse-workspace\\AutomationPractice\\AutomationPractice\\MyStoreApp.xlsx";
	
	
	@BeforeClass
	public void initilize() throws IOException {
		driver = MyDriverClass.Initialize();
		driver.get(Utilities.readProperty("Config","url"));	
		//Utilities.initiateEyes("My Store", "Create Account");
		long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
	}

	@Test
	public void navigateCreateAccount() throws IOException {
		// Home page ******************
		HomePage hp = new HomePage();
		hp.getSignIn().click();
		// Authentication page **************
		// verify 'CREATE AN ACCOUNT" title is displayed
		//Utilities.validateWindow("Authentication page");
		Authentication ap = new Authentication();
		Assert.assertEquals("CREATE AN ACCOUNT", ap.getCreateAccTitle().getText());
		log.info("CREATE AN ACCOUNT title verification passed");
		// generate an unique email address
		String emailAddress = "Test"+Utilities.GetRandomNum(6)+"@gmai.com";
		log.info("New email address: "+emailAddress+" is generated");
		ap.getNewEmail().sendKeys(emailAddress);
		// Store email address in UserData.properties file
		Utilities.addPropData("UserData","email",emailAddress);
		ap.getSubmitCreate().click();		
	}
	
	@Test
	public void setupNewAcount() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Utilities.validateWindow("Login MyStore page");
		log.info("****************LoginMyStore page **********************");
		// Login MyStore page ********************
		//Utilities.initilizeJSON ("myjson.json","PersonalInfo", "TestSet2");	
		LoginMyStore lmsp = new LoginMyStore();
		// first name
		//lmsp.getFirstName().sendKeys(Utilities.getJSONData("FirstName"));	
		
		
		// Enter data in all required fields
		// retrieve data from excel MyStoreApp.xlsx
		Utilities.connectToExcel(fPath);
		ArrayList <String> ExcelData = Utilities.getExcelData("PersonalInfo","TestID","Scenario1");	
		// title
		if (ExcelData.get(1).equalsIgnoreCase("Mr")){
			lmsp.getMrTitle().click();
		}
		else if (ExcelData.get(1).equalsIgnoreCase("Mrs")) {
			lmsp.getMrsTitle().click();
		}
		// first name
		lmsp.getFirstName().sendKeys(ExcelData.get(2));		
		// last name
		lmsp.getLastName().sendKeys(ExcelData.get(3));
		// password
		lmsp.getPassword().sendKeys(ExcelData.get(5));	
		// Store password in UserData.properties file
		Utilities.addPropData("UserData","password",ExcelData.get(5));
		// Verify email field is pre-populated by default
		Assert.assertEquals(Utilities.readProperty("UserData","email"), lmsp.getEmail().getAttribute("value"));
		// Select DOB day
		Select days = new Select (lmsp.getDaySelect());
		days.selectByValue(ExcelData.get(6));
		// Select DOB month
		Select months = new Select (lmsp.getMonthSelect());
		int month = Utilities.getMonthNumber(ExcelData.get(7));
		months.selectByIndex(month);
		// Select DOB year
		Select years = new Select (lmsp.getYearSelect());
		years.selectByValue(ExcelData.get(8));
		// Select "Sign up for our newsletter" check box
		lmsp.getNewsletter().click();
		// Select "Receive special offers" check box
		lmsp.getSpecialOffers().click();
		// Verify first name field is populated on address section 
		Assert.assertEquals(ExcelData.get(2), lmsp.getFirstNameAddress().getAttribute("value"));
		// Verify last name field is populated on address section 
		Assert.assertEquals(ExcelData.get(3), lmsp.getLastNameAddress().getAttribute("value"));
		// Company
		lmsp.getCompany().sendKeys(ExcelData.get(9));
		// Address Line 1
		lmsp.getAddressLine1().sendKeys(ExcelData.get(10));
		// Address Line2
		lmsp.getAddressLine2().sendKeys(ExcelData.get(11));
		// City
		lmsp.getCity().sendKeys(ExcelData.get(12));
		// Select State
		Select states = new Select (lmsp.getStateSelect());
		states.selectByVisibleText(ExcelData.get(13));
		// ZIP code
		lmsp.getPostCode().sendKeys(ExcelData.get(14));
		// Select country
		Select countries = new Select(lmsp.getCountrySelect());
		countries.selectByVisibleText(ExcelData.get(15));
		// Enter additional info
		lmsp.getAddInfo().sendKeys(ExcelData.get(16));
		// Enter Home phone 
		lmsp.getHomePhone().sendKeys(ExcelData.get(17));
		//Enter mobile phone
		lmsp.getMobilePhone().sendKeys(ExcelData.get(18));
		// Enter alias address
		lmsp.getAliasAddress().sendKeys(" "+ExcelData.get(19));
		log.info("all required fields are populated");
		// Click Register button
		lmsp.getSubmitButton().click();
	}	
	@Test
	public void verifyMyAddress() throws IOException {	
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// retrieve data from excel MyStoreApp.xlsx
		ArrayList <String> ExcelData = Utilities.getExcelData("PersonalInfo","TestID","Scenario1");	
				
		// My Account page ***************************
		log.info("****************My Account page **********************");
		//Utilities.validateWindow("My Account page");
		MyAccount map = new MyAccount();
		//Verify user name
		Assert.assertEquals(ExcelData.get(2)+" "+ExcelData.get(3), map.getUserName().getText());
		
		log.info("user name is displayed");
		// click on My Address link
		map.getMyAddressLink().click();
		log.info("My Address link is selected");	
		// My Addresses page **************************
		log.info("****************My Address page **********************");
		//Utilities.validateWindow("My Address page");
		MyAddress madp = new MyAddress();
		// Verify page title
		Assert.assertEquals("MY ADDRESSES", madp.getMyAddressesTitle().getText());
		// Verify alias address
		Assert.assertEquals("MY ADDRESS "+ExcelData.get(19).toUpperCase(), madp.getAliasAddress().getText());
		//Verify first name
		Assert.assertEquals(ExcelData.get(2), madp.getFirstName().getText());
		//Verify last name
		Assert.assertEquals(ExcelData.get(3), madp.getLasttName().getText());
		//Verify company name
		Assert.assertEquals(ExcelData.get(9), madp.getCompanyName().getText());
		//Verify address1
		Assert.assertEquals(ExcelData.get(10), madp.getAddress1().getText());
		//Verify address2
		Assert.assertEquals(ExcelData.get(11), madp.getAddress2().getText());
		//Verify city
		String city = madp.getCity().getText();
		city = city.replace(",", "");
		Assert.assertEquals(ExcelData.get(12), city);
		//Verify state
		Assert.assertEquals(ExcelData.get(13), madp.getState().getText());
		//Verify postal code
		Assert.assertEquals(ExcelData.get(14), madp.getPostCode().getText());
		//Verify country
		Assert.assertEquals(ExcelData.get(15), madp.getCountry().getText());
		//Verify home phone
		Assert.assertEquals(ExcelData.get(17), madp.getHomePhone().getText());
		//Verify mobile
		Assert.assertEquals(ExcelData.get(18), madp.getMobile().getText());
		log.info("My Address page verification passed");
		madp.backButton().click();
		log.info("Back to Account button is clicked");				
	}	
	@AfterClass
	public void closeBroser() {
		//Utilities.tearDown();
		driver.close();
		driver.quit();
	}
}
