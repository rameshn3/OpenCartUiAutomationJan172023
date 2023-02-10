package com.qa.opencart.testcase;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class MyAccountPageTest extends TestBase {
	private Logger log = LogManager.getLogger(MyAccountPageTest.class.getName());

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		log.info("initialising the page class objects");
		homePg = new HomePage(driver);
		regPg = new RegistrationPage(driver);
		loginPg = new LoginPage(driver);
		myaccountPg = new MyAccountPage(driver);
		logoutPg = new LogoutPage(driver);
		 resultPg=new ResultsPage(driver);
		homePg.goToLoginPage();
		loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
		
	}

	@BeforeMethod
	public void waitSetup() {
		myaccountPg.waitForPageLoad(2000);
	}
 
  @Severity(SeverityLevel.TRIVIAL)
  @Test(description="my account page title verification",priority=1)
  public void accountPageTitleTest() {
	  Assert.assertEquals(myaccountPg.getTitle(), Constants.MY_ACCOUNT_PAGE_TITLE);
  }
  
  @Severity(SeverityLevel.TRIVIAL)
  @Test(description="my account page url verification",priority=2)
  public void accountPageUrlTest() {
	  Assert.assertTrue(myaccountPg.getMyAccountPageUrl().contains(Constants.ACC_PAGE_FRACTION_URL));
  }
  
  @Severity(SeverityLevel.TRIVIAL)
  @Test(description="my account page search editbox exists Test",priority=3)
  public void accountPageSearchExistTest() {
	  Assert.assertTrue(myaccountPg.isSearchExist());
  }
  
  @Severity(SeverityLevel.TRIVIAL)
  @Test(description="my account page logout link exists Test",priority=4)
  public void accountPageLogoutExistTest() throws InterruptedException {
	  Assert.assertTrue(myaccountPg.isLogoutExist());
  }
  @Severity(SeverityLevel.MINOR)
  @Test(description="my account menu option list verification",priority=5)
  public void myAccountMenuOptionTest() {
	  Assert.assertEquals(myaccountPg.getMyAccountMenuOptionList(), Constants.EXPECTED_MYACC_MENU_OPTS_LIST);
  }
  
  @Severity(SeverityLevel.NORMAL)
  @Test(description="my account headers list verification",priority=6)
  public void myAccountHeaderTest() {
	  Assert.assertEquals(myaccountPg.getMyAccountHeaderList(), Constants.EXPECTED_MYACC_HEADER_LIST);
  }
  
  @DataProvider(name="products")
  public Object[][] productTestData(){
	  
	 // Object[][] data=new Object[3][1]
	return   new Object[][] {
		{"MacBook"},
		{"iMac"},
		{"Samsung"}
	};
  }
  
  
  @Severity(SeverityLevel.NORMAL)
  @Test(description="perform product search from my account page",priority=7,dataProvider="products")
  public void productSearchTest(String productName) throws InterruptedException {
	  resultPg=myaccountPg.doProductSearch(productName);
	  String actualresultTitle=resultPg.getTitle();
	  log.info("Search results page title:"+actualresultTitle);
	  new SoftAssert().assertEquals(actualresultTitle, "Search - "+productName);	
	  Assert.assertTrue(resultPg.getSearchProductsSize()>0);
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
