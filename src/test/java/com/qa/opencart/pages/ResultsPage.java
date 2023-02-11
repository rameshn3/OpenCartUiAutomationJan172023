package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.JavaScriptUtils;
import com.qa.opencart.utils.WebDriverUtils;

public class ResultsPage extends WebDriverUtils{
	private WebDriver driver;
	private Logger log=LogManager.getLogger(ResultsPage.class.getName());
	JavaScriptUtils jsUtils;
	public ResultsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	@FindBy(css="div[class*='product-layout product-grid']")
	private List<WebElement> searchProducts;
	@FindBy(css="ul.breadcrumb>li:nth-child(2)>a")
	private WebElement searchBreadCrumb;
	
	public String getSearchResultsPageTitle(String productName) {
		return waitForTitleContains(productName);
	}
	
	public int getSearchProductsSize() {
		return searchProducts.size();
	}
	
	public ProductDetailsPage selectProduct(String productName) {
		log.info("product name is:"+productName);
		//xpath=//a[text()='"+linkname+"']
		driver.findElement(By.linkText(productName)).click();
		return new ProductDetailsPage(driver);
	}
	
}
