package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BasePage;

public class SchedulingDemoPageObjects extends BasePage{
	public SchedulingDemoPageObjects() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="FirstName")
	private WebElement inputFirstName;
	
	@FindBy(id="LastName")
	private WebElement inputLastName;
	
	@FindBy(id="Email")
	private WebElement inputEmail;
	
	@FindBy(id="Company")
	private WebElement inputCompanyName;
	
	@FindBy(id="Phone")
	private WebElement inputPhoneNumber;
	
	@FindBy(id="Unit_Count__c")
	private WebElement inputSelectUnitCount;
	
	@FindBy(id="Title")
	private WebElement inputTitle;
	
	@FindBy(id="demoRequest")
	private WebElement inputSelectIam;

	public WebElement getInputFirstName() {
		return inputFirstName;
	}

	public WebElement getInputLastName() {
		return inputLastName;
	}

	public WebElement getInputEmail() {
		return inputEmail;
	}

	public WebElement getInputCompanyName() {
		return inputCompanyName;
	}

	public WebElement getInputPhoneNumber() {
		return inputPhoneNumber;
	}

	public WebElement getInputSelectUnitCount() {
		return inputSelectUnitCount;
	}

	public WebElement getInputTitle() {
		return inputTitle;
	}

	public WebElement getInputSelectIam() {
		return inputSelectIam;
	}
	
}
