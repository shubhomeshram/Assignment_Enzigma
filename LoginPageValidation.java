package Enzigma;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageValidation {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.nokodr.com/super/apps/auth/v1/index.html#/login");
		// WebElement usernameError =
		// driver.findElement(By.xpath("//input[@name='username']//following-sibling::div[@class='error-message']"));
		// WebElement passwordError =
		// driver.findElement(By.xpath("//input[@name='password']//following-sibling::div[@class='error-message']"));

		// assert usernameError.getText().equals("Username is required.") : "Username
		// error message is incorrect.";
		// assert passwordError.getText().equals("Password is required.") : "Password
		// error message is incorrect.";

		WebElement Email = driver.findElement(By.xpath("//input[@type='email']"));
		Email.sendKeys("sameermeshram1192@gmail.com");
		WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
		Password.sendKeys("Shubham@1309");
		driver.findElement(By.id("rememberMe")).click();
		WebElement Login = driver.findElement(By.xpath("//div[@title='Log In']"));
		Login.click();
		WebDriverWait wait = null;
		validateIncorrectCredentials(driver, wait);
		validateBlankUsername(driver, wait);
		validateBlankPassword(driver, wait);
		validateBlankBothFields(driver, wait);
		validateSpecialCharactersInUsername(driver, wait);
		validateSpecialCharactersInPassword(driver, wait);
		validateSpecialCharactersInBothFields(driver, wait);
		validateValidCredentials(driver, wait);
		submitAndVerifyInvalidCredentials(driver, wait);
		String password = "Shubham@1309";
		isValidPassword(password);

		if (isValidPassword(password)) {
			Email.sendKeys("sameermeshram1192@gmail.com");
			Password.sendKeys("Shubham@1309");
			Login.click();
		} else {
			System.out.println("Password does not meet the required format.");
		}
	}

	public static boolean isValidPassword(String password) {

		String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	private static void validateIncorrectCredentials(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("sameermeshram1192@gmail.com");
		passwordField.sendKeys("Shubham@1309");
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage.getText().equals("Invalid username or password.")
				: "Error message for incorrect username is incorrect.";
	}

	private static void validateBlankUsername(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.sendKeys("Shubham@130");
		loginButton.click();

		WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@name='username']//following-sibling::div[@class='error-message']")));
		assert usernameError.getText().equals("Username is required.")
				: "Username error message for blank field is incorrect.";

		System.out.println("Blank Username  invalid validation failed.");
	}

	private static void validateBlankPassword(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("sameermeshram@gmail.com");
		loginButton.click();

		WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@name='password']//following-sibling::div[@class='error-message']")));
		assert passwordError.getText().equals("Password is required.")
				: "Password error message for blank field is incorrect.";

		System.out.println("Blank Password invalid validation failed.");
	}

	private static void validateBlankBothFields(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		loginButton.click();

		WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@name='username']//following-sibling::div[@class='error-message']")));
		WebElement passwordError = driver
				.findElement(By.xpath("//input[@name='password']//following-sibling::div[@class='error-message']"));

		assert usernameError.getText().equals("Username is required.")
				: "Username error message for blank field is incorrect.";
		assert passwordError.getText().equals("Password is required.")
				: "Password error message for blank field is incorrect.";

		System.out.println("Blank Both Fields invalid validation failed.");
	}

	private static void validateSpecialCharactersInUsername(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("!@#$%^&*");
		passwordField.sendKeys("Shubham@130");
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage.getText().equals("Invalid username or password.")
				: "Error message for special characters in username is incorrect.";

		System.out.println("Special Characters in Username validation passed.");
	}

	private static void validateSpecialCharactersInPassword(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("sameermeshram@gmail.com");
		passwordField.sendKeys("<script>alert('test')</script>");
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage.getText().equals("Invalid username or password.")
				: "Error message for special characters in password is incorrect.";

		System.out.println("Special Characters in Password validation passed.");
	}

	private static void validateSpecialCharactersInBothFields(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("!@#$%^&*");
		passwordField.sendKeys("<script>alert('test')</script>");
		loginButton.click();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage.getText().equals("Invalid username or password.")
				: "Error message for special characters in both fields is incorrect.";

		System.out.println("Special Characters in Both Fields validation passed.");
	}

	private static void validateValidCredentials(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		// Enter valid credentials in username and password fields
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("sameermeshram1192@gmail.com"); // Example valid username
		passwordField.sendKeys("Shubham@1309"); // Example valid password
		loginButton.click();

		// Wait for redirection to dashboard
		wait.until(ExpectedConditions.urlToBe("https://www.nokodrplatform.com/dashboard"));

		// Validate the redirection to the dashboard
		assert driver.getCurrentUrl().equals("https://www.nokodrplatform.com/dashboard")
				: "Redirection to dashboard failed.";

		System.out.println("Login with valid credentials and redirection to dashboard passed.");
	}

	private static void submitAndVerifyInvalidCredentials(WebDriver driver, WebDriverWait wait) {
		WebElement usernameField = driver.findElement(By.name("username"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		// Enter incorrect credentials (invalid username or password)
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("sameermeshram@gmail.com");
		passwordField.sendKeys("Shubham@130");
		loginButton.click();

		// Wait for the error message and verify it
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage.getText().equals("Invalid username or password.")
				: "Error message for invalid username or password is incorrect.";

		System.out.println("Invalid credentials: Error message displayed correctly.");
	}

}
