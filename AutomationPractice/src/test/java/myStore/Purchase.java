package myStore;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ReusableActions.AddToCartPupUpRA;
import ReusableActions.CatalogSelectionRA;
import baseFunctions.MyDriverClass;
import baseFunctions.MyExceptionClass;
import baseFunctions.Utilities;
import pageObject.AddToCartPupUpPO;
import pageObject.Authentication;
import pageObject.CatalogtSelectionPO;
import pageObject.HomePage;
import pageObject.MyAccount;
import pageObject.OrderPO;


public class Purchase {
	
	private  WebDriver driver;
	private Logger log = LogManager.getLogger(Purchase.class.getName());
	
	
	@BeforeClass
	public void initilize() throws IOException {
		
		driver = MyDriverClass.Initialize();
		driver.get(Utilities.readProperty("Config","url"));	
		//Utilities.initiateEyes("My Store", "Purchase");
		long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
		
	}

	@Test(priority=1)
	public void navigateMyAccount() throws IOException, org.json.simple.parser.ParseException {
		// Home page ******************
		//Utilities.validateWindow("Home page");
		HomePage hp = new HomePage();
		hp.getSignIn().click();
		
		// Authentication page **************
		//Utilities.validateWindow("Authentication page");
		// Sign in as existing user
		Authentication ap = new Authentication();
		ap.getExistedEmail().sendKeys(Utilities.readProperty("UserData","email"));
		ap.getPassword().sendKeys(Utilities.readProperty("UserData","password"));
		ap.getSubmitLogin().click();
	
	}
	
	@Test(priority=2)
	public void selectFirstProduct() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		//Utilities.validateWindow("My Account page");
		// My Account page **************
		MyAccount map = new MyAccount();
		// select Women tab
		Actions a = new Actions(driver);
		a.moveToElement(map.getWomenTab()).build().perform();
		// select Blouses link
		map.getBlousesLink().click();
		// Catalog selection page ***************************
		//Utilities.validateWindow("Catalog selection page");
		CatalogtSelectionPO catalogSelect = new CatalogtSelectionPO();
		// make a catalog selections
		CatalogSelectionRA.makeSelection("TestSet1");	
		// Verify search results is not null
		String searchResult = catalogSelect.getSearchResults().getText();
		if (Character.getNumericValue(searchResult.charAt(8))>0)
			log.info("Search results is showing " +searchResult);
		else
			throw new MyExceptionClass("Search results is showing null");
		
		wait.until(ExpectedConditions.elementToBeClickable(catalogSelect.getProductName()));
		// Store product name
		Utilities.addPropData("UserData", "ProductTitle", catalogSelect.getProductName().getText());
		// Store product price
		Utilities.addPropData("UserData", "PurchasePrice_1", catalogSelect.getProductPrice().getText());
		// Click Add to Cart button		
		a.moveToElement(catalogSelect.getImgBlouse()).build().perform();
		catalogSelect.getAddToCart().click();
		log.info("Add to Cart button is clicked");
		// Add To Cart Pop Up **********************************
		AddToCartPupUpPO addToCart = new AddToCartPupUpPO();
		// Verify Add To Cart info is displayed according to selection
		AddToCartPupUpRA.verifyAddToCartContent(1, "TestSet1");
		// Click continue Shopping button
		addToCart.getContinueShoping().click();	
	}
	
	@Test(priority=3)
	public void selectSecondProduct() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,200);
		// Catalog selection page ***************************
		CatalogtSelectionPO catalogSelect = new CatalogtSelectionPO();		
		// select Dresses tab
		Actions a = new Actions(driver);
		a.moveToElement(catalogSelect.getSearchQuery()).click().sendKeys("summer dresses").sendKeys(Keys.ENTER).build().perform();	
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(catalogSelect.getDressesTab()));
		//catalogSelect.getDressesTab().click();
		// select SummerDresses link
		//catalogSelect.getSummerDresseslink().click();	
		// make a catalog selections
		//CatalogSelectionRA.makeSelection("TestSet2");	
		// Verify search results is not null
		String searchResult = catalogSelect.getSearchResults().getText();
		if (Character.getNumericValue(searchResult.charAt(8))>0)
			log.info("Search results is showing " +searchResult);
		else
			throw new MyExceptionClass("Search results is showing null");
		wait.until(ExpectedConditions.elementToBeClickable(catalogSelect.getProductName()));
		// Store product name
		Utilities.addPropData("UserData", "ProductTitle", catalogSelect.getProductName().getText());
		// Store product price
		Utilities.addPropData("UserData", "PurchasePrice_2", catalogSelect.getProductPrice().getText());
		// Click Add to Cart button		
		//a.moveToElement(catalogSelect.getImgBlouse()).build().perform();
		catalogSelect.getAddToCart().click();
		log.info("Add to Cart button is clicked");
		// Add To Cart Pop Up **********************************
		AddToCartPupUpPO addToCart = new AddToCartPupUpPO();
		// Verify Add To Cart info is displayed according to selection
		AddToCartPupUpRA.verifyAddToCartContent(2, "TestSet2");
		// Click Proceed To Checkout button
		addToCart.getProceedToCheckout().click();
		// Order page
		OrderPO orderPg = new OrderPO();
		// click Sign out 
		orderPg.getSignOut().click();
		
	}
	
	@AfterClass 
	public void closeBroser() {
		//Utilities.tearDown();
		
		driver.close();
		driver.quit();
	}
}
