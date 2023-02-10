package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

public class ForgotPasswordPage extends WebDriverUtils{

private Logger log=LogManager.getLogger(ForgotPasswordPage.class.getName());
JavaScriptUtils jsUtils;
public ForgotPasswordPage(WebDriver driver) {
super(driver);
jsUtils=new JavaScriptUtils(driver);
}

@FindBy(css="div#content>h1")
private WebElement forgotYourPasswordHeader;

@FindBy(xpath="//div[@id='content']/form/fieldset/legend")
private WebElement yourEmailAddressText;

@FindBy(css="#input-email")
private WebElement emailAddressEditbox;

@FindBy(css="a.btn.btn-default")
private WebElement backBtn;

@FindBy(xpath="//input[@class='btn btn-primary']")
private WebElement forgotPageContinueBtn;

public boolean isForgotYourPasswordHeaderAvailable() {
return isDisplayed(forgotYourPasswordHeader);
}

public boolean isYourEmailAddressTextHeaderAvailable() {
return isDisplayed(yourEmailAddressText);
}

public void setEmailAddress(String email) throws InterruptedException {
log.info("entering the email address");
sendData(emailAddressEditbox,email);
}

public void goToLoginPage() {
log.info("click on back page");
try {
click(backBtn);
}catch(InterruptedException e) {
e.printStackTrace();
}
}

public void clickForgotPageContinueBtn() throws InterruptedException{
log.info("click on continue");
click(forgotPageContinueBtn);
}
}
