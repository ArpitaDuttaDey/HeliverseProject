package TestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.TestUtility;

public class FunctionalTest {

	@Test
	public void runScript() throws InterruptedException {

		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Select for login
			TestUtility.login(driver, "arpitadutta98@gmail.com", "arpi@98");

			// Clearing the shopping cart
			TestUtility.clearCart(driver);

			// Search the product
			TestUtility.search(driver, "Book");

			// Select product to buy
			driver.findElement(By.xpath("//img[@alt='Picture of Health Book']")).click();

			// Adding the product to the cart 2 times
			TestUtility.addProductToCart(driver, "add-to-cart-button-22");
			Thread.sleep(2000);
			TestUtility.addProductToCart(driver, "add-to-cart-button-22");

			// Open shopping cart
			driver.findElement(By.linkText("Shopping cart")).click();

			// Validate if cart value price is correct
			String actualProductPrice = driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText();
			Assert.assertEquals(actualProductPrice, "20.00");

			// Tick-out terms of services and click on checkout
			driver.findElement(By.id("termsofservice")).click();
			driver.findElement(By.id("checkout")).click();

			// Validate that billing page is coming after checkout
			String actualPage = driver.findElement(By.xpath("(//div[@class='step-title'])[1]")).getText();
			System.out.println(actualPage);
			Assert.assertEquals(actualPage, "1\nBilling Address");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
