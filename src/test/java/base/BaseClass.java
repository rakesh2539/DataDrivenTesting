package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import freemarker.template.SimpleDate;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	public FileInputStream fis;
    String propetyfile_path;
	@BeforeMethod
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		prop=new Properties();
		fis=new FileInputStream("src\\test\\resources\\Properties\\config.properties");
	    prop.load(fis);

		logger = LogManager.getLogger(this.getClass());
     
		//String br=  prop.getProperty("browser");
		
		  switch(br.toLowerCase()) { 
		  
		  case "chrome" : driver=new ChromeDriver(); break;
		  
		  case "firefox" : driver=new FirefoxDriver(); break;
		  
		  case "edge" : driver=new EdgeDriver(); break;
		  
		  default: System.out.println("no browser matched"); return;
		  
		  }
		 logger.info("*********Browser is Launched**********");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("url"));
		
		 logger.info("*********Navigated to application url**********");

		//driver.get("https://adactinhotelapp.com/");

	}

	@AfterMethod
	public void teardown() throws IOException {
		// fis.close();

		driver.quit();
		logger.info("*************Browser is Closed*************");

	}
	
	public String captureScreenShot(String testname) {
		
	String timestamp =	new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
    TakesScreenshot ts=(TakesScreenshot) driver;
    File sourcefile=ts.getScreenshotAs(OutputType.FILE);
    
    String screenshotpath=System.getProperty("user.dir")+"\\Screenshots\\"+testname+"_"+timestamp+".png";
	
    File targetfile=new File(screenshotpath);
    
    sourcefile.renameTo(targetfile);
    
    return screenshotpath;
	}
	
	

}
