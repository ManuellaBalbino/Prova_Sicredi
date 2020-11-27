package br.com.dbc.sicredi.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	
	public static void sleep(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void logingTestAsPassed(String scenarioName) {
		System.out.println("Scenario " + scenarioName + " - " + "PASSED");
	}
	
	public static void logingTestAsFailed(String scenarioName) {
		System.out.println("Scenario " + scenarioName + " - " + "FAILED");
	}
	
	public static void takeScreenShot(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
	        Utils.sleep(500);
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        ImageIO.write(image, "jpg", new File("C:\\Evidencia\\" + Constants.TESTECASE_TITLE + "_" + dateFormat.format(date) + ".jpg"));
	    }
	    catch( Exception e ) {
	        e.printStackTrace();
	    }
	}
	
	public static void waitOnObject(WebDriver driver, WebElement element) {
		WebDriverWait waitDriver = new WebDriverWait(driver, 20);
		element = waitDriver.until(ExpectedConditions.visibilityOf(element));
	}

}
