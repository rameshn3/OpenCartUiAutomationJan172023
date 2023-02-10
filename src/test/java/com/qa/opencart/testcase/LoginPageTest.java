package com.qa.opencart.testcase;

import org.testng.annotations.Test;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class LoginPageTest extends TestBase {
	private Logger log = LogManager.getLogger(LoginPageTest.class.getName());

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		log.info("initialising the page class objects");
		homePg = new HomePage(driver);
		regPg = new RegistrationPage(driver);
		loginPg = new LoginPage(driver);
		myaccountPg = new MyAccountPage(driver);
		logoutPg = new LogoutPage(driver);
		forgotpwdPg = new ForgotPasswordPage(driver);
		homePg.goToLoginPage();
	}

	@BeforeMethod
	public void waitSetup() {
		loginPg.waitForPageLoad(2000);
	}

	@Description("Login page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Asserting login page title", priority = 1)
	public void verifyLoginPageTitleTest() {
		log.info("Verify the Login page title");
		Assert.assertEquals(loginPg.getTitle(), Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Login page Url Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Asserting login page Url", priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPg.getLoginPageUrl().contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}

	@Description("isRegisterAccntTextAvailable() Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "isNewCustomerHeaderAvailable", priority = 3)
	public void isRegisterAccntTextExistTest() {
		Assert.assertTrue(loginPg.isRegisterAccntTextAvailable());
	}

	@Description("isNewCustomerHeaderAvailable Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "isNewCustomerHeaderAvailable", priority = 4)
	public void isNewCustomerHeaderExistTest() {
		Assert.assertTrue(loginPg.isNewCustomerHeaderAvailable());
	}

	@Description("isNewCustomerContinueBtnAvailable Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "isNewCustomerContinueBtnAvailable", priority = 5)
	public void isNewCustomerContinueBtnAvailableExistTest() {
		Assert.assertTrue(loginPg.isNewCustomerContinueBtnAvailable());
	}

	@Description("isReturningCustomerHeaderAvailable Test")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "isReturningCustomerHeaderAvailable", priority = 6)
	public void isReturningCustomerHeaderExistTest() {
		Assert.assertTrue(loginPg.isReturningCustomerHeaderAvailable());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 7)
	public void goToForgotPasswordTest() {
		loginPg.goToForgotPasswordPage();
		forgotpwdPg.navigateBrowserBack();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 8)
	public void goToRegistrationPageTest() {
		loginPg.clickNewCustomerContinueBtn();
		regPg.navigateBrowserBack();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "login to the open cart page", priority = 9)
	public void loginTest() throws InterruptedException {
		loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
		myaccountPg.waitForPageLoad(2000);
		Assert.assertEquals(myaccountPg.getTitle(), Constants.MY_ACCOUNT_PAGE_TITLE);
	}

	@AfterClass
	public void logoutTest() throws InterruptedException {
		myaccountPg.clickLogoutLink();
		logoutPg.waitForPageLoad(2000);
		Assert.assertEquals(logoutPg.getTitle(), Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
		log.info("Click on Continue button in logout page");
		logoutPg.clickContinueBtn();

		homePg.waitForPageLoad(2000);
		Assert.assertEquals(homePg.getTitle(), Constants.HOME_PAGE_TITLE);
	}

}
