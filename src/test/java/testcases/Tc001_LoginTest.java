package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.LoginPage;
import pageobjects.SearchHotel_Page;
import utils.DataproviderManager;

public class Tc001_LoginTest extends BaseClass {



    // Test method using the data provider
    @Test(dataProvider = "logindata1",dataProviderClass = DataproviderManager.class)
    public void Validate_login(String username, String password) {
        logger.info("***Validate_login testcase is started****");

        try {
            LoginPage loginpage = new LoginPage(driver);

            // Enter username and password from the Excel data
            
            loginpage.Enterusername(username);
            logger.info("username is Entered");

            loginpage.Enterpasswrod(password);
            logger.info("password is Entered");

            loginpage.click_login();
            logger.info("login button is clicked");
            
            SearchHotel_Page shp=new SearchHotel_Page(driver);
    
            Assert.assertEquals(driver.getTitle(), "Adactin.com - Search Hotel");
            }
            catch(Exception e) {
        	e.printStackTrace();
       }
}
}