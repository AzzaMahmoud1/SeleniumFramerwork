package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "login")
	public WebElement signInLink;
	
	@FindBy(id = "email")
	public WebElement emailfield;
	
	@FindBy(id = "passwd")
	public WebElement passwdfield;
	
	@FindBy(id = "SubmitLogin")
	public WebElement signInBtn;
	
	

	public void OpenCreateUserPage() {
		clickButton(signInLink);
	}
	
	public void login(String email, String Pass) {
	emailfield.sendKeys(email);
	passwdfield.sendKeys(Pass);
	signInBtn.click();
	
	}

}
