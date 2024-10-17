package TestScripts;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.TestUtility;

public class ValidationTest {

	@Test
	public void validationScript() {
		// Open the browser
		WebDriver driver = TestUtility.screenLauncher();

		try {
			// Open the web-site
			TestUtility.openWebsite(driver, "https://demowebshop.tricentis.com/");

			// Register using invalid credentials
			TestUtility.register(driver, "Arpita", "", "arpitadutta", "12345", "1234");

			// Validate for mandatory field missing
			String actualMandatoryError = driver.findElement(By.xpath("(//span[@class='field-validation-error'])[1]"))
					.getText();
			Assert.assertEquals("Last name is required.", actualMandatoryError);
			System.out.println("Mandatory field missing error validation successful");

			// Validate for wrong email error message
			String actualRes = driver.findElement(By.xpath("(//span[@class='field-validation-error'])[2]")).getText();
			Assert.assertEquals(actualRes, "Wrong email");
			System.out.println("Email wrong format error validation successful");

			// Validate for wrong password length error message
			String actualResult = driver.findElement(By.xpath("(//span[@class='field-validation-error'])[3]"))
					.getText();
			Assert.assertEquals(actualResult, "The password should have at least 6 characters.");
			System.out.println("Password length constraint error validation successful");

			// Validate for password & confirm password mismatch error message
			String actualAns = driver.findElement(By.xpath("(//span[@class='field-validation-error'])[4]")).getText();
			Assert.assertEquals(actualAns, "The password and confirmation password do not match.");
			System.out.println("Password mismatch error validation successful");

			// Validate if post registration with correct credentials, success message is
			// shown
			String mailId = "arpitadutta" + new Random().nextInt(99999999) + "@gmail.com";
			TestUtility.register(driver, "Arpita", "Dutta", mailId, "123456", "123456");
			String actualAnswer = driver.findElement(By.xpath("//div[@class='result']")).getText();
			Assert.assertEquals(actualAnswer, "Your registration completed");
			System.out.println("Registration completed validation successful");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
