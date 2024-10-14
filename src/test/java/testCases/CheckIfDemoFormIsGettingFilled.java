package testCases;

import java.time.Duration;

import javax.swing.Scrollable;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObjects;
import pages.HomePage;
import pages.SchedulingDemoPage;
import utilities.BasePage;
import utilities.ReportManager;

/**
 * CASE to check if demo form is getting filled.
 * 
 * @author Rashmi
 */
public class CheckIfDemoFormIsGettingFilled extends BasePage{
	SchedulingDemoPage schedulingDemoPage;
	HomePage homePage;
	HomePageObjects homePageObjects;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions action = new Actions(driver);

	public CheckIfDemoFormIsGettingFilled() throws Exception {
		super();
		schedulingDemoPage = new SchedulingDemoPage();
		homePage = new HomePage();
		homePageObjects = new HomePageObjects();
	}

	public void checkIfDemoFormIsGettingFilled() throws Exception {
		try {
			homePage.navigateToSubMenuThroughMenuBar("Products", "ResidentPortal", "ResidentPortal");
			wait.until(ExpectedConditions.visibilityOf(homePageObjects.getScheduleDemoButton()));
			action.click(homePageObjects.getScheduleDemoButton()).build().perform();
			ReportManager.logInfo("Successfully clicked on schedule demo button");
			schedulingDemoPage.fillDemoForm();
			ReportManager.logPass("TESTCASE PASSED:: checkIfDemoFormIsGettingFilled");
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("FAILURE in testcase :: CheckIfDemoFormIsGettingFilled");
			ReportManager.endTest();
		}
	}
}
