package testCases;

import org.junit.jupiter.api.Assertions;

import pages.HomePage;
import pages.SchedulingDemoPage;
import utilities.ReportManager;

/**
 * CASE to check if user redirects to subMenu through menu bar on home page
 * 
 * @author Rashmi
 */
public class CheckingIfSubMenuRedirectsToRespectivePage {
	SchedulingDemoPage schedulingDemoPage;
	HomePage homePage;

	public CheckingIfSubMenuRedirectsToRespectivePage() throws Exception {
		super();
		schedulingDemoPage = new SchedulingDemoPage();
		homePage = new HomePage();
	}

	public void checkingIfSubMenuRedirectsToRespectivePage() throws Exception {
		try {
			String title = homePage.navigateToSubMenuThroughMenuBar("Products", "ResidentPay", "ResidentPay");
			Assertions.assertEquals("ResidentPay | Entrata", title);
			ReportManager.logPass("TESTCASE PASSED: checkingIfSubMenuRedirectsToRespectivePage");
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("FAILURE in testcase :: CheckingIfSubMenuRedirectsToRespectivePage");
			ReportManager.endTest();
		}
	}
}
