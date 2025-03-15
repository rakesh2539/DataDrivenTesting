package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchHotel_Page extends BasePage {

	public SearchHotel_Page(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement Logout_btn;

	public void Logoutbutton_click() {

		Logout_btn.click();
	}

	public boolean Logout_buttonisdisplayed() {

		boolean flag = Logout_btn.isDisplayed();

		return flag;
	}
}
