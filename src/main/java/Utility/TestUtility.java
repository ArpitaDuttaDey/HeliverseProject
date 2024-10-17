package Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class TestUtility {

	/**
	 * This method is used to launch chrome browser, maximize the screen and wait
	 * for the page to load.
	 */
	public static WebDriver screenLauncher() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("Launching & maximising browser", true);
		return driver;
	}

	/**
	 * This method is used to open the given web-site.
	 */
	public static void openWebsite(WebDriver driver, String webAddress) {
		driver.get(webAddress);
		Reporter.log("Website launched", true);
	}

	/**
	 * This method is used to login to the web-site.
	 */
	public static void login(WebDriver driver, String mailId, String password) {
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(mailId);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Reporter.log("Request submitted for Login", true);
	}

	/**
	 * This method is used to clear the shopping cart.
	 */
	public static void clearCart(WebDriver driver) {
		driver.findElement(By.linkText("Shopping cart")).click();
		driver.findElement(By.name("removefromcart")).click();
		driver.findElement(By.name("updatecart")).click();
		Reporter.log("Cart cleared", true);
	}

	/**
	 * This method is used to search inventory with given keyword.
	 */
	public static void search(WebDriver driver, String keyword) {
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys(keyword);
		driver.findElement(By.xpath("//input[@value='Search']")).click();
	}

	/**
	 * This method is used to search inventory with given keyword.
	 */
	public static void addProductToCart(WebDriver driver, String productId) {
		driver.findElement(By.id(productId)).click();
		Reporter.log("Product added to Cart", true);
	}

	/**
	 * This method is used to register user.
	 */
	public static void register(WebDriver driver, String firstName, String lastName, String emailId, String pwd,
			String confirmPwd) {
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(emailId);
		driver.findElement(By.id("Password")).sendKeys(pwd);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPwd);
		driver.findElement(By.xpath("//input[@value='Register']")).click();
		Reporter.log("Request submitted for registration", true);
	}
}
