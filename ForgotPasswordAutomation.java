package Enzigma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordAutomation {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://app.nokodr.com/super/apps/auth/v1/index.html#/login");

		WebElement forgotPasswordLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot your password?")));
		forgotPasswordLink.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forgotPasswordPage")));

		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("sameermeshram1192@gmail.com");

		WebElement submitButton = driver.findElement(By.id("submitButton"));
		submitButton.click();

		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
		System.out.println("Success Message: " + successMessage.getText());
		assert successMessage.getText().equals("A password reset link has been sent to your email.")
				: "Success message is incorrect.";

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		System.out.println("Error Message: " + errorMessage.getText());
		assert errorMessage.getText().equals("Email not found.") : "Error message is incorrect.";
		driver.quit();

		emailField.clear();
		submitButton.click();

		WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert emailError.getText().equals("Email is required.") : "Email error message is incorrect.";

		emailField.clear();
		emailField.sendKeys("sameermeshram1192@gmail.com");
		submitButton.click();

		WebElement emailFormatError = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert emailFormatError.getText().equals("Invalid email format.") : "Email format error message is incorrect.";

		emailField.clear();
		emailField.sendKeys("sameermeshram@gmail.com");
		submitButton.click();

		Boolean noErrorMessage = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("emailFormatError")));
		assert noErrorMessage != null : "Error message should not be visible with a valid email.";

		System.out.println("Email validation tests completed successfully.");
		emailField.clear();
		emailField.sendKeys("sameermeshram1192@gmail.com");
		submitButton.click();

		wait.until(ExpectedConditions.urlToBe("https://www.nokodrplatform.com/dashboard"));
		System.out.println("Valid input test passed with registered email.");

		// Invalid Input (Non-Registered Email)
		emailField.clear();
		emailField.sendKeys("sameermeshram@gmail.com");
		submitButton.click();

		WebElement errorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
		assert errorMessage1.getText().equals("Email not found.")
				: "Error message for non-registered email is incorrect.";
		System.out.println("Invalid input test passed for non-registered email.");

		// Invalid Input (Invalid Email Format)
		emailField.clear();
		emailField.sendKeys("sameermeshram@123gmail.com");
		submitButton.click();

		WebElement formatErrorMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert formatErrorMessage.getText().equals("Invalid email format.")
				: "Error message for invalid email format is incorrect.";
		System.out.println("Invalid input test passed for invalid email format.");

		// Blank Email Field
		emailField.clear();
		submitButton.click();

		WebElement blankErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert blankErrorMessage.getText().equals("Email is required.")
				: "Error message for blank email field is incorrect.";
		System.out.println("Invalid input test passed for blank email field.");
		emailField.clear();
		emailField.sendKeys("sameermeshram1192@gmail.com");
		submitButton.click();

		// Wait for the success message and verify it
		WebElement successMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
		assert successMessage1.getText().equals("Reset link sent to your email.") : "Success message is incorrect.";
		System.out.println("Test Case 1: Valid input test passed.");
		emailField.clear();
		emailField.sendKeys("sameermeshram1192@gmail.com");
		submitButton.click();

		// Wait for error message and verify it
		WebElement emailFormatError1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailFormatError")));
		assert emailFormatError1.getText().equals("Invalid email format.")
				: "Error message for invalid email format is incorrect.";
		System.out.println("Test Case 3: Invalid email format test passed.");

		// Test Case 4: Blank Email Field
		emailField.clear();
		submitButton.click();

		// Wait for error message and verify it
		WebElement blankErrorMessage1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
		assert blankErrorMessage1.getText().equals("Email is required.")
				: "Error message for blank email field is incorrect.";
		System.out.println("Test Case 4: Blank email field test passed.");

		// Close the browser
		driver.quit();

	}
}
