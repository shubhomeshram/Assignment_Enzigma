package Enzigma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPageValidation {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
		driver.findElement(By.xpath("//a[text()='Sign up']")).click();

		validateMandatoryFields(driver, wait);

		validateValidInputs(driver, wait);

		validateInvalidInput(driver, wait);
		validateInvalidInputs(driver, wait);
		validateAllInputFields(driver, wait);
		submitAndVerify(driver, wait);
	}

	public static void validateMandatoryFields(WebDriver driver, WebDriverWait wait) {

		WebElement submitButton = driver.findElement(By.id("submitButton"));
		submitButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPasswordError")));

		System.out.println("Mandatory field validation passed.");
	}

	public static void validateValidInputs(WebDriver driver, WebDriverWait wait) {

		driver.findElement(By.id("name")).sendKeys("Shubham");
		driver.findElement(By.id("email")).sendKeys("sameermeshram1192@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Shubham@1309");
		driver.findElement(By.id("confirmPassword")).sendKeys("Shubham@1309");

		driver.findElement(By.id("submitButton")).click();

		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
		assert successMessage.getText().equals("Account created successfully!");

		System.out.println("Valid input validation passed.");
	}

	public static void validateInvalidInput(WebDriver driver, WebDriverWait wait) {

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("sameermeshram1192@gmail.com");
		driver.findElement(By.id("submitButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));

		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Shubham@130");
		driver.findElement(By.id("confirmPassword")).clear();
		driver.findElement(By.id("confirmPassword")).sendKeys("Shubham@130");
		driver.findElement(By.id("submitButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPasswordError")));

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("confirmPassword")).clear();
		driver.findElement(By.id("submitButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));

		System.out.println("Invalid input validation passed.");
	}

	private static void validateAllInputFields(WebDriver driver, WebDriverWait wait) {
		WebElement nameField = driver.findElement(By.name("name"));
		WebElement emailField = driver.findElement(By.name("email"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
		WebElement submitButton = driver.findElement(By.id("submitButton"));

		// Check for Mandatory Fields
		// Validate Name
		nameField.clear();
		submitButton.click();
		WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));
		assert nameError.getText().equals("Name is required.") : "Name error message is incorrect.";

		// Validate Email
		emailField.clear();
		submitButton.click();
		WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert emailError.getText().equals("Email is required.") : "Email error message is incorrect.";

		// Validate Password
		passwordField.clear();
		submitButton.click();
		WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
		assert passwordError.getText().equals("Password is required.") : "Password error message is incorrect.";

		// Validate Confirm Password
		confirmPasswordField.clear();
		submitButton.click();
		WebElement confirmPasswordError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPasswordError")));
		assert confirmPasswordError.getText().equals("Confirm password is required.")
				: "Confirm password error message is incorrect.";

		// Validate Email Format
		emailField.clear();
		emailField.sendKeys("invalidEmailFormat");
		submitButton.click();
		WebElement emailFormatError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert emailFormatError.getText().equals("Invalid email format.") : "Email format error message is incorrect.";

		// Validate Password Format (e.g., minimum length, uppercase letter, number,
		// special character)
		passwordField.clear();
		passwordField.sendKeys("Swetha@123");
		submitButton.click();
		WebElement passwordFormatError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordFormatError")));
		assert passwordFormatError.getText().equals(
				"Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character.")
				: "Password format error message is incorrect.";

		// Ensure Confirm Password Matches Password
		passwordField.clear();
		confirmPasswordField.clear();
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();
		WebElement passwordMismatchError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordMismatchError")));
		assert passwordMismatchError.getText().equals("Passwords do not match.")
				: "Password mismatch error message is incorrect.";

		// Successful Form Submission (all fields are valid)
		nameField.clear();
		emailField.clear();
		passwordField.clear();
		confirmPasswordField.clear();
		nameField.sendKeys("Shubham");
		emailField.sendKeys("sameermeshram1192@gmail.com");
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();

		// Wait for successful submission and verify (e.g., redirection or success
		// message)
		wait.until(ExpectedConditions.urlToBe("https://example.com/success"));
		assert driver.getCurrentUrl().equals("https://example.com/success")
				: "Form submission failed or redirection incorrect.";

		System.out.println("Form submission successful with valid inputs.");
	}

	private static void validateInvalidInputs(WebDriver driver, WebDriverWait wait) {
		WebElement nameField = driver.findElement(By.name("name"));
		WebElement emailField = driver.findElement(By.name("email"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
		WebElement submitButton = driver.findElement(By.id("submitButton"));

		// Invalid Email Format
		emailField.clear();
		emailField.sendKeys("swetha@123gmail.com");
		submitButton.click();
		WebElement emailFormatError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert emailFormatError.getText().equals("Invalid email format.") : "Email format error message is incorrect.";

		// Passwords Not Matching
		passwordField.clear();
		confirmPasswordField.clear();
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();
		WebElement passwordMismatchError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordMismatchError")));
		assert passwordMismatchError.getText().equals("Passwords do not match.")
				: "Password mismatch error message is incorrect.";

		// Missing or Blank Mandatory Fields
		// Validate Name
		nameField.clear();
		submitButton.click();
		WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));
		assert nameError.getText().equals("Name is required.") : "Name error message is incorrect.";

		// Validate Email
		emailField.clear();
		submitButton.click();
		WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert emailError.getText().equals("Email is required.") : "Email error message is incorrect.";

		// Validate Password
		passwordField.clear();
		submitButton.click();
		WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
		assert passwordError.getText().equals("Password is required.") : "Password error message is incorrect.";

		// Validate Confirm Password
		confirmPasswordField.clear();
		submitButton.click();
		WebElement confirmPasswordError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPasswordError")));
		assert confirmPasswordError.getText().equals("Confirm password is required.")
				: "Confirm password error message is incorrect.";

		// Edge Case - Special Characters in Name
		nameField.clear();
		nameField.sendKeys("Shubham$1309");
		submitButton.click();
		WebElement nameSpecialCharError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameSpecialCharError")));
		assert nameSpecialCharError.getText().equals("Name contains invalid characters.")
				: "Name special character error message is incorrect.";

		// Edge Case - Extremely Long Inputs
		String longText = "a".repeat(1000);
		nameField.clear();
		nameField.sendKeys(longText);
		emailField.clear();
		emailField.sendKeys(longText + "@example.com");
		passwordField.clear();
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.clear();
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();
		wait.until(ExpectedConditions.urlToBe("https://example.com/success"));
		assert driver.getCurrentUrl().equals("https://example.com/success") : "Form submission failed for long inputs.";

		System.out.println("Invalid inputs validated successfully.");
	}

	private static void submitAndVerify(WebDriver driver, WebDriverWait wait) {
		WebElement nameField = driver.findElement(By.name("name"));
		WebElement emailField = driver.findElement(By.name("email"));
		WebElement passwordField = driver.findElement(By.name("password"));
		WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
		WebElement submitButton = driver.findElement(By.id("submitButton"));

		// Valid Form Submission

		nameField.clear();
		emailField.clear();
		passwordField.clear();
		confirmPasswordField.clear();
		nameField.sendKeys("Shubham@1309");
		emailField.sendKeys("sameermeshram1192@gmail.com");
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.sendKeys("Shubham@1309");

		submitButton.click();

		// Wait for success message or redirection
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
		assert successMessage.getText().equals("Account created successfully!") : "Success message is incorrect.";

		System.out.println("Form submission successful with valid inputs.");

		// Invalid Email Format
		emailField.clear();
		emailField.sendKeys("sameermeshram1192@gmail.com");
		submitButton.click();
		WebElement emailFormatError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert emailFormatError.getText().equals("Invalid email format.") : "Email format error message is incorrect.";

		// Passwords Not Matching
		passwordField.clear();
		confirmPasswordField.clear();
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();
		WebElement passwordMismatchError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordMismatchError")));
		assert passwordMismatchError.getText().equals("Passwords do not match.")
				: "Password mismatch error message is incorrect.";

		// Missing Mandatory Fields
		// Test missing name
		nameField.clear();
		submitButton.click();
		WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameError")));
		assert nameError.getText().equals("Name is required.") : "Name error message is incorrect.";

		// Test missing email
		emailField.clear();
		submitButton.click();
		WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert emailError.getText().equals("Email is required.") : "Email error message is incorrect.";

		// Test missing password
		passwordField.clear();
		submitButton.click();
		WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
		assert passwordError.getText().equals("Password is required.") : "Password error message is incorrect.";

		// Test missing confirm password
		confirmPasswordField.clear();
		submitButton.click();
		WebElement confirmPasswordError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPasswordError")));
		assert confirmPasswordError.getText().equals("Confirm password is required.")
				: "Confirm password error message is incorrect.";

		// Edge Case - Special Characters in Name
		nameField.clear();
		nameField.sendKeys("Shubham$");
		submitButton.click();
		WebElement nameSpecialCharError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameSpecialCharError")));
		assert nameSpecialCharError.getText().equals("Name contains invalid characters.")
				: "Name special character error message is incorrect.";

		// Edge Case - Extremely Long Inputs
		String longText = "a".repeat(1000);
		nameField.sendKeys(longText);
		emailField.clear();
		emailField.sendKeys(longText + "@example.com");
		passwordField.clear();
		passwordField.sendKeys("Shubham@1309");
		confirmPasswordField.clear();
		confirmPasswordField.sendKeys("Shubham@1309");
		submitButton.click();
		wait.until(ExpectedConditions.urlToBe("https://example.com/success"));
		assert driver.getCurrentUrl().equals("https://example.com/success") : "Form submission failed for long inputs.";

		System.out.println("Edge cases validated successfully.");

		// Optional: Test Email/OTP Verification (if applicable)
		// Assuming an email/OTP verification step exists after submitting the form:
		WebElement otpField = driver.findElement(By.name("otp"));
		WebElement verifyOtpButton = driver.findElement(By.id("verifyOtpButton"));
		otpField.sendKeys("123456");
		verifyOtpButton.click();

		WebElement otpSuccessMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("otpSuccessMessage")));
		assert otpSuccessMessage.getText().equals("OTP verified successfully!") : "OTP verification failed.";

		System.out.println("OTP verification successful.");
	}
}
