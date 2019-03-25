package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class MyAddress {
	private WebDriver driver;
	
	//By myaddresses = By.xpath("//h1[@class='page-heading']");
	By myaddresses = By.cssSelector("h1.page-heading");
	By aliasaddress = By.cssSelector("h3.page-subheading");
	By firstname = By.cssSelector("ul.last_item.item.box>li:nth-of-type(2)>span:first-child");
	By lastname = By.cssSelector("ul.last_item.item.box>li:nth-of-type(2)>span:nth-of-type(2)");
	By company  = By.className("address_company");
	By address1 = By.className("address_address1");
	By address2 = By.className("address_address2");
	By city = By.cssSelector("ul.last_item.item.box>li:nth-of-type(5)>span:nth-of-type(1)");
	By state = By.cssSelector("ul.last_item.item.box>li:nth-of-type(5)>span:nth-of-type(2)");
	By postcode = By.cssSelector("ul.last_item.item.box>li:nth-of-type(5)>span:last-child");
	By country = By.cssSelector("ul.last_item.item.box>li:nth-of-type(6)>span:first-child");
	By homephone = By.className("address_phone");
	By mobile = By.className("address_phone_mobile");
	By backbutton = By.xpath("//ul[@class='footer_links clearfix']//li[1]//a[1]//span[1]");
	
	
	public MyAddress() {
		this.driver = MyDriverClass.getDriver();		
	}
	
	public WebElement getMyAddressesTitle () {
		return driver.findElement(myaddresses);
	}
	public WebElement getAliasAddress() {
		return driver.findElement(aliasaddress);
	}
	public WebElement getFirstName() {
		return driver.findElement(firstname);
	}
	public WebElement getLasttName() {
		return driver.findElement(lastname);
	}
	public WebElement getCompanyName() {
		return driver.findElement(company);
	}
	public WebElement getAddress1() {
		return driver.findElement(address1);
	}
	public WebElement getAddress2() {
		return driver.findElement(address2);
	}	
	public WebElement getCity() {
		return driver.findElement(city);
	}
	public WebElement getState() {
		return driver.findElement(state);
	}
	public WebElement getPostCode() {
		return driver.findElement(postcode);
	}
	public WebElement getCountry() {
		return driver.findElement(country);
	}
	public WebElement getHomePhone() {
		return driver.findElement(homephone);
	}
	public WebElement getMobile() {
		return driver.findElement(mobile);
	}
	public WebElement backButton() {
		return driver.findElement(backbutton);
	}
	
	
	
	
}