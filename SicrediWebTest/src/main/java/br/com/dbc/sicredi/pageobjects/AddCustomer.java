package br.com.dbc.sicredi.pageobjects;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import br.com.dbc.sicredi.utils.Constants;
import br.com.dbc.sicredi.utils.Utils;

public class AddCustomer extends StartDriver{
	
	@FindBy(how = How.ID, using = "field-customerName")
	protected WebElement nameTextField;
	@FindBy(how = How.ID, using = "field-contactLastName")
	protected WebElement lastNameTextField;
	@FindBy(how = How.ID, using = "field-contactFirstName")
	protected WebElement contactFirstNameTextField;
	@FindBy(how = How.ID, using = "field-phone")
	protected WebElement phoneTextField;
	@FindBy(how = How.ID, using = "field-addressLine1")
	protected WebElement addressLine1TextField;
	@FindBy(how = How.ID, using = "field-addressLine2")
	protected WebElement addressLine2TextField;
	@FindBy(how = How.ID, using = "field-city")
	protected WebElement cityTextField;
	@FindBy(how = How.ID, using = "field-state")
	protected WebElement stateTextField;
	@FindBy(how = How.ID, using = "field-postalCode")
	protected WebElement postalCodeTextField;
	@FindBy(how = How.ID, using = "field-country")
	protected WebElement countryTextField;
	@FindBy(how = How.CSS, using = "#field_salesRepEmployeeNumber_chosen > a")
	protected WebElement fromEmployerComboBox;
	@FindBy(how = How.CSS, using = "#field_salesRepEmployeeNumber_chosen > div > div > input[type=text]")
	protected WebElement fromEmployerTextField;
	@FindBy(how = How.ID, using = "field-creditLimit")
	protected WebElement creditLimitTextField;
	@FindBy(how = How.ID, using = "form-button-save")
	protected WebElement saveBtn;
	@FindBy(how = How.CSS, using = "#report-success > p")
	protected WebElement successMsgLabel;
	@FindBy(how = How.XPATH, using = "//*[@id=\"report-success\"]/p/a[2]")
	protected WebElement goBackBtn;	
	@FindBy(how = How.ID, using = "switch-version-select")
	protected WebElement selectVersionComboBox;

	public AddCustomer(WebDriver driver) {
		super(driver);
	}	

	public void fillingNewCustomerData(String name, String lastName, String contactFirstName, String phone, String addressLine1, String addressLine2, String city, String state, String postalCode, String country, String fromEmployer, String  creditLimit) {
		
		//Aguardar carregamento da pagina inicial
		Utils.waitOnObject(driver, nameTextField);
		
		//Preencher dados do cliente
		System.out.println("Name: " + name);
		this.nameTextField.sendKeys(name);
		System.out.println("Last Name: " + lastName);
		this.lastNameTextField.sendKeys(lastName);
		System.out.println("Contact First Name: " + contactFirstName);		
		this.contactFirstNameTextField.sendKeys(contactFirstName);
		System.out.println("Phone: " + phone);		
		this.phoneTextField.sendKeys(phone);
		System.out.println("Address Line 1: " + addressLine1);		
		this.addressLine1TextField.sendKeys(addressLine1);
		System.out.println("Address Line 2: " + addressLine2);		
		this.addressLine2TextField.sendKeys(addressLine2);
		System.out.println("City: " + city);		
		this.cityTextField.sendKeys(city);
		System.out.println("State: " + state);		
		this.stateTextField.sendKeys(state);
		System.out.println("Postal Code: " + postalCode);		
		this.postalCodeTextField.sendKeys(postalCode);
		System.out.println("Country: " + country);		
		this.countryTextField.sendKeys(country);
		System.out.println("From Employer: " + fromEmployer);		
		selectingEmployer(fromEmployer);
		System.out.println("Credit Limit: " + creditLimit);		
		this.creditLimitTextField.sendKeys(creditLimit);		
		Utils.takeScreenShot();
	}	

	public void savingNewCustomerData() {
		System.out.println("Saving");
		this.saveBtn.click();
		Utils.takeScreenShot();
	}
	
	public void goingBackToIntialScreen() {
		System.out.println("Going back to initial screen");
		this.goBackBtn.click();
		Utils.waitOnObject(driver, selectVersionComboBox);
		Utils.takeScreenShot();
	}

	public void validatingNewCustomerSuccessMessage() {
		Utils.waitOnObject(driver, successMsgLabel);
		String systemMessage = this.successMsgLabel.getText();
		if (systemMessage.contains("Your data has been successfully stored into the database. Edit Customer or Go back to list")) {	
			System.out.println("VALIDATION - Success message found: " + systemMessage);
			Utils.logingTestAsPassed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(true);
		}
		else {
			System.out.println("VALIDATION - Success message not found: " + systemMessage);
			Utils.logingTestAsFailed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(false);
		}
	}

	private void selectingEmployer(String employer) {
		this.fromEmployerComboBox.click();
		this.fromEmployerTextField.sendKeys(employer);
		this.fromEmployerTextField.sendKeys(Keys.ENTER);
	}
	

	
	
}
