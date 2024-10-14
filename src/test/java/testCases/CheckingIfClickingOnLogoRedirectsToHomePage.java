package testCases;

import org.junit.jupiter.api.Assertions;

import pages.HomePage;
import pages.SchedulingDemoPage;
import utilities.ReportManager;

/**
 * CASE to check if clicking on brand logo redirects to homePage.
 * 
 * @author Rashmi
 */
public class CheckingIfClickingOnLogoRedirectsToHomePage {

	HomePage homePage;

	public CheckingIfClickingOnLogoRedirectsToHomePage() throws Exception {
		super();
		homePage = new HomePage();
	}

	public void checkingIfClickingOnLogoRedirectsToHomePage() throws Exception {
		try {
			homePage.navigateToSubMenuThroughMenuBar("Products", "ResidentPortal", "ResidentPortal");
			String title = homePage.clickOnBrandLogoAndAndReturnPageTitle();
			Assertions.assertEquals("Property Management Software | Entrata", title);
			ReportManager.logPass("TESTCASE PASSED: checkingIfClickingOnLogoRedirectsToHomePage");
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("FAILURE in testcase :: CheckIfDemoFormIsGettingFilled");
			ReportManager.endTest();
		}
	}

}
