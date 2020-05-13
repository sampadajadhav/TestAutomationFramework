package TestAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {


	public Sheet ReadExcel(String filePath,String fileName,String sheetName) throws IOException {


		File file = new File(filePath + "\\TestData\\" + fileName);

		FileInputStream FS = new FileInputStream(file);
		//Creats the generic workbook for xlsx and xls
		Workbook TestcaseWorkbook = null;

		// condition to handle workbook for xlsx. and xls
		String FileExtnName = fileName.substring(fileName.indexOf("."));

		if (FileExtnName.equalsIgnoreCase(".xlsx")) {
			TestcaseWorkbook = new XSSFWorkbook(FS);
		}

		else if (FileExtnName.equalsIgnoreCase(".xls")) {
			TestcaseWorkbook = new HSSFWorkbook(FS);

		}
		
		//Read the sheet in side the workbook declared null by the sheetName provided in string parameter
		
		Sheet TestcaseDataSheet = TestcaseWorkbook.getSheet(sheetName);
		
		return TestcaseDataSheet;
		

	}

}
