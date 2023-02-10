package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

public class RegistrationPage extends WebDriverUtils{
	JavaScriptUtils jsUtils;
	private Logger log=LogManager.getLogger(RegistrationPage.class.getName());
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
		jsUtils=new JavaScriptUtils(driver);
	}

	@FindBy(css="h1")
	private WebElement registerAccntHeader;
	
	@FindBy(css="div#content>p")
	private WebElement registerAccntStaticTxt;
	
	@FindBy(css="div#content>p>a")
	private WebElement registerAccntLoginPageLink;
	
	@FindBy(css="fieldset#account>legend")
	private WebElement personalDetailsLegendTxt;
	@FindBy(id="input-firstname")
	private WebElement firstNameEditbox;
	@FindBy(name="lastname")
	private WebElement lastNameEditbox;
	@FindBy(id="input-email")
	private WebElement emailEditbox;
	@FindBy(css="#input-telephone")
	private WebElement telephoneEditbox;
	@FindBy(css="#input-fax")
	private WebElement faxEditbox;
	//Your Address Section Fields
	@FindBy(css="fieldset#address>legend")
	private WebElement addressLegendTxt;
	@FindBy(id="input-company")
	private WebElement companyEditbox;
	@FindBy(id="input-address-1")
	private WebElement address1Editbox;
	@FindBy(id="input-address-2")
	private WebElement address2Editbox;
	@FindBy(css="#input-city")
	private WebElement cityEditbox;
	@FindBy(css="#input-postcode")
	private WebElement postcodeEditbox;
	@FindBy(css="#input-country")
	private WebElement countryDropdown;
	@FindBy(css="#input-zone")
	private WebElement stateDropdown;
	
	@FindBy(css="#input-password")
	private WebElement passwordEditbox;
	@FindBy(css="input[id='input-confirm']")
	private WebElement passwordConfirmEditbox;
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	private WebElement subscribeYesRadioBtn;
	@FindBy(xpath="//input[@name='newsletter' and @value='0']")
	private WebElement subscribeNoRadioBtn;
	@FindBy(css="a.agree")
	private WebElement privacyPolicyLink;
	@FindBy(css="input[name='agree']")
	private WebElement privacyPolicyCheckbox;
	@FindBy(css="input.btn.btn-primary")
	private WebElement continueBtn;
	@FindBy(xpath = "//i[contains(@class,'fa fa-home')]")
	private WebElement regHomeIcon;
	
	@FindBy(css="div.alert.alert-danger.alert-dismissible")
	private WebElement alreadyRegAccErrMsg;
	
	@FindBy(css="div#content>h1")
	private WebElement accntCreatedHeader;
	
	@FindBy(css="div#content>p")
	private WebElement accntCreatedSuccMsg;
	
	@FindBy(css="a.btn.btn-primary")
	private WebElement accntCreatedcontinueBtn;
	
	public String getAlreadyRegisteredAccountErrMsg() throws InterruptedException {
		return getText(alreadyRegAccErrMsg);
	}
	
	public String getAccountCreatedHeader() throws InterruptedException {
		return getText(accntCreatedHeader);
	}
	
	public String getAccountCreatedSuccMsg() throws InterruptedException {
		return getText(accntCreatedSuccMsg);
	}
	
	
	public void setFirstName(String fname) throws InterruptedException {
		log.info("enetering the firstname value");
		sendData(firstNameEditbox, fname);
	}
	
	public void setLastName(String lname) throws InterruptedException {
		log.info("enetering the larstname value");
		sendData(lastNameEditbox, lname);
	}
	
	public void setEmail(String email) throws InterruptedException {
		log.info("enetering the email value");
		sendData(emailEditbox, email);
	}
	public void setTelePhone(String telephone) throws InterruptedException {
		log.info("enetering the telephone value");
		sendData(telephoneEditbox, telephone);
	}
	public void setPassword(String pwd) throws InterruptedException {
		log.info("enetering the pwd value");
		sendData(passwordEditbox, pwd);
	}
	public void setConfirmPassword(String confirmPwd) throws InterruptedException {
		log.info("enetering the confirm value");
		sendData(passwordConfirmEditbox, confirmPwd);
	}
	public void selectSubScribe(String subScribe) throws InterruptedException {
		log.info("enetering the subscribe value");
		if(subScribe.equalsIgnoreCase("Yes")) {
			log.info("select Yes radio buton");
			click(subscribeYesRadioBtn);
		}else {
			log.info("select No radio buton");
			click(subscribeNoRadioBtn);
		}
	}
	public void checkAgreeCheckbox() throws InterruptedException {
		log.info("check Agree Checkbox");
		check(privacyPolicyCheckbox);
	}
	public void clickContinueBtn() throws InterruptedException {
		log.info("click on Continue button");
		click(continueBtn);
	}
	public void clickHomeIcon() throws InterruptedException {
		log.info("clickHomeIcon");
		jsUtils.clickElementByJS(regHomeIcon);
	}
	
	public void clickAccountCreatedContinueBtn() throws InterruptedException {
		log.info("click on AccountCreatedContinue button");
		click(accntCreatedcontinueBtn);
	}
	
	public void setFax(String fax) throws InterruptedException {
		log.info("eneter the fax value in fax editbox");
		sendData(faxEditbox, fax);
	}
	
	public void setCompany(String company) throws InterruptedException {
		if(!company.isEmpty()) {
		log.info("eneter the company value in company editbox");
		sendData(companyEditbox, company);
		}
	}
	
	public void setAddress1(String address1) throws InterruptedException {
		log.info("eneter the address1 value in address1 editbox");
		sendData(address1Editbox, address1);
	}
	public void setAddress2(String address2) throws InterruptedException {
		log.info("eneter the address2 value in address2 editbox");
		sendData(address2Editbox, address2);
	}
	
	public void setCity(String city) throws InterruptedException {
		log.info("eneter the city value in city editbox");
		sendData(cityEditbox, city);
	}
	public void setPostCode(String postcode) throws InterruptedException {
		log.info("eneter the postcode value in postcode editbox");
		sendData(postcodeEditbox, postcode);
	}
	
	public void selectCountry(String optionTxt) {
		log.info("Select an option From country dropdown by label");
		selectOptionByVisibleText(countryDropdown, optionTxt);
	}
	
	public void selectState(String visibleText) {
		log.info("Select an option From State dropdown by value Attribute");
		waitForPageLoad(2000);
		selectOptionByVisibleText(stateDropdown, visibleText);
	}
	
	public void fillAddressDetails(String company,String addr1,String addr2,String city,String postcode,String optiontxt,String attrval) throws InterruptedException {
		try {
			setCompany(company);
			setAddress1(addr1);
			setAddress2(addr2);
			setCity(city);
			setPostCode(postcode);
			selectCountry(optiontxt);
			selectState(attrval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
