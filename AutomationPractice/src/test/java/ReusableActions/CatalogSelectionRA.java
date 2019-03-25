package ReusableActions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.applitools.eyes.selenium.Eyes;

import baseFunctions.MyDriverClass;
import baseFunctions.MyExceptionClass;
import baseFunctions.Utilities;
import junit.framework.Assert;
import pageObject.CatalogtSelectionPO;

public class CatalogSelectionRA extends MyExceptionClass{
	public CatalogSelectionRA(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	private static Logger log = LogManager.getLogger(CatalogSelectionRA.class.getName());
	static Eyes eyes;
	static WebDriver driver = MyDriverClass.getDriver();
	
	
	public static void makeSelection(String testSet) throws Exception {
		
		
		// Catalog selection page ************************************************
		CatalogtSelectionPO catalogSelect = new CatalogtSelectionPO();
	
		Utilities.initilizeJSON ("myjson.json","Purchase", testSet);
		// verify Catalog name	
		Assert.assertEquals((Utilities.getJSONData("CatalogName").toUpperCase()+" "), catalogSelect.getTitleBlock().getText());	
		log.info((Utilities.getJSONData("CatalogName"))+ " catalog name is loaded");
		//Select Sort by d/d
		Select productSort = new Select(catalogSelect.getProductSort());
		productSort.selectByVisibleText(Utilities.getJSONData("ProductSort"));
		log.info("Products are sorted by "+ Utilities.getJSONData("ProductSort"));
		// Select size checkbox
		String size = Utilities.getJSONData("Size");
		if (size == null) {log.info("Size is not provided");}
		else {
			switch (size) {
			case "S" : catalogSelect.getSizeSmall().click(); break;
			case "M" : catalogSelect.getSizeMedium().click();break;
			case "L" : catalogSelect.getSizeLarge().click();break;	
			default: throw new MyExceptionClass("Specify a correct size");
			}
			log.info((Utilities.getJSONData("Size"))+ " size is checked");
		}	
		// Select color check box
		String color = Utilities.getJSONData("Color");
		if (color == null) {log.info("Color is not provided");}
		else {
			switch (color) {
			case "Beige" : catalogSelect.getColorBeige().click();break;
			case "White" : catalogSelect.getColorWhite().click();break;
			case "Black" : catalogSelect.getColorBlack().click();break;
			case "Orahge" : catalogSelect.getColorOrange().click();break;
			case "Blue" : catalogSelect.getColorBlue().click();break;
			case "Green" : catalogSelect.getColorGreen().click();break;
			case "Yellow" : catalogSelect.getColorYellow().click();break;
			case "Pink" : catalogSelect.getColorPink().click();break;
			default: throw new MyExceptionClass("Specify a correct color");
			}		
			log.info((Utilities.getJSONData("Color"))+ " color is checked");
		}
		// Select composition check box
		String composition = Utilities.getJSONData("Composition");
		if (composition == null) {log.info("Composition is not provided");}
		else {
			switch (composition) {
			case "Polyester" : catalogSelect.getComposPolyester().click();break;
			case "Viscosa" : catalogSelect.getComposViscosa().click();break;
			case "Cotton" : catalogSelect.getComposCotton().click();break;		
			default: throw new MyExceptionClass("Specify a correct composition");
			}		
			log.info((Utilities.getJSONData("Composition"))+ " composition is checked");
		}
		// Select style check box
		String style = Utilities.getJSONData("Style");
		if (style == null) {log.info("Style is not provided");}
		else {
			switch (style) {
			case "Casual" : catalogSelect.getStyleCasual().click();break;
			case "Girly" : catalogSelect.getStyleGirly().click();break;
			default: throw new MyExceptionClass("Specify a correct style");
			}		
			log.info((Utilities.getJSONData("Style"))+ " style is checked");
		}		
		
	}



}
