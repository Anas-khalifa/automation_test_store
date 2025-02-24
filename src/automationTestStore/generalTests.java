package automationTestStore;

import java.time.Duration;
import java.util.List;

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

	@Test(priority = 1, enabled = false)
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

		String expetedSuccessMessage = driver.findElement(By.cssSelector(".maintext")).getText();
		String actualMessage = "YOUR ACCOUNT HAS BEEN CREATED!";
		Assert.assertEquals(actualMessage, expetedSuccessMessage);

		continueButton = driver.findElement(By.cssSelector("a[title='Continue']"));
		continueButton.click();

	}

	@Test(priority = 2,enabled=false)
	public void login() {
		WebElement loginNavigate = driver.findElement(By.linkText("Login or register"));
		loginNavigate.click();

		WebElement nameInput = driver.findElement(By.id("loginFrm_loginname"));
		WebElement passwordInput = driver.findElement(By.id("loginFrm_password"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
		nameInput.sendKeys(loginName);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

	@Test(priority = 3, enabled = false)
	public void menSection() {
		driver.get("https://automationteststore.com/index.php?rt=product/category&path=58");

		String menSections[] = { "Fragrance Sets", "Body & Shower", "Pre-Shave & Shaving", "Skincare" };
		int pageNumber = menSections.length;
		int numberOfProductsSelected = 0;
		while (pageNumber != 0) {
			pageNumber--;
			WebElement bodyPage = driver.findElement(By.linkText(menSections[pageNumber]));
			bodyPage.click();

			WebElement productContainor = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
			List<WebElement> products = productContainor.findElements(By.xpath("//a[@title='Add to Cart']"));

			for (int i = 0; i < products.size() / 2; i++) {
				products.get(i).click();
			}
			driver.navigate().back();

		}

	}

	@Test(priority=4,enabled=false)
	public void contactNumber() {
		String actualPhoneNumber=driver.findElement(By.xpath("//li[span[@class='phone']]")).getText().trim();
		String expectedPhoneNumber="+123 456 7890";
		Assert.assertEquals(actualPhoneNumber, expectedPhoneNumber);
		
	}
	
	@Test(priority=5)
	public void siteMap() {
		WebElement siteMapButton=driver.findElement(By.linkText("Site Map"));
		siteMapButton.click();
		WebElement content=driver.findElement(By.className("content"));
		List<WebElement> allItems=content.findElements(By.tagName("a"));
		for(int i=0;i<allItems.size();i++)
		System.out.println(allItems.get(i).getText().toUpperCase());
			
	}
}
