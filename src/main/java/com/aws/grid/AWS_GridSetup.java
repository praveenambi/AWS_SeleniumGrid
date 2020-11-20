/**
 * 
 */
package com.aws.grid;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			try {
				driver = new RemoteWebDriver(new URL("http://172.31.11.10:4444/grid/consloe"), capabs);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabs = new DesiredCapabilities();
			capabs.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			try {
				driver = new RemoteWebDriver(new URL("http://172.31.11.10:4444/grid/consloe"), capabs);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://freecrm.co.in/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void getTitle() {

		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "youtube");

	}

	@Test(priority = 2)
	public void verifySignUP() {

		boolean flag = false;
		flag = driver.findElement(By.xpath("//a[text()='Sign Up']")).isDisplayed();
		Assert.assertTrue(flag);

	}
	
	
	@Test(priority = 3)
	public void countLinks() {
		
	List<WebElement> anchorLinks =	driver.findElements(By.tagName("a"));
	System.out.println("The anchor links are " + anchorLinks.size() );
	assertEquals("61", anchorLinks.size());
	
		
	}

	@AfterMethod
	public void teardown() {

		driver.quit();



	}




}
