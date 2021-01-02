package tests;

import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import pages.AuthenticateNewUserPage;
import pages.CreateAccountPage;
import pages.DashBoardPage;
import pages.HomePage;

public class LoginTest extends TestBase {


	HomePage homeObject;
	CreateAccountPage CreateAccountObject;
	AuthenticateNewUserPage AuthenticateUserObject;
	DashBoardPage DashBoardPageObject;
	
	final String emailAddresss = enterEmailAddress() ;
	
	public final String enterEmailAddress() {
		FakeValuesService fakeValuesService = new FakeValuesService(
				new Locale("en-GB"), new RandomService());
		String emailAddress = fakeValuesService.bothify("????##@gmail.com");
		System.out.println(emailAddress);
	return emailAddress;
	
	}
	@Test(priority = 1, alwaysRun = true)
	public void createNewUserSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.OpenCreateUserPage();
		CreateAccountObject = new CreateAccountPage(driver);
		CreateAccountObject.CreateNewAccount(emailAddresss);
	}

	@Test(dependsOnMethods = { "createNewUserSuccessfully" })
	public void authenticateNewUser() throws InterruptedException {
		AuthenticateUserObject = new AuthenticateNewUserPage(driver);

		AuthenticateUserObject.AuthenticateUser("jack", "jack", "test@1234", "1", "May", "1993", "company", "cairo",
				"cairo", "cairo", "Arizona", "11222", "United States", "hi this is test", "1234567", "122333333",
				"cairo");
		Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?controller=my-account");
	}
	@Test(dependsOnMethods = { "authenticateNewUser" })
	public void login() {
		homeObject = new HomePage(driver);
		DashBoardPageObject = new DashBoardPage(driver);
		DashBoardPageObject.signOut(); 
		homeObject.login(emailAddresss, "test@1234");
		Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?controller=my-account");

	}

}
