package com.qa.opencart.testcase;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.opencart.factory.TestBase;
import com.qa.opencart.factory.WebDriverFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

import java.io.IOException;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class RegistrationTest extends TestBase {
	private Logger log = LogManager.getLogger(RegistrationTest.class.getName());
	String fname;
	String lname;
	String telephone;
	String fax;
	String compName;
	String addr1;
	String addr2;
	String city;
	String postcode;
	String country;
	String state;
	
	
	@BeforeClass
	public void setupRegData() {
	Faker fkobj=new Faker(new Locale("en-US"));
	fname=fkobj.address().firstName();
	lname=fkobj.address().lastName();
	//email=fkobj.internet().emailAddress();
	telephone=fkobj.phoneNumber().cellPhone();
	fax=fkobj.phoneNumber().phoneNumber();
	compName=fkobj.company().name();
	addr1=fkobj.address().buildingNumber();
	addr2=fkobj.address().latitude();
	city=fkobj.address().city();
	postcode=fkobj.address().zipCode();
	System.out.println("postcode is:"+postcode);
	country="United States";
	System.out.println("country is:"+country);
	state=fkobj.address().state();
	System.out.println("country state is:"+state);
	
	}
	
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		log.info("initialising the page class objects");
		homePg = new HomePage(driver);
		regPg = new RegistrationPage(driver);
		myaccountPg = new MyAccountPage(driver);
		logoutPg=new LogoutPage(driver); 
	}

	@BeforeMethod
	public void preCondition() throws InterruptedException {
		log.info("navigate to registration page");
		homePg.navigateToRegisterPage();
		log.info("Verify the Registration page title");
		regPg.waitForPageLoad(2000);
		Assert.assertEquals(regPg.getTitle(), Constants.REGISTRATION_PAGE_TITLE);
	}

	private String getRandomEmail() {
		return WebDriverFactory.randomeString() + "@gmail.com";
	}
	
	@Test
	public void registrationTest() throws InterruptedException {
		try {

			String fname = WebDriverFactory.randomeString();
			log.info("setting the first name:" + fname);
			regPg.setFirstName(fname);
			String lname = WebDriverFactory.randomeString();
			log.info("setting the last name:" + lname);
			regPg.setLastName(lname);
			String email = getRandomEmail();
			log.info("setting the email:" + email);
			regPg.setEmail(email);
			String telephone = WebDriverFactory.randomeNumber();
			log.info("setting the telephone:" + telephone);
			regPg.setTelePhone(telephone);
			String pwd = WebDriverFactory.randomAlphaNumeric();
			log.info("Entering the Address details ....");
					 
			regPg.fillAddressDetails(compName, addr1, addr2, city, postcode, country, state);
			log.info("setting the password value is:" + pwd);
			regPg.setPassword(pwd);
			regPg.setConfirmPassword(pwd);
			log.info("select the subscribe option yes");
			regPg.selectSubScribe("Yes");
			log.info("check the Privacy policy checkbox");
			regPg.checkAgreeCheckbox();
			log.info("Click on Continue button");
			regPg.clickContinueBtn();
			regPg.waitForPageLoad(2000);
			log.info("verify the Account Creation success message and  header ");
			Assert.assertEquals(regPg.getAccountCreatedHeader(), Constants.YOUR_ACCNT_CREATED_HEADER);
			Assert.assertEquals(regPg.getAccountCreatedSuccMsg(), Constants.YOUR_ACCNT_CREATED_SUCC_MSG);
			log.info("click on Account Created Continue button");
			regPg.clickAccountCreatedContinueBtn();
			myaccountPg.waitForPageLoad(2000);
			
		} catch (InterruptedException e) {
			log.info("Account creation failed");
			e.printStackTrace();
		}

	}
	
	
	  @DataProvider public Object[][] regTestData() throws InvalidFormatException,
	  IOException{ Object[][] data=new
	  ExcelUtils().getTestData(Constants.TEST_DATA_FILE_PATH,
	  Constants.REGISTER_SHEET_NAME); return data; }
	  
	  
	  
	  @Test(dataProvider="regTestData") 
	  public void registrationDataDrivenTest(String fname,String lname,String
	  telephone,String pwd,String subscribe) throws InterruptedException {
	  log.info("setting the first name:" + fname); 
	  regPg.setFirstName(fname);
	  log.info("setting the last name:" + lname);
	  regPg.setLastName(lname); 
	  String email = getRandomEmail();
	  log.info("setting the email:" + email);
	  regPg.setEmail(email); 
	  log.info("setting the telephone:" + telephone);
	  regPg.setTelePhone(telephone); 
	  regPg.fillAddressDetails(compName, addr1,
	  addr2, city, postcode, country, state);
	  log.info("setting the password value is:" + pwd);
	  regPg.setPassword(pwd);
	  regPg.setConfirmPassword(pwd); 
	  log.info("select the subscribe option yes");
	  regPg.selectSubScribe(subscribe);
	  log.info("check the Privacy policy checkbox");
	  regPg.checkAgreeCheckbox();
	  log.info("Click on Continue button"); 
	  regPg.clickContinueBtn();
	  regPg.waitForPageLoad(2000);
	  log.info("verify the Account Creation success message and  header ");
	  Assert.assertEquals(regPg.getAccountCreatedHeader(),
	  Constants.YOUR_ACCNT_CREATED_HEADER);
	  Assert.assertEquals(regPg.getAccountCreatedSuccMsg(),
	  Constants.YOUR_ACCNT_CREATED_SUCC_MSG);
	  log.info("click on Account Created Continue button");
	  regPg.clickAccountCreatedContinueBtn(); myaccountPg.waitForPageLoad(2000);
	  
	  }
	 
	@AfterMethod
	public void tearDown() throws InterruptedException {
		log.info("validate the my Account Page title");
		Assert.assertEquals(myaccountPg.getTitle(), Constants.MY_ACCOUNT_PAGE_TITLE);
		log.info("click on logout link");
		myaccountPg.clickLogoutLink();
		log.info("validate the logout Page title");
		Assert.assertEquals(logoutPg.getlogoutPageTitle(), Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
		log.info("Click on Continue button in Logout page");
		logoutPg.clickContinueBtn();
	}
	
	
}
