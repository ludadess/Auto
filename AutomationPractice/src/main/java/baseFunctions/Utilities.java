package baseFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.eyes.selenium.Eyes;

public class Utilities {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JSONObject jobj;
	public static JSONObject jobjPageName;
	public static JSONObject jobjTestSet;
	public static XSSFWorkbook workbook;
	public static Eyes eyes;
	
	
	public static void flashElement (WebElement element, WebDriver driver){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 for( int i=0; i<50; i++) {
			 js.executeScript("arguments[0].style.border='2px dashed black'", element);
			 js.executeScript("arguments[0].style.border='2px dashed white'", element);
			 }
	 }
	
	public static String readProperty (String fileName,String key) throws IOException {
		
		String fPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\"+fileName+".properties";
		File file = new File (fPath);
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties ();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;	
	}

	public static void addPropData(String fileName, String keyName, String keyValue ) throws IOException {
		String fPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\"+fileName+".properties";
	    
		//1. Convert properties data to HashMap ***********************
		Map mapProp = new HashMap();
		// create file input stream object, to make file as readable by machine
		FileInputStream fis = new FileInputStream(fPath);
		// create properties class object to access all non-static methods
		Properties prop = new Properties();
		// load the required .properties file
		prop.load(fis);
		// close the file as Properties class object have all the details
		fis.close();
		// Properties work like HashTable, so we have to handle like hash table only
		Enumeration enumKeys = prop.keys();
		// iterate till the enumKeys has keys
		while (enumKeys.hasMoreElements()) {
			// move from null to first element(key), by default it will not point to first element
			String key = (String) enumKeys.nextElement();
			// fetch the property for the key
			String value = prop.getProperty(key);
			// store the key and value in map
			mapProp.put(key, value);
		}		
		mapProp.put(keyName, keyValue);
		System.out.println("Key: " + keyName + " and Value: " + keyValue + " are added to "+fileName+".properties file");		
		
		//2. Write Hashmap to property file *************************
		
		File file = new File(fPath);
		FileOutputStream outStream = new FileOutputStream (file);
		Properties prop1 = new Properties();
		
		Iterator keys = (Iterator) mapProp.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) mapProp.get(key);
			prop1.setProperty(key, value);
		}
		prop1.store(outStream, null);
	}
		
	public static void addPropData1(String fileName, String keyName, String keyValue ) throws IOException {
		String fPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\"+fileName+".properties";	
		File file = new File(fPath);
		OutputStream outStream = new FileOutputStream (file);
		Properties prop1 = new Properties();
		prop1.setProperty(keyName, keyValue);
		prop1.store(outStream, null);
	}
		
	
	
	public static String GetRandomNum (int countDigit) {
		Random objGenerator = new Random();
	    String generatedText = "";
	  	for (int iCount = 0; iCount< countDigit; iCount++){
	        int randomNumber = objGenerator.nextInt(10);
	        generatedText = generatedText + randomNumber;
	       }	
	  	return generatedText;
	  	
	}
	
	public static void getScreenShot(String fileName) throws IOException {
		driver = MyDriverClass.getDriver();
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Work\\eclipse-workspace\\AutomationPractice\\AutomationPractice\\"+fileName+"screen.png"));				
	}

	public static void connectToExcel (String filePath) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(file);
	}
	
	public static ArrayList <String> getExcelData (String sheetName, String columnName,String rowName ) {
		// TODO Auto-generated method stub
	ArrayList <String>	excelData = new ArrayList <String>();	
	boolean sheetFound = false;
	int sheetsCount = workbook.getNumberOfSheets();
	for(int i=0; i<sheetsCount; i++){
		if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {// get sheet
			System.out.println("sheet "+sheetName+" is found");
			sheetFound = true;
			XSSFSheet sheet = workbook.getSheetAt(i);
			Iterator <Row> rows = sheet.rowIterator();
			Row rowIndex = rows.next();// get next row
			Iterator <Cell> cells = rowIndex.cellIterator();
			
			boolean columnFound = false;
			int columnIndex = 0;		
			while(cells.hasNext()) {
				Cell cellIndex = cells.next();
				if (cellIndex.getStringCellValue().equalsIgnoreCase(columnName)) {// get column 
					columnFound = true;
					break;
				}
				columnIndex++;
			}
			if (columnFound==true) {
				System.out.println("column "+columnName+" is found");
				System.out.println("column: "+ columnName+ " has index "+columnIndex);
				boolean rowFound = false;
				while(rows.hasNext()) {
					rowIndex =rows.next();//move to the next row
					
					if(rowIndex.getCell(columnIndex).getStringCellValue().equalsIgnoreCase(rowName)) {
					rowFound = true;
					System.out.println("row "+rowName+" is found");
					cells = rowIndex.cellIterator();
					while(cells.hasNext()) {
						Cell cellIndex = cells.next();
						if (cellIndex.getCellType()==CellType.STRING) {
							excelData.add(cellIndex.getStringCellValue());
						}
						else {
							excelData.add(NumberToTextConverter.toText(cellIndex.getNumericCellValue()));
						}
					}
					}
				}
				if(rowFound == false) {
					System.out.println("row "+rowName+" not found");
					excelData=null;	
				}
			}
			else {
				System.out.println("column "+columnName+" not found");
				excelData=null;
			}
		}
	if(sheetFound==true) {
		break;
				}
	}
	if(sheetFound ==false) {
		System.out.println("sheet "+sheetName+" not found");
		excelData = null;
	}
	return excelData;			
	}	

	public static Integer getMonthNumber (String monthName) throws ParseException {
	Date date = new SimpleDateFormat("MMMM").parse(monthName);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal.get(Calendar.MONTH)+1;
	}
	
	public static void initilizeJSON (String filePath,String pageName,String TestSet) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {	
		JSONParser  jparser = new JSONParser();
		Object obj = jparser.parse(new FileReader(filePath));
		jobj = (JSONObject) obj;	
		jobjPageName = (JSONObject) jobj.get(pageName);
		jobjTestSet = (JSONObject) jobjPageName.get(TestSet);
	}
	
	public static String getJSONData (String keyName) {		
		String keyValue = (String) jobjTestSet.get(keyName);
		return keyValue;
		
	}
	
	public static WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;	
	}
	
	public void clickWhenReady(By locator, int timeout) {
		WebElement element = null;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
}