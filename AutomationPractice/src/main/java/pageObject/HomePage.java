package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseFunctions.MyDriverClass;

public class HomePage {
	private WebDriver driver;
	@FindBy()
	
	By signin = By.xpath("//a[contains(text(),'Sign in')]");
	
	
	public HomePage() {
		this.driver = MyDriverClass.getDriver();		
	}
	
	public WebElement getSignIn () {
		return driver.findElement(signin);
	}
}
