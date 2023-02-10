package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

public class LogoutPage extends WebDriverUtils{

	private Logger log=LogManager.getLogger(LogoutPage.class.getName());
	JavaScriptUtils jsUtils;
	public LogoutPage(WebDriver driver) {
		super(driver);
		jsUtils=new JavaScriptUtils(driver) ;
	}

	@FindBy(css="div#content>h1")
	private WebElement accountLogoutHeader;
	@FindBy(css="div#content>p")
	private WebElement accountLoggedOffMsg;
	@FindBy(css="a.btn.btn-primary")
	private WebElement continueBtn;

	public boolean isAccountLogoutHeaderExists() {
		waitForPageLoad(2000);
		return accountLogoutHeader.isDisplayed();
	}
	
	public boolean isAccountLoggedOffMsgExists() {
		waitForPageLoad(2000);
		return accountLoggedOffMsg.isDisplayed();
	}
	
	public String getlogoutPageTitle() {
		waitForPageLoad(2000);
		return getTitle();
	}
	
	public void clickContinueBtn() throws InterruptedException {
		click(continueBtn);
	}
}
