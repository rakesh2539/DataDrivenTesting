package utils;

import org.testng.annotations.DataProvider;

public class DataproviderManager {

	
	//for every test we need to specify the File path and sheet name
	
	   public static String  path = System.getProperty("user.dir") + "\\TestData\\logindata.xlsx";
	   public static String  SHEET_NAME = "Sheet1";
	   public static String  SHEET_NAME2 = "Sheet2";
       
	   
	   //DataProvider:1

	    @DataProvider(name = "logindata1")
	    public Object[][] getLoginData() throws Exception {
	        return ExcelReader.getTestData(path, SHEET_NAME);
	    }
	
	   //DataProvider:2
	    
	    @DataProvider(name = "logindata2")
	    public Object[][] getLoginData2() throws Exception {
	        return ExcelReader.getTestData(path, SHEET_NAME2);
	    }
	
	
	

}
