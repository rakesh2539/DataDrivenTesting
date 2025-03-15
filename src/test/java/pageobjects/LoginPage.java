package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="username")
	 private WebElement usernamefiled_element;
	
	@FindBy(id="password")
	private WebElement passwordfield_element;
	
	@FindBy(id="login")
	private WebElement loginbtn_element;
	
	
	public void Enterusername(String uname) {
		usernamefiled_element.sendKeys(uname);
	}
	
	public void Enterpasswrod(String pwd) {
		passwordfield_element.sendKeys(pwd);
	}
	public void click_login() {
		loginbtn_element.click();
		
		//loginbtn_element.submit();
		
		//Actions act=new Actions(driver);
		//act.moveToElement(loginbtn_element).click().perform();
		
		//JavascriptExecutor jse=(JavascriptExecutor) driver;
		//jse.executeScript("arguments[0].click()", loginbtn_element);
		
	}
	

}
