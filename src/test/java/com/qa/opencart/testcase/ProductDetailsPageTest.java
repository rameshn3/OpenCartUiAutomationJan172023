package com.qa.opencart.testcase;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ProductDetailsPageTest extends TestBase {
	private Logger log = LogManager.getLogger(ProductDetailsPageTest.class.getName());

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		log.info("initialising the page class objects");
		homePg = new HomePage(driver);
		regPg = new RegistrationPage(driver);
		loginPg = new LoginPage(driver);
		myaccountPg = new MyAccountPage(driver);
		logoutPg = new LogoutPage(driver);
		resultPg=new ResultsPage(driver);
		 productDetailPg=new ProductDetailsPage(driver);
		homePg.goToLoginPage();
		loginPg.doLogin(rb.getString("username"), rb.getString("pwd"));
		
	}

	@BeforeMethod
	public void waitSetup() {
		myaccountPg.waitForPageLoad(2000);
	}

	@DataProvider
	public Object[][] getProductTestData(){
		
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Apple","Apple Cinema 30\""}
			
		};
	}
	
	
	@Test(dataProvider="getProductTestData")
	public void productHeaderTest(String key,String productName) throws InterruptedException {
		resultPg=myaccountPg.doProductSearch(key);
		productDetailPg=resultPg.selectProduct(productName);
		String productHeaderName=productDetailPg.getProductName();
		Assert.assertEquals(productHeaderName, productName);
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData(){
		
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Apple","Apple Cinema 30\"",6}
			
		};
	}
	
	
	@Test(dataProvider="getProductImagesTestData")
	public void productImagesTest(String key,String productName,int imgCount) throws InterruptedException {
		resultPg=myaccountPg.doProductSearch(key);
		productDetailPg=resultPg.selectProduct(productName);
		int actProductImageCount=productDetailPg.getProductImageCount();
		Assert.assertEquals(actProductImageCount, imgCount);
	}
	
	@Test
	public void productMetaDataTest() throws InterruptedException {
		resultPg=myaccountPg.doProductSearch("MacBook");
		productDetailPg=resultPg.selectProduct("MacBook Pro");
		Map<String,String>actualProductInfoMap=productDetailPg.getProductInformation();	
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actualProductInfoMap.get("actualprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
  @AfterClass
  public void afterClass() throws InterruptedException {
	  myaccountPg.clickLogoutLink();
		logoutPg.waitForPageLoad(2000);
		Assert.assertEquals(logoutPg.getTitle(), Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
		log.info("Click on Continue button in logout page");
		logoutPg.clickContinueBtn();

		homePg.waitForPageLoad(2000);
		Assert.assertEquals(homePg.getTitle(), Constants.HOME_PAGE_TITLE);
  }

}
