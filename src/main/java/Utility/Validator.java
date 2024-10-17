package Utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Validator {

	/**
	 * This method is used to validate the navigation menu.
	 */
	public static void validateNavigationMenu(WebDriver driver, By navMenuLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(navMenuLocator));

		List<WebElement> menuItems = element.findElements(By.tagName("a"));

		for (WebElement item : menuItems) {
			String itemText = item.getText();
			String itemUrl = item.getAttribute("href");

			System.out.println("Validating menu item: " + itemText + " with URL: " + itemUrl);

			// Check if the item is displayed and clickable
			if (!item.isDisplayed()) {
				System.out.println(itemText + " is not displayed");
				continue;
			}
			if (!item.isEnabled()) {
				System.out.println(itemText + " is not clickable");
				continue;
			}

			// Click the menu item and check the URL
			item.click();

			// Wait for the URL to change
			wait.until(ExpectedConditions.urlToBe(itemUrl));

			String currentUrl = driver.getCurrentUrl();
			if (!currentUrl.equals(itemUrl)) {
				System.out.println("Failed: " + itemText + " did not navigate to the correct URL. Expected: " + itemUrl
						+ ", got: " + currentUrl);
			} else {
				System.out.println("Passed: " + itemText + " navigated correctly.");
			}

			// Go back to the previous page
			driver.navigate().back();
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(navMenuLocator));
			menuItems = element.findElements(By.tagName("a"));
		}
		System.out.println("Navigation Bar validation passed.");
	}
	
	/**
	 * This method is used to validate the footer menu.
	 */
	public static void validateFooter(WebDriver driver, By footerLocator){
		WebElement footer = driver.findElement(footerLocator);

        // Validate specific footer elements
        List<WebElement> links = footer.findElements(By.tagName("a"));
        links.forEach(link -> System.out.println(link.getText()));

        // Validate a specific link
        String expectedLinkText = "Privacy Notice";
        boolean linkFound = links.stream().anyMatch(link -> link.getText().equals(expectedLinkText));
        Assert.assertTrue(linkFound);

        // Validate copyright text
        WebElement copyrightText = footer.findElement(By.xpath(".//div[contains(text(), '©')]")); // Adjust as needed
        assert copyrightText.isDisplayed() : "Copyright text is not displayed.";

        System.out.println("Footer validation passed.");
	}
}
