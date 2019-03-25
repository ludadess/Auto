package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class LoginMyStore {
	private WebDriver driver;
	
	
	By mrtitle = By.id("uniform-id_gender1");
	By mrstitle = By.id("uniform-id_gender2");
	By firstname = By.id("customer_firstname");
	By lastname = By.id("customer_lastname");
	By email = By.id("email");
	By password = By.id("passwd");
	By days = By.id("days");
	By months = By.id("months");
	By years = By.id("years");
	By newsletter = By.id("newsletter");
	By offers = By.id("optin");
	By firstnameaddress = By.id("firstname");
	By lastnameaddress = By.id("lastname");
	By company = By.id("company");
	By address1 = By.id("address1");
	By address2 = By.id("address2");
	By city = By.id("city");
	By id_state = By.id("id_state");
	By postcode = By.id("postcode");
	By id_country = By.id("id_country");
	By other = By.id("other");
	By phone = By.id("phone");
	By phone_mobile = By.id("phone_mobile");
	By alias = By.id("alias");
	By submitAccount = By.id("submitAccount");
	
	public LoginMyStore() {
		this.driver = MyDriverClass.getDriver();		
	}
	
	public WebElement getMrTitle () {
		return driver.findElement(mrtitle);
	}
	public WebElement getMrsTitle() {
		return driver.findElement(mrstitle);
	}
	public WebElement getFirstName() {
		return driver.findElement(firstname);		
	}
	public WebElement getLastName() {
		return driver.findElement(lastname);		
	}
	public WebElement getEmail() {
		return driver.findElement(email);		
	}
	public WebElement getPassword() {
		return driver.findElement(password);		
	}
	public WebElement getDaySelect() {
		return driver.findElement(days);		
	}
	public WebElement getMonthSelect() {
		return driver.findElement(months);		
	}
	public WebElement getYearSelect() {
		return driver.findElement(years);		
	}
	public WebElement getNewsletter() {
		return driver.findElement(newsletter);		
	}
	public WebElement getSpecialOffers() {
		return driver.findElement(offers);		
	}
	public WebElement getFirstNameAddress() {
		return driver.findElement(firstnameaddress);		
	}
	public WebElement getLastNameAddress() {
		return driver.findElement(lastnameaddress);		
	}
	public WebElement getCompany() {
		return driver.findElement(company);		
	}
	public WebElement getAddressLine1() {
		return driver.findElement(address1);		
	}
	public WebElement getAddressLine2() {
		return driver.findElement(address2);		
	}
	public WebElement getCity() {
		return driver.findElement(city);		
	}
	public WebElement getStateSelect() {
		return driver.findElement(id_state);		
	}
	public WebElement getPostCode() {
		return driver.findElement(postcode);		
	}
	public WebElement getCountrySelect() {
		return driver.findElement(id_country);		
	}
	public WebElement getAddInfo() {
		return driver.findElement(other);		
	}
	public WebElement getHomePhone() {
		return driver.findElement(phone);		
	}
	public WebElement getMobilePhone() {
		return driver.findElement(phone_mobile);		
	}
	public WebElement getAliasAddress() {
		return driver.findElement(alias);		
	}
	public WebElement getSubmitButton() {
		return driver.findElement(submitAccount);		
	}
	
}
