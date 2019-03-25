package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class MyAccount {
	private WebDriver driver;
	
	By username = By.xpath("//a[@class ='account']//span[1]");	
	//By creditslips = By.xpath("//a[@title = 'Credit slips')]");
	By creditslips = By.cssSelector("a[title ='Credit slips']");                      
	//By myaddress = By.xpath("//span[contains(text(),'My addresses')]");
	By myaddress = By.cssSelector("a[title ='Addresses']");
	By womentab = By.cssSelector("a[title ='Women']");
	By dressestab = By.cssSelector("a[title ='Dresses']");
	By tshirtstab = By.cssSelector("a[title ='T-shirts']");
	By blouseslink = By.cssSelector("a[title ='Blouses']");
	By summerDresses = By.cssSelector("a[title ='Summer Dresses']");
	
	public MyAccount() {
		this.driver = MyDriverClass.getDriver();		
	}
	
	public WebElement getUserName () {
		return driver.findElement(username);
	}
	public WebElement getCreditSlipsLink () {
		return driver.findElement(creditslips);
	}
	public WebElement getMyAddressLink () {
		return driver.findElement(myaddress);
	}
	public WebElement getWomenTab () {
		return driver.findElement(womentab);
	}
	public WebElement getDressesTab () {
		return driver.findElement(dressestab);
	}
	public WebElement getTShirtTab () {
		return driver.findElement(tshirtstab);
	}
	public WebElement getBlousesLink () {
		return driver.findElement(blouseslink);
	}
	public WebElement getSummerDressesLink () {
		return driver.findElement(summerDresses);
	}
	
}
