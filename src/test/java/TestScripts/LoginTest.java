package TestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.TestUtility;

public class LoginTest {

	@Test
	public void loginScript() {

		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Login using invalid credentials
			TestUtility.login(driver, "arpitadutta98@gmail.com", "arpi@99");

			// Validate for login unsuccessful error message
			String actualRes = driver.findElement(By.xpath("//span[contains(text(),'unsuccessful')]")).getText();
			Assert.assertTrue(actualRes.contains("Login was unsuccessful"));

			// Login using valid credentials
			TestUtility.login(driver, "arpitadutta98@gmail.com", "arpi@98");

			// Validate if post login success, log out link is shown
			String actualResult = driver.findElement(By.partialLinkText("Log out")).getText();
			Assert.assertEquals(actualResult, "Log out");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
