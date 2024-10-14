package pages;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.SchedulingDemoPageObjects;
import utilities.BasePage;
import utilities.ReportManager;

public class SchedulingDemoPage extends BasePage{

	SchedulingDemoPageObjects schedulingDemoPageObjects;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	
	public SchedulingDemoPage() throws Exception {
		super();
		schedulingDemoPageObjects = new SchedulingDemoPageObjects();
	}
	
	public boolean filloutField(String field, String value) throws Exception {
		try {
			ReportManager.logInfo("Filling value in textbox: "+field+" --> "+value);
			switch(field){
			case "First Name":
				scrollIntoView(schedulingDemoPageObjects.getInputFirstName());
				schedulingDemoPageObjects.getInputFirstName().sendKeys(value);
				break;
			case "Last Name":
				scrollIntoView(schedulingDemoPageObjects.getInputLastName());
				schedulingDemoPageObjects.getInputLastName().sendKeys(value);
				break;
			case "Email":
				scrollIntoView(schedulingDemoPageObjects.getInputEmail());
				schedulingDemoPageObjects.getInputEmail().sendKeys(value);
				break;
			case "Company Name":
				scrollIntoView(schedulingDemoPageObjects.getInputCompanyName());
				schedulingDemoPageObjects.getInputCompanyName().sendKeys(value);
				break;
			case "Phone Number":
				scrollIntoView(schedulingDemoPageObjects.getInputPhoneNumber());
				schedulingDemoPageObjects.getInputPhoneNumber().sendKeys(value);
				break;
			case "Job Title":
				scrollIntoView(schedulingDemoPageObjects.getInputTitle());
				schedulingDemoPageObjects.getInputTitle().sendKeys(value);
				break;
			}
			ReportManager.logPass("Filled value in textbox: "+field+" --> "+value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Issue in filloutField:"+field+"--"+value);
			ReportManager.endTest();
		}
		return false;
	}
	
	/**
	 * Method to fill the demo form
	 * @throws Exception
	 */
	public void fillDemoForm() throws Exception {
		try {
			Assertions.assertTrue(filloutField("First Name", "Rashmi"));
			Assertions.assertTrue(filloutField("Last Name", "Dandekar"));
			Assertions.assertTrue(filloutField("Email", "Rashmi@abc.com"));
			Assertions.assertTrue(filloutField("Company Name", "Rashmi Infotech"));
			Assertions.assertTrue(filloutField("Phone Number", "0000000000"));
			selectValueFromDropDown("Unit Count", "1 - 10");
			Assertions.assertTrue(filloutField("Job Title", "IT"));
			selectValueFromDropDown("I am", "a Resident");
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Issue in fillDemoForm");
			ReportManager.endTest();
		}
	}
	
	/**
	 * Method to select value from dropdown
	 * @param dropdown
	 * @param value
	 * @throws Exception
	 */
	public void selectValueFromDropDown(String dropdown, String value) throws Exception {
		try {
			ReportManager.logInfo("Selecting value from dropdown: "+dropdown+" --> "+value);
			Select s;
			switch (dropdown) {
			case "Unit Count":
				s = new Select(schedulingDemoPageObjects.getInputSelectUnitCount());
				s.selectByValue(value);
				break;
			case "I am":
				s = new Select(schedulingDemoPageObjects.getInputSelectIam());
				s.selectByValue(value);
				break;
			}
			ReportManager.logPass("Selected value from dropdown: "+dropdown+" --> "+value);
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Issue in selectValueFromDropDown" + dropdown + "--" + value);
			ReportManager.endTest();
		}
	}
}
