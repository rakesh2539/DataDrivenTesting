package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Object[][] getTestData(String excelPath, String sheetName) throws IOException {
	    FileInputStream fis = new FileInputStream(excelPath);
	    XSSFWorkbook wb = new XSSFWorkbook(fis);
	    XSSFSheet sheet = wb.getSheet(sheetName);

	    int rowCount = sheet.getPhysicalNumberOfRows();
	    int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	    Object[][] data = new Object[rowCount - 1][colCount];

	    // Fill the data array with values from the Excel sheet
	    for (int i = 1; i < rowCount; i++) {  // Skip header row
	        for (int j = 0; j < colCount; j++) {
	            XSSFCell cell = sheet.getRow(i).getCell(j);
	            if (cell == null) {
	                data[i - 1][j] = "";  // Assign empty string or default value if cell is null
	            } else {
	                // Check if it's a string or handle other types accordingly
	                switch (cell.getCellType()) {
	                    case STRING:
	                        data[i - 1][j] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
	                        break;
	                    case BOOLEAN:
	                        data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
	                        break;
	                    default:
	                        data[i - 1][j] = "";  // Handle other cell types if needed
	                }
	            }
	        }
	    }

	    wb.close();
	    fis.close();

	    return data;
	}
}