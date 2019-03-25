package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class OrderPO {
private WebDriver driver;	
By signOut = By.xpath("//a[@title ='Log me out']");

public OrderPO() {this.driver = MyDriverClass.getDriver();}

public WebElement getSignOut() {return driver.findElement(signOut);}
}