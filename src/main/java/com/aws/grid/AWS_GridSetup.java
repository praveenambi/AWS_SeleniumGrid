/**
 * 
 */
package com.aws.grid;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bsh.Capabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author PRAAMBI
 * 
 * setinig up the gris for pareller execution in AWS instance using docker
 *
 */
public class AWS_GridSetup {
	
	 WebDriver driver;
	
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities capabs = new DesiredCapabilities();
			capabs.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			driver = new RemoteWebDriver(new URL("http://172.31.11.10:4444/grid/consloe"));
			
		}
		
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabs = new DesiredCapabilities();
			capabs.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			driver = new RemoteWebDriver(new URL("http://172.31.11.10:4444/grid/consloe"));
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
		driver.get("https://www.youtube.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	private void getTitle() {
		
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "youtube");

	}
	
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
		
		
	}
	
	
	

}
