package br.com.dbc.sicredi.testcases;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.dbc.sicredi.pageobjects.AddCustomer;
import br.com.dbc.sicredi.pageobjects.Customers;
import br.com.dbc.sicredi.utils.Constants;
import br.com.dbc.sicredi.utils.Utils;


public class TC_001_Add_Customer {
	
	static WebDriver driver;
	static Customers customers;
	static AddCustomer addCustomer;
	static File browser = new File(Constants.PATH_CHROMEDRIVER);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", browser.getAbsolutePath());
		driver = new ChromeDriver();
		customers = new Customers(driver);
		addCustomer = new AddCustomer(driver);
		
		//Definir o título do caso de teste, a ser mostrado no console
		Constants.TESTECASE_TITLE = "TC_001_Add_Customer";
	}

	@After
	public void tearDown() throws Exception {
		//Fechar browser
		Utils.sleep(3000);
		driver.quit();
	}

	@Test
	public void test() {
		
		//Abrir Url e maximizar o navegador
		customers.openWebPageAndMax();
	
		//Selecionar versao
		customers.selectingVersion("bootstrap_theme_v4");
		
		//Adicionar cliente
		customers.addingCustomer();
		
		//Adicionar dados do cliente
		addCustomer.fillingNewCustomerData("Teste Sicredi", "Teste", "seu nome", "51 9999-9999", "Av Assis Brasil, 3970", "Torre D", "Porto Alegre", "RS", "91000-000", "Brasil", "Fixter", "200");
		
		//Salvar
		addCustomer.savingNewCustomerData();
		
		//Validar mensagem de sucesso
		addCustomer.validatingNewCustomerSuccessMessage();
		

		
	}

}
