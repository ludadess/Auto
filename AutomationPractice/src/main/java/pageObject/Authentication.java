package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class Authentication {
	private WebDriver driver;
	
	By createacctitle = By.xpath("//h3[contains(text(),'Create an account')]");
	By newemail = By.id("email_create");
	By submitcreate = By.id("SubmitCreate");
	By existemail = By.id("email");
	By password = By.id("passwd");
	By SubmilLogin = By.id("SubmitLogin");
	
	
	public Authentication () {
		this.driver = MyDriverClass.getDriver();
	}

	public WebElement getCreateAccTitle() {
		return driver.findElement(createacctitle);
	}
	public WebElement getNewEmail() {
		return driver.findElement(newemail);
	}
	public WebElement getSubmitCreate() {
		return driver.findElement(submitcreate);
	}
	public WebElement getExistedEmail() {
		return driver.findElement(existemail);
	}
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	public WebElement getSubmitLogin() {
		return driver.findElement(SubmilLogin);
	}
}
