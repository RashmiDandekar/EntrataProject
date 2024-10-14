package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BasePage;

public class HomePageObjects extends BasePage{

	public HomePageObjects() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@aria-label='home' and @class='brand w-nav-brand']")
	private WebElement brandLogo;
	
	@FindBy(xpath = "//h1[text()='Efficiency everywhere, from site-level to C-level']")
	private WebElement brandheadLine;
	
	@FindBy(xpath = "//a[@id='product-banner-button']//div[text()='Schedule Demo']")
	private WebElement scheduleDemoButton;
	
	public WebElement getBrandheadLine() {
		return brandheadLine;
	}

	public WebElement getScheduleDemoButton() {
		return scheduleDemoButton;
	}

	public WebElement getMenuOnPage(String menu) {
		return driver.findElement(
				By.xpath("//nav[contains(@class,'nav-menu')]//div[@class='dropdown-menu-text---brix' and text()='"
						+ menu + "']"));
	}
	
	public WebElement getDropDownMenuOnPage(String menu) {
		return driver.findElement(
				By.xpath("//div[@class='mega-nav desktop']//div[@class='footer-column']//a[text()='" + menu + "']"));
	}
	
	public WebElement getHeaderOfPage(String expectedHead) {
		return driver.findElement(By.xpath("//h1[contains(text(),'"+expectedHead+"')]"));
	}

	public WebElement getBrandLogo() {
		return brandLogo;
	}
}
