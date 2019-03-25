package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFunctions.MyDriverClass;

public class CatalogtSelectionPO {
	private WebDriver driver;
	By searchQuery = By.cssSelector("input[id ='search_query_top']");	
	By dressestab = By.cssSelector("a[title ='Dresses']");
	By summerDresseslink = By.cssSelector("a[title ='Summer Dresses']");	
	//title block
	By catalogName = By.className("cat-name");
	// Product sort d/d
	By productSort = By.id("selectProductSort");
	// size checkboxes
	By sizesmall = By.id("layered_id_attribute_group_1");
	By sizemedium = By.id("layered_id_attribute_group_2");
	By sizelarge = By.id("layered_id_attribute_group_3");
	// color checkboxes
	By colorbeige = By.id("layered_id_attribute_group_7");
	By colorwhite = By.id("layered_id_attribute_group_8");
	By colorblack = By.id("layered_id_attribute_group_11");
	By colororange = By.id("layered_id_attribute_group_13");
	By colorblue = By.id("layered_id_attribute_group_14");
	By colorgreen = By.id("layered_id_attribute_group_15");
	By coloryellow = By.id("layered_id_attribute_group_16");
	By colorpink = By.id("layered_id_attribute_group_24");
	// composition checkboxes
	By composPolyester = By.id("layered_id_feature_1");
	By composViscosa = By.id("layered_id_feature_2");
	By composCotton = By.id("layered_id_feature_5");
	//style
	By styleCasual = By.id("layered_id_feature_11");
	By stylGirly= By.id("layered_id_feature_13");
	// properties
	By shortSleeve = By.id("layered_id_feature_17");
	By dressColorful = By.id("layered_id_feature_18");
	By dressShort = By.id("layered_id_feature_19");
	By dressMidi= By.id("layered_id_feature_20");
	By dressMaxi= By.id("layered_id_feature_21");
	// search results
	By searchResults = By.cssSelector("div.product-count");
	By imgBlouse = By.cssSelector("a[title='Blouse']");
	By addToCart = By.xpath("//span[contains(text(),'Add to cart')]");
	By productPrice = By.xpath("//div[@class='right-block']//span[@class='price product-price']");
	By productName = By.cssSelector("div.right-block>h5>a.product-name");

	
	public CatalogtSelectionPO() {this.driver = MyDriverClass.getDriver();	}
		
	public WebElement getSearchQuery () {return driver.findElement(searchQuery);}
	public WebElement getDressesTab () {return driver.findElement(dressestab);}	
	public WebElement getSummerDresseslink () {return driver.findElement(summerDresseslink);}
	public WebElement getTitleBlock () {return driver.findElement(catalogName);}	
	public WebElement getProductSort () {return driver.findElement(productSort);}
	// size checkboxes
	public WebElement getSizeSmall() {return driver.findElement(sizesmall);}
	public WebElement getSizeMedium() {return driver.findElement(sizemedium);}
	public WebElement getSizeLarge() {return driver.findElement(sizelarge);}		
	// color checkboxes
	public WebElement getColorBeige() {return driver.findElement(colorbeige);}
	public WebElement getColorWhite () {return driver.findElement(colorwhite);}
	public WebElement getColorBlack () {return driver.findElement(colorblack);}
	public WebElement getColorOrange () {return driver.findElement(colororange);}
	public WebElement getColorBlue () {return driver.findElement(colorblue);}
	public WebElement getColorGreen () {return driver.findElement(colorgreen);}
	public WebElement getColorYellow () {return driver.findElement(coloryellow);}
	public WebElement getColorPink () {return driver.findElement(colorpink);}
	// composition checkboxes
	public WebElement getComposPolyester () {return driver.findElement(composPolyester);}
	public WebElement getComposViscosa () {return driver.findElement(composViscosa);}
	public WebElement getComposCotton () {return driver.findElement(composCotton);}
	//style checkboxes
	public WebElement getStyleCasual () {return driver.findElement(styleCasual);}
	public WebElement getStyleGirly () {return driver.findElement(stylGirly);}	
	// properties checkboxes
	public WebElement getShortSleeve () {return driver.findElement(shortSleeve);}
	public WebElement getDressColorful () {return driver.findElement(dressColorful);}
	public WebElement getDressShort () {return driver.findElement(dressShort);}
	public WebElement getDressMidi () {return driver.findElement(dressMidi);}
	public WebElement getDressMaxi () {return driver.findElement(dressMaxi);}
	// search results
	public WebElement getSearchResults () {return driver.findElement(searchResults);}
	public WebElement getImgBlouse () {return driver.findElement(imgBlouse);}
	public WebElement getAddToCart () {return driver.findElement(addToCart);}
	public WebElement getProductPrice () {return driver.findElement(productPrice);}
	public WebElement getProductName () {return driver.findElement(productName);}
}
