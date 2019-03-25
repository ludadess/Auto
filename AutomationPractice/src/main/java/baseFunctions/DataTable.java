package baseFunctions;

import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable {
	private static Logger log = LogManager.getLogger(DataTable.class.getName());
	private static Map<String, String> DataTable = new HashMap<String, String>();

	public static void Import(String FileName, String SheetName, int RowNumber) {

		// Create an object of File class to open xlsx file
		File file = new File(FileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Workbook excelWorkbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = FileName.substring(FileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			try {
				excelWorkbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			try {
				excelWorkbook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// search for a sheet by SheetName
		boolean sheetFound = false;
		int sheetsCount = excelWorkbook.getNumberOfSheets();
		for (int i = 0; i < sheetsCount; i++) {
			if (excelWorkbook.getSheetName(i).equalsIgnoreCase(SheetName)) {// get sheet
				sheetFound = true;
				Sheet excelSheet = excelWorkbook.getSheetAt(i);
				// Read row0
				Row row0 = excelSheet.getRow(0);
				// Read rowNumber and store in map as a key (column name) and a value (cell
				// string value)
				if (RowNumber <= excelSheet.getLastRowNum()) {
					Row row1 = excelSheet.getRow(RowNumber);
					for (int j = 0; j < row0.getLastCellNum(); j++) {
						Cell cell1 = row1.getCell(j);
						DataTable.put(row0.getCell(j).getStringCellValue(), getCellValueAsString(cell1));
						// Print Excel data in console
						// System.out.println(row0.getCell(j).getStringCellValue() + " = " +
						// getCellValueAsString(cell1));
					}
				} else {
					log.error("Total number of rows is " + excelSheet.getLastRowNum() + ". There is no row with number "
							+ RowNumber);
					System.exit(0);
				}
			}
			if (!sheetFound) {
				log.error("There is no sheet with name " + SheetName);
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
			}
		} else
			strCellValue = "";
		return strCellValue;
	}
}
