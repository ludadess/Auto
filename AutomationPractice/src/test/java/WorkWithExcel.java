
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import baseFunctions.DataTable2;


public class WorkWithExcel {
	
@Test
public void testExcel() throws IOException {
	System.out.println("starting");
	String fileName = System.getProperty("user.dir")+"/DataTableDemo.xlsx";
	DataTable2 reader = new DataTable2(fileName);
	//reader.removeColumn("DataTable", "1");
	reader.removeColumn("DataTable","Company");
	//reader.addSheet("DataTable");
	//reader.addColumn("DataTable", "Company");
	//reader.addColumn("DataTable", "Contact");
	//reader.addColumn("DataTable", "Test");
	
}

	
	
}



