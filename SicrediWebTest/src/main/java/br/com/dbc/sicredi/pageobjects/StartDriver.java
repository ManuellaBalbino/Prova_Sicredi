package br.com.dbc.sicredi.pageobjects;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import br.com.dbc.sicredi.utils.Constants;                               

public class StartDriver {

	protected static WebDriver driver;
	static File browser = new File(Constants.PATH_CHROMEDRIVER);;

	public StartDriver(WebDriver driver) {

		initiateBrowser(driver);
		StartDriver.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public WebDriver initiateBrowser(WebDriver driver) {

		if (driver == null) {

			System.setProperty("webdriver.chrome.driver", browser.getAbsolutePath());
			driver = new ChromeDriver();
		}

		return driver;
	}
}
