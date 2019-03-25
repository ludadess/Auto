package StepsDefinition;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.support.ui.Select;

import baseFunctions.MyDriverClass;
import baseFunctions.Utilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import junit.framework.Assert;
import pageObject.Authentication;
import pageObject.HomePage;
import pageObject.LoginMyStore;
import pageObject.MyAccount;
import pageObject.MyAddress;



//@RunWith(Cucumber.class)
public class CreateAccountStepDef extends Utilities {
	private static Logger log = LogManager.getLogger(CreateAccountStepDef.class.getName());
	@Given("^New User is on Landing page$")
	public void new_User_is_on_Landing_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver = MyDriverClass.Initialize();
		driver.get(readProperty("Config","url"));			
	}

	@When("^Navigate to Create Account page$")
	public void navigate_to_Create_Account_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// Home page ******************
				HomePage hp = new HomePage();
				hp.getSignIn().click();
				// Authentication page **************
				// verify 'CREATE AN ACCOUNT" title is displayed
				Authentication ap = new Authentication();
				Assert.assertEquals("CREATE AN ACCOUNT", ap.getCreateAccTitle().getText());
				log.info("CREATE AN ACCOUNT title verification passed");
				// generate an unique email address
				String emailAddress = "Test"+GetRandomNum(6)+"@gmai.com";
				log.info("New email address: "+emailAddress+" is generated");
				ap.getNewEmail().sendKeys(emailAddress);
				// Store email address in UserData.properties file
				addPropData("UserData","email",emailAddress);
				ap.getSubmitCreate().click();		
	    
	}

	@When("^Enter data in all requered fields \"([^\"]*)\" and submit account$")
	public void enter_data_in_all_requered_fields_and_submit_account(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("****************LoginMyStore page **********************");
		// Login MyStore page ********************
		LoginMyStore lmsp = new LoginMyStore();
		
		// Enter data in all required fields
		// retrieve data from excel MyStoreApp.xlsx
		ArrayList <String> ExcelData = getExcelData("PersonalInfo","TestID", arg1);	
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
		addPropData("UserData",ExcelData.get(5),"password");
		// Verify email field is pre-populated by default
		Assert.assertEquals(readProperty("UserData","email"), lmsp.getEmail().getAttribute("value"));
		// Select DOB day
		Select days = new Select (lmsp.getDaySelect());
		days.selectByValue(ExcelData.get(6));
		// Select DOB month
		Select months = new Select (lmsp.getMonthSelect());
		int month = getMonthNumber(ExcelData.get(7));
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

	@Then("^Verify newly created account \"([^\"]*)\" info is displayed correcty$")
	public void verify_newly_created_account_info_is_displayed_correcty(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// retrieve data from excel MyStoreApp.xlsx
		ArrayList <String> ExcelData = getExcelData("PersonalInfo","TestID", arg1);	
				
		// My Account page ***************************
		log.info("****************My Account page **********************");
		MyAccount map = new MyAccount();
		//Verify user name
		Assert.assertEquals(ExcelData.get(2)+" "+ExcelData.get(3), map.getUserName().getText());
		
		log.info("user name is displayed");
		// click on My Address link
		map.getMyAddressLink().click();
		log.info("My Address link is selected");	
		// My Addresses page **************************
		log.info("****************My Address page **********************");
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
		driver.close();
		driver.quit();
	    
	}



}
