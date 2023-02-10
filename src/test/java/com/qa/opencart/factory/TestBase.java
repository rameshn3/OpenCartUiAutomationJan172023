package com.qa.opencart.factory;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.opencart.pages.ForgotPasswordPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.Constants;

public class TestBase {
	public HomePage homePg;
	public RegistrationPage regPg; 
	public LoginPage loginPg;
	public LogoutPage logoutPg;
	public MyAccountPage myaccountPg;
	public ForgotPasswordPage forgotpwdPg;
	public ResultsPage resultPg;
	public ProductDetailsPage productDetailPg;
	public static WebDriver driver;
	public ResourceBundle rb;// to read config.properties
	public WebDriverWait wait;
	private static final Logger log = LogManager.getLogger(TestBase.class.getName());
	
	@Parameters({"browser"})
	@BeforeClass
	public void commonSetUp(@Optional("firefox") String browser) throws IOException {
		rb = ResourceBundle.getBundle("config");// Load config.properties
		log.debug("setting the driver");
		
		driver = WebDriverFactory.getInstance().getDriver(browser);
		
		log.info("create object for WebDriverWait class");

		wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
		// get url from config.properties file
		log.debug("open the application url :" + rb.getString("appUrl"));

		driver.get(rb.getString("appUrl"));



	}



	@AfterClass

	public void commonTearDown() {

		log.debug("close the browser");

		if (driver != null) {

			WebDriverFactory.getInstance().quitDriver();



		}



	}

}

