package TestCase;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestAutomation.BaseFunctionsUtility;
import TestAutomation.ExcelUtility;
import TestAutomation.ReadObjectProperty;

public class ExecuteDriverTest{

	WebDriver driver = null;

	private static Logger log = LogManager.getLogger(ExecuteDriverTest.class.getName());

	@Test(dataProvider = "TestData")

	public void ExecuteTest(String TestCaseName, String keyword, String ObjectName, String ObjectType, String Value)
			throws Exception {

		if (TestCaseName != null && TestCaseName.length() != 0) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
			//created object for readobjectproperty class
			ReadObjectProperty object = new ReadObjectProperty();
			Properties prop = object.getObjectRepository();
			//created object for BaseFunctionsUtility class
			BaseFunctionsUtility operation = new BaseFunctionsUtility(driver);
			operation.perform(prop, keyword, ObjectName, ObjectType, Value);
			log.info("Keyword executed successfully");
		}
	

	@DataProvider(name = "TestData")
	public Object[][] gettestdata() throws IOException {
		Object[][] object = null;
		ExcelUtility Excelfile = new ExcelUtility();
		// Read TestCase sheet
		Sheet testcasedataSheet = Excelfile.ReadExcel(System.getProperty("user.dir") + "\\", "TestCase.xlsx", "TestCase");
		int rowcount = testcasedataSheet.getLastRowNum() - testcasedataSheet.getFirstRowNum();
		object = new Object[rowcount][5];
		for (int i = 0; i < rowcount; i++) {
			Row row = testcasedataSheet.getRow(i+1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				object[i][j] = row.getCell(j).toString();
			}
		}
		log.info("Test Data Imported succssfully");		
		return object;
	}
}