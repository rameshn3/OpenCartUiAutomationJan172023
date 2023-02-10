package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

import io.qameta.allure.Step;

public class MyAccountPage extends WebDriverUtils{
	private WebDriver driver;
	private Logger log=LogManager.getLogger(MyAccountPage.class.getName());
	JavaScriptUtils jsUtils;
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		jsUtils=new JavaScriptUtils(driver);
	}
	
	@FindBy(css="ul.breadcrumb>li:nth-child(2)>a")
	private WebElement myaccountBreadcrumb;
	@FindBy(css="div#content>p")
	private WebElement accountLoggedOffMsg;
	@FindBy(css="a.btn.btn-primary")
	private WebElement continueBtn;
	@FindBy(linkText="Logout")
	private WebElement logoutLink;
	@FindBy(xpath="//span[contains(.,'My Account')]")
	private WebElement myAccountMenu;
	@FindBy(name="search")
	private WebElement searchEditbox;
	@FindBy(css="button[class='btn btn-default btn-lg']")
	private WebElement searchTorchIcon;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li/a")
	private List<WebElement>myAccountMenuOptionList;
	@FindBy(xpath="//*[@id='content']/h2")
	private List<WebElement>myAccountHeaderList;
	
	
	
	@Step("click on MyAccount link")
	public void clickMyAccountLink() throws InterruptedException {
		click(myAccountMenu);
	}
	
	@Step("Get My Account page url")
	public String getMyAccountPageUrl() {
		return waitForUrlContains(Constants.ACC_PAGE_FRACTION_URL);
	}
	
	public boolean isSearchExist() {
		return waitForElementVisible(By.name("search")).isDisplayed();
	}
	
	public boolean isLogoutExist() throws InterruptedException {
		clickMyAccountLink();
		return waitForElementVisible(By.linkText("Logout")).isDisplayed();
	}
	
	@Step("click on logout link")
	public void clickLogoutLink() throws InterruptedException {
		clickMyAccountLink();
		waitForElementVisible(By.linkText("Logout"));
		click(logoutLink);
	}
	
	public List<String> getMyAccountMenuOptionList(){
		List<String>myAccMenuOptionList=new ArrayList<>();
		//iterate the myAccountMenuOptionList
		for(WebElement el:myAccountMenuOptionList) {
			String elTxt=el.getText();
			//add the element text to myAccMenuOptionList
			myAccMenuOptionList.add(elTxt);
		}
		return myAccMenuOptionList;
	}
	
	public List<String> getMyAccountHeaderList(){
		List<String>myAccHeaderList=new ArrayList<>();
		//iterate the myAccountMenuOptionList
		for(WebElement el:myAccountHeaderList) {
			String elTxt=el.getText();
			//add the element text to myAccMenuOptionList
			myAccHeaderList.add(elTxt);
		}
		return myAccHeaderList;
	}
	
	public ResultsPage doProductSearch(String productName) throws InterruptedException {
		log.info("searching for the prodct:"+ productName);
		try {
			if(isSearchExist()) {
				sendData(searchEditbox, productName);
				jsUtils.clickElementByJS(searchTorchIcon);
				return new ResultsPage(driver);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ElementClickInterceptedException eciex) {
			eciex.printStackTrace();
		}
		return null;
	}
	
}
