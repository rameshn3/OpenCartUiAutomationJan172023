package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

import io.qameta.allure.Step;

public class LoginPage extends WebDriverUtils{

	private Logger log=LogManager.getLogger(LoginPage.class.getName());
	JavaScriptUtils jsUtils;
	public LoginPage(WebDriver driver) {
		super(driver);
		jsUtils=new JavaScriptUtils(driver) ;
	}

	@FindBy(xpath="//ul[@class='breadcrumb']/li[3]/a[text()='Login']")
	private WebElement loginBreadCrumb;
	
	@FindBy(css="div.well>h2")
	private WebElement newCustomerHeader;
	
	@FindBy(xpath = "//strong[contains(.,'Register Account')]")
	private WebElement registerAccntText;
	
	@FindBy(css="a.btn.btn-primary")
	private WebElement newCustomerContinueBtn;
	
	@FindBy(xpath = "//h2[contains(.,'Returning Customer')]")
	private WebElement returningCustomerHeader;
	
	@FindBy(css="#input-email")
	private WebElement emailAddressEditbox;
	
	@FindBy(css = "#input-password")
	private WebElement passwordEditbox;
	
	@FindBy(linkText="Forgotten Password")
	private WebElement forgottenPasswordLink;
	
	@FindBy(xpath = "//input[contains(@value,'Login')]")
	private WebElement loginBtn;
	@FindBy(xpath = "//i[contains(@class,'fa fa-home')]")
	private WebElement loginHomeIcon;
	
	@Step("Click on Breadcrumb home icon")
	public void clickHomeIcon() throws InterruptedException {
		log.info("clickHomeIcon");
		jsUtils.clickElementByJS(loginHomeIcon);
	}
	
	@Step("Get Login page current address bar url")
	public String getLoginPageUrl() {
		return waitForUrlContains(Constants.LOGIN_PAGE_FRACTION_URL);
	}
	
	@Step("login with username:{0} and password: {1}")
	public void doLogin(String email,String pwd) throws InterruptedException {
		log.info("Performing the login to the website");
		sendData(emailAddressEditbox, email);
		sendData(passwordEditbox, pwd);
		log.info("click on Login button");
		click(loginBtn);
	}
	
	public void goToForgotPasswordPage() {
		log.info("click on forgot password");
		try {
			click(forgottenPasswordLink);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Step("Click on New Customer section Continue button")
	public void clickNewCustomerContinueBtn() {
		log.info("click on forgot password");
		try {
			click(newCustomerContinueBtn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean isNewCustomerHeaderAvailable() {
		return isDisplayed(newCustomerHeader);
	}
	public boolean isRegisterAccntTextAvailable() {
		return isDisplayed(registerAccntText);
	}
	public boolean isNewCustomerContinueBtnAvailable() {
		return isDisplayed(newCustomerContinueBtn);
	}
	public boolean isReturningCustomerHeaderAvailable() {
		return isDisplayed(returningCustomerHeader);
	}
}
