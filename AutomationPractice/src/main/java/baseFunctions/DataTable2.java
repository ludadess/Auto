package baseFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable2 {
	public static String fileName;
	public static FileInputStream inputStream = null;
	public static FileOutputStream outputStream =null;
	private static Workbook workbook = null;	
	private static XSSFSheet sheet = null;
	private static XSSFRow row   =null;
	private static XSSFCell cell = null;
	private static Logger log = LogManager.getLogger(DataTable2.class.getName());
	private static Map<String, String> DataTable = new HashMap<String, String>();
	
	
	public DataTable2(String fileName){
		
		this.fileName=fileName;
		
		// Create an object of FileInputStream class to read excel file
		try {
			inputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		System.out.println(fileExtensionName);

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			try {
				workbook = new XSSFWorkbook(inputStream);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			try {
				workbook = new HSSFWorkbook(inputStream);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		log.info("Import "+fileName+ " successfully completed");
		}		

	public static void readData(String sheetName, int rowNumber) {

		// search for a sheet by SheetName
		boolean sheetFound = false;
		int sheetsCount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetsCount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {// get sheet
				sheetFound = true;
				Sheet sheet = workbook.getSheetAt(i);
				// Read row0
				Row row0 = sheet.getRow(0);
				// Read rowNumber and store in map as a key (column name) and a value (cell string value)
				if (rowNumber <= sheet.getLastRowNum()) {
					Row row1 = sheet.getRow(rowNumber);
					for (int j = 0; j < row0.getLastCellNum(); j++) {
						Cell cell1 = row1.getCell(j);
						DataTable.put(row0.getCell(j).getStringCellValue(), getCellValueAsString(cell1));
						}
				} else {
					log.error("Total number of rows is " + sheet.getLastRowNum() + ". There is no row with number "
							+ rowNumber);
					System.exit(0);
				}
			}
			if (!sheetFound) {
				log.error("There is no sheet with name " + sheetName);
				System.exit(0);
			}
		}
	}

	public static String Value(String KeyName) {
		String strValue = "";
		strValue = DataTable.get(KeyName);
		if (strValue == null) {
			log.error("There is no column with name " + KeyName);
			System.exit(0);
		}
		return strValue;
	}

	//convert different type of data to string
	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;

		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				strCellValue = cell.toString();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case BLANK:
				strCellValue = "";
				break;
			default:
				break;
			}
		} else
			strCellValue = "";
		return strCellValue;
	}
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
			if(rowNum<=0)
				return false;
		
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
			return false;
			sheet = (XSSFSheet) workbook.getSheetAt(index);
		
			// get column number for colName
			Row row0 = sheet.getRow(0);
			int colNum = -1;
			for (int j=0; j< row0.getLastCellNum(); j++) {
				cell = (XSSFCell) row0.getCell(j);
				String cellValue = getCellValueAsString(cell);
				if(cellValue.equalsIgnoreCase(colName)) {
					colNum = j;
					break;
				}
			}
			if(colNum==-1)
				return false;

			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum-1);
			if (row == null)
				row = sheet.createRow(rowNum-1);
		
			cell = row.getCell(colNum);	
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);

			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		
		sheet = (XSSFSheet) workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
	// returns true if sheet is created successfully else false
	public boolean addSheet(String  sheetname){						
		try {
			workbook.createSheet(sheetname);	
			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
			}
			return true;
		}
	
	// returns true if sheet is removed successfully else false if sheet does not exist
	public boolean removeSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		try {
			workbook.removeSheetAt(index);
			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName){					
		try{				
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
					return false;
			XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					
			sheet=(XSSFSheet) workbook.getSheetAt(index);
					
			row = (XSSFRow) sheet.getRow(0);
			if (row == null)
				row = (XSSFRow) sheet.createRow(0);
					
			if(row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colName);
			cell.setCellStyle(style);
			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();		    
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
			return true;
		}
		
	//  returns true if column is removed successfully
	public boolean removeColumn(String sheetName, String colName) {
		try{
			if(!isSheetExist(sheetName))
				return false;

			sheet=(XSSFSheet) workbook.getSheet(sheetName);	
			// get column number for colName
			Row row0 = sheet.getRow(0);
			int colNum = -1;
			for (int j=0; j< row0.getLastCellNum(); j++) {
				cell = (XSSFCell) row0.getCell(j);
				String cellValue = getCellValueAsString(cell);
				if(cellValue.equalsIgnoreCase(colName)) {
					colNum = j;
					break;
				}
			}
				
			for(int i =0;i< getRowCount(sheetName);i++){
				row=sheet.getRow(i);	
					if(row!=null){
						cell=row.getCell(colNum);
						if(cell!=null){
							row.removeCell(cell);
						}
					}
				}
			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();	
			}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
			return true;
				
		}
	
	//  returns true if column is removed successfully
	public boolean removeColumn(String sheetName, int colNumber) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		
		sheet=(XSSFSheet) workbook.getSheet(sheetName);
				
		for(int i =0;i< getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNumber);
				if(cell!=null){
					row.removeCell(cell);
				}
			}
		}
		outputStream = new FileOutputStream(fileName);
		workbook.write(outputStream);
		outputStream.close();	
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	  // find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
				return true;
		}

}
