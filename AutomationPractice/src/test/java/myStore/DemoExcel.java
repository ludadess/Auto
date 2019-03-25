package myStore;

import org.testng.annotations.Test;

import baseFunctions.DataTable;

public class DemoExcel {
	
	@Test
	public void Practice() {
		DataTable.Import("C:/Work/eclipse-workspace/AutomationPractice/AutomationPractice/MyStoreApp.xlsx", "PersonalInfo", 3);
		System.out.println(DataTable.Value("Password1"));
		System.out.println(DataTable.Value("LastName"));
		System.out.println(DataTable.Value("DayDOB"));
		System.out.println(DataTable.Value("ZipCode"));
		
	}

}
