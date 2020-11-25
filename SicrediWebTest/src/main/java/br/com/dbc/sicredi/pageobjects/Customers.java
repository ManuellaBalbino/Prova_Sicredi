package br.com.dbc.sicredi.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import br.com.dbc.sicredi.utils.Constants;
import br.com.dbc.sicredi.utils.Utils;

public class Customers extends StartDriver {

	private Select comboTheme;
	@FindBy(how = How.ID, using = "switch-version-select")
	protected WebElement selectVersionComboBox;
	@FindBy(how = How.CSS, using = "#gcrud-search-form > div.header-tools > div.floatL.t5 > a")
	protected WebElement addCustomerBtn;
	@FindBy(how = How.CSS, using = "#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td:nth-child(3) > input")
	protected WebElement searchNameTextField;
	@FindBy(how = How.CSS, using = "#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td.no-border-right > div > input")
	protected WebElement selectAllRowsCheckBox;
	@FindBy(how = How.CSS, using = "#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td.no-border-left")
	protected WebElement deleteBtn;	
	@FindBy(how = How.CSS, using = "#gcrud-search-form > div.scroll-if-required > table > thead > tr.filter-row.gc-search-row > td.no-border-left > div.floatR.l5 > a")
	protected WebElement updateSearchBtn;	
	@FindBy(how = How.CLASS_NAME, using = "alert-delete-multiple-one")
	protected WebElement popUpDeleteConfirmationLbl;
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'delete-multiple-confirmation-button')]")
	protected WebElement deletePopUpBtn;
	@FindBy(how = How.XPATH, using = "//span[@data-growl=\"message\"]")
	protected WebElement deleteSucessMessageSpan;	

	public Customers(WebDriver driver) {
		super(driver);
	}

	public void openWebPageAndMax() {
		//Abrir url
		System.out.println("Opening url: https://www.grocerycrud.com/demo/bootstrap_theme");
		driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");
		Utils.takeScreenShot();

		//Maximizar
		System.out.println("Maximizing window");
		driver.manage().window().maximize();
		Utils.takeScreenShot();

		//Aguardar carregamento da pagina inicial
		Utils.waitOnObject(driver, selectVersionComboBox);
	}

	public void selectingVersion(String version) {
		comboTheme = new Select(selectVersionComboBox);
		comboTheme.selectByValue(version);
		System.out.println("Selecting version: " + version);
		Utils.takeScreenShot();
	}

	public void addingCustomer() {
		this.addCustomerBtn.click();
		System.out.println("Adding customer");
		Utils.takeScreenShot();
	}

	private void searchName(String name) {
		this.searchNameTextField.sendKeys(name);
	}

	private void selectingAllRows() {
		this.updateSearchBtn.click();
		Utils.waitOnObject(driver, selectAllRowsCheckBox);
		this.selectAllRowsCheckBox.click();
	}

	private void deletingSelectedLine() {
		this.deleteBtn.click();

	}

	private void validatingPopUpDeleteMessage() {
		Utils.waitOnObject(driver, popUpDeleteConfirmationLbl);
		String systemMessage = this.popUpDeleteConfirmationLbl.getAttribute("innerHTML");
		if (systemMessage.contains("Are you sure that you want to delete this 1 item?")) {
			System.out.println("VALIDATION - Delete message found: " + systemMessage);
			Utils.logingTestAsPassed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(true);
		} else {
			System.out.println("VALIDATION - Delete message not found: " + systemMessage);
			Utils.logingTestAsFailed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(false);
		}
	}

	private void pressingDeletePopUp() {
		Utils.waitOnObject(driver, deletePopUpBtn);
		this.deletePopUpBtn.click();
	}


	private void validatingLabelDeleteMessage() {
		Utils.waitOnObject(driver, popUpDeleteConfirmationLbl);
		String systemMessage = this.popUpDeleteConfirmationLbl.getText();
		if (systemMessage.contains("Your data has been successfully deleted from the database.")) {
			System.out.println("VALIDATION - Deleted successfully message found: " + systemMessage);
			Utils.logingTestAsPassed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(true);
		} else {
			System.out.println("VALIDATION - Deleted successfully message not found: " + systemMessage);
			Utils.logingTestAsFailed(Constants.TESTECASE_TITLE);
			Utils.takeScreenShot();
			Assert.assertTrue(false);
		}
	}

	public void searchAndDeleteCustomerByName(String name) {
		System.out.println("Searching Name: " + name);
		searchName(name);
		Utils.takeScreenShot();		
		System.out.println("Selecting all rows");
		Utils.sleep(3000);
		selectingAllRows();
		Utils.takeScreenShot();		
		System.out.println("Pressing delete");
		deletingSelectedLine();
		Utils.takeScreenShot();		
		Utils.sleep(4000);
		System.out.println("Validating delete pop-up");
		validatingPopUpDeleteMessage();
		Utils.takeScreenShot();	
		System.out.println("Pressing delete on the pop-up");
		pressingDeletePopUp();
		Utils.takeScreenShot();
		System.out.println("Validating if the deleted successfully message exists");
		validatingLabelDeleteMessage();
		Utils.takeScreenShot();
	}

}
