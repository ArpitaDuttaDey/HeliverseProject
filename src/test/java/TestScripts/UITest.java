package TestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.TestUtility;
import Utility.Validator;

public class UITest {

	@Test
	public void runSearchTest() {

		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Search the product
			TestUtility.search(driver, "Computer");
			Thread.sleep(2000);

			// Validate that search result is giving expected product
			String result = driver.findElement(By.xpath("//img[@alt='Picture of Build your own computer']")).getText();
			Assert.assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	@Test
	public void runNavigationTest() {

		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Validate the navigation menu
			By navMenuLocator = By.xpath("//div[@class='header-menu']");
			Validator.validateNavigationMenu(driver, navMenuLocator);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	@Test
	public void runFooterTest() {

		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Validate the footer
			By footerLocator = By.xpath("//div[@class='footer']");
			Validator.validateFooter(driver, footerLocator);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
