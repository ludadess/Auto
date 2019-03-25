package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class AddToCartPupUpPO {
	private WebDriver driver;
	
	By cartProductTitle = By.id("layer_cart_product_title");
	By cartProductAttributes = By.id("layer_cart_product_attributes");
	By cartProductQuantity = By.id("layer_cart_product_quantity");
	By cartProductPrice = By.id("layer_cart_product_price");
	By totalPrice = By.cssSelector("span.ajax_block_cart_total");
	By continueShoping = By.cssSelector("div.button-container>span>span");
	By proceedToCheckout = By.cssSelector("a[title ='Proceed to checkout']>span");
	
	public AddToCartPupUpPO() {this.driver = MyDriverClass.getDriver();}
	
	public WebElement GetCartProductTitle() { return driver.findElement(cartProductTitle);}
	public WebElement GetCartProductAttributes() { return driver.findElement(cartProductAttributes);}
	public WebElement GetCartProductQuantity() { return driver.findElement(cartProductQuantity);}
	public WebElement GetCartProductPrice() { return driver.findElement(cartProductPrice);}
	public WebElement getContinueShoping() {return driver.findElement(continueShoping);}
	public WebElement getTotalPrice() {return driver.findElement(totalPrice);}
	public WebElement getProceedToCheckout() {return driver.findElement(proceedToCheckout);}
	
	
	
	}
	
	

