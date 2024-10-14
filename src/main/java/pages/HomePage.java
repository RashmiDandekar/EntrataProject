package pages;

import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObjects;
import utilities.BasePage;
import utilities.ReportManager;

public class HomePage extends BasePage {

	HomePageObjects homePageObjects;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	public HomePage() throws Exception {
		super();
		homePageObjects = new HomePageObjects();
	}

	/**
	 * Method to click on brand logo and check if it is redirecting to home page
	 * 
	 * @return page title
	 * @throws Exception
	 */
	public String clickOnBrandLogoAndAndReturnPageTitle() throws Exception {
		try {
			scrollIntoView(homePageObjects.getBrandLogo());
			homePageObjects.getBrandLogo().click();
			ReportManager.logInfo("Successfully clicked on brand logo");
			String actualTitle = driver.getTitle();
			ReportManager.logInfo("Title of the page is :" + actualTitle);
			return actualTitle;
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Issue in clickOnBrandLogoAndCheckIfRedirectsToHomePage");
			ReportManager.endTest();
		}
		return null;
	}

	/**
	 * Method to navigate to sub menu through menu bar on home Page
	 * 
	 * @return page title
	 * @throws Exception
	 */
	public String navigateToSubMenuThroughMenuBar(String menu, String subMenu, String heading) throws Exception {
		try {
			scrollIntoView(homePageObjects.getMenuOnPage(menu));
			Actions action = new Actions(driver);
			action.moveToElement(homePageObjects.getMenuOnPage(menu)).build().perform();
			ReportManager.logInfo("Successfully clicked on menu on the page: " + menu);
			action.moveToElement(homePageObjects.getDropDownMenuOnPage("ResidentPay")).build().perform();
			homePageObjects.getDropDownMenuOnPage(subMenu).click();
			ReportManager.logInfo("Successfully clicked on submenu on the page: " + subMenu);
			wait.until(ExpectedConditions.visibilityOf(homePageObjects.getHeaderOfPage(heading)));
			ReportManager.logPass("User is successfully redirected to :" + subMenu);
			return driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Issue in navigateToSubMenuThroughMenuBar :" + subMenu);
			ReportManager.endTest();
		}
		return null;
	}
}
