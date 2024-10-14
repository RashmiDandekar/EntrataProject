package runners;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testCases.CheckIfDemoFormIsGettingFilled;
import testCases.CheckingIfClickingOnLogoRedirectsToHomePage;
import testCases.CheckingIfSubMenuRedirectsToRespectivePage;
import utilities.BasePage;
import utilities.ReportManager;

public class Runner {

	static String url = "https://www.entrata.com/b";

	@BeforeEach // Test case execution begins with the startTest
	public void startTest() throws Exception {
		BasePage.instantiateWebBrowser(url);
	}

	@Test
	@DisplayName("CASE to check if user redirects to subMenu through menu bar on home page")
	public void checkingIfSubMenuRedirectsToRespectivePage() throws Exception {
		ReportManager.startTest("checkingIfSubMenuRedirectsToRespectivePage",
				"CASE to check if user redirects to subMenu through menu bar on home page");
		CheckingIfSubMenuRedirectsToRespectivePage run = new CheckingIfSubMenuRedirectsToRespectivePage();
		run.checkingIfSubMenuRedirectsToRespectivePage();
		ReportManager.endTest();
	}

	@Test
	@DisplayName("CASE to check if clicking on brand logo redirects to homePage.")
	public void checkingIfClickingOnLogoRedirectsToHomePage() throws Exception {
		ReportManager.startTest("checkingIfClickingOnLogoRedirectsToHomePage",
				"CASE to check if clicking on brand logo redirects to homePage.");
		CheckingIfClickingOnLogoRedirectsToHomePage run = new CheckingIfClickingOnLogoRedirectsToHomePage();
		run.checkingIfClickingOnLogoRedirectsToHomePage();
		ReportManager.endTest();
	}

	@Test
	@DisplayName("CASE to check if demo form is getting filled.")
	public void checkIfDemoFormIsGettingFilled() throws Exception {
		ReportManager.startTest("checkIfDemoFormIsGettingFilled", "CASE to check if demo form is getting filled.");
		CheckIfDemoFormIsGettingFilled run = new CheckIfDemoFormIsGettingFilled();
		run.checkIfDemoFormIsGettingFilled();
		ReportManager.endTest();
	}

	@AfterEach
	public void endTest() throws Exception {
		BasePage.tearDown();
	}
}
