package automationTestStore;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class generalTests {

	WebDriver driver = new ChromeDriver();
	String URL = "https://automationteststore.com/";
	String loginName = "JonDeo00", password = "123AAA$$88asdf";

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1, enabled = true)
	public void signup() throws InterruptedException {
		WebElement signupNavigate = driver.findElement(By.linkText("Login or register"));
		signupNavigate.click();
		WebElement continueButton = driver.findElement(By.cssSelector("button[title='Continue']"));
		continueButton.click();

		WebElement firstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement lastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement emailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement phoneInput = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement faxInput = driver.findElement(By.id("AccountFrm_fax"));
		WebElement companyInput = driver.findElement(By.id("AccountFrm_company"));
		WebElement address1Input = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement cityInput = driver.findElement(By.id("AccountFrm_city"));
		WebElement stateInput = driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement zipCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement counterInput = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement loginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement passwordInput = driver.findElement(By.id("AccountFrm_password"));
		WebElement confirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement subscribeRadio = driver.findElement(By.id("AccountFrm_newsletter0"));
		WebElement privacyPolicyAgreement = driver.findElement(By.id("AccountFrm_agree"));

		// Filling out the registration form
		firstNameInput.sendKeys("John");
		lastNameInput.sendKeys("Doe");
		emailInput.sendKeys("johndoe@example00.com");
		phoneInput.sendKeys("1234567890");
		faxInput.sendKeys("9876543210");
		companyInput.sendKeys("TechCorp");
		address1Input.sendKeys("123 Main Street");
		cityInput.sendKeys("New York");

		// Selecting state from dropdown
		Select stateDropdown = new Select(stateInput);
		stateDropdown.selectByIndex(2);

		zipCodeInput.sendKeys("10001");
		// Selecting country from dropdown
		Select countryDropdown = new Select(counterInput);
		countryDropdown.selectByIndex(1);

		// Enter login details
		loginNameInput.sendKeys(loginName);
		passwordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(password);

		// Select "No" for the newsletter subscription
		subscribeRadio.click();
		privacyPolicyAgreement.click();

		continueButton = driver.findElement(By.cssSelector("button[title='Continue']"));
		continueButton.click();
		Thread.sleep(2000);
		
		String expetedSuccessMessage=driver.findElement(By.cssSelector(".maintext")).getText();
		String actualMessage="YOUR ACCOUNT HAS BEEN CREATED!";
		Assert.assertEquals(actualMessage, expetedSuccessMessage);
		
		continueButton = driver.findElement(By.cssSelector("a[title='Continue']"));
		continueButton.click();
		
		

	}

}
