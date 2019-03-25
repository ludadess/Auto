package ReusableActions;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseFunctions.MyDriverClass;
import baseFunctions.Utilities;
import junit.framework.Assert;
import pageObject.AddToCartPupUpPO;

public class AddToCartPupUpRA {
	private static Logger log = LogManager.getLogger(AddToCartPupUpRA.class.getName());
	static WebDriver driver = MyDriverClass.getDriver();
	
	public static void verifyAddToCartContent (int quantity, String TestSet) throws FileNotFoundException, IOException, ParseException, InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		AddToCartPupUpPO addToCartPg = new AddToCartPupUpPO();
		Utilities.initilizeJSON ("myjson.json","Purchase", TestSet);
		WebDriverWait wait = new WebDriverWait (driver,20);
		//Verify Product Title
		wait.until(ExpectedConditions.visibilityOf(addToCartPg.GetCartProductTitle()));
		Assert.assertEquals(Utilities.readProperty("UserData", "ProductTitle"), addToCartPg.GetCartProductTitle().getText());
		log.info("Add To Cart info is dispalyed: " );
		log.info("Product title: "+addToCartPg.GetCartProductTitle().getText() );
		// Verify Product Attributes
		String expProductAttributes =Utilities.getJSONData("Color") +", "+Utilities.getJSONData("Size");
		Assert.assertEquals(expProductAttributes, addToCartPg.GetCartProductAttributes().getAttribute("textContent"));
		log.info("Product Attributes: "+addToCartPg.GetCartProductAttributes().getText() );
		// Verify Quantity	
		Assert.assertEquals(Integer.toString(1), addToCartPg.GetCartProductQuantity().getAttribute("textContent"));
		log.info("Quantity: "+addToCartPg.GetCartProductQuantity().getText() );
		// Verify Product Price
		Assert.assertEquals(Utilities.readProperty("UserData", "PurchasePrice_"+Integer.toString(quantity)), addToCartPg.GetCartProductPrice().getText());
		// Verify Total Price
		double totalPrice = 2.00;
		for (int i=quantity; i>0; i--) {
			String price = Utilities.readProperty("UserData", "PurchasePrice_"+Integer.toString(i));
			totalPrice = totalPrice+Double.parseDouble(price.replace("$", ""));						
		}
		Thread.sleep(1000);
		Assert.assertEquals("$"+String.format("%.2f", totalPrice), addToCartPg.getTotalPrice().getAttribute("textContent"));	
		log.info("Total price: "+addToCartPg.getTotalPrice().getAttribute("textContent") );		
	}
}
