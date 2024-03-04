package magentoGitHub;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.errorprone.annotations.ThreadSafe;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String[] firstNames = { "bana", "fatima", "Deyaa", "Lala", "zaid", "lana", "Nana" };
	String[] lastNames = { "fadi", "Ayman", "Deyaa", "Mazen", "zaid", "abedalkareem", "Neel", "Naseem" };

	String pass = "ASD123asd";

	int firstNamesIndex = rand.nextInt(firstNames.length);
	int lastNamesIndex = rand.nextInt(lastNames.length);
	int emailRand = rand.nextInt(99);

	String emailText = firstNames[firstNamesIndex] + lastNames[lastNamesIndex] + emailRand + "@gmail.com";

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();

		driver.get("https://magento.softwaretestingboard.com/");
	}

	@Test(priority = 1)
	public void creataccount() throws InterruptedException {

		Thread.sleep(2000);
		WebElement creatAccount = driver.findElement(By.linkText("Create an Account"));
		creatAccount.click();

		Thread.sleep(2000);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailInput = driver.findElement(By.id("email_address"));
		WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
		WebElement passworConfirmdInput = driver.findElement(By.cssSelector("#password-confirmation"));
		WebElement createAccount = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		firstNameInput.sendKeys(firstNames[firstNamesIndex]);
		lastNameInput.sendKeys(lastNames[lastNamesIndex]);
		emailInput.sendKeys(emailText);

		passwordInput.sendKeys(pass);
		passworConfirmdInput.sendKeys(pass);

		Thread.sleep(2000);
		createAccount.click();

		Thread.sleep(5000);

		WebElement Msg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		String actualMsg = Msg.getText();

		Assert.assertEquals(actualMsg.contains("registering"), true, "this is check crat an account ");

	}

	@Test(priority = 2)
	public void logout() throws InterruptedException {

		Thread.sleep(2000);
		driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");

		WebElement signOutMsg = driver.findElement(By.xpath("//span[@class='base']"));
		String actualMsg = signOutMsg.getText();
		String expectedMsg = "You are signed out";

		Assert.assertEquals(actualMsg, expectedMsg);

	}

	@Test(priority = 3)
	public void logIn() throws InterruptedException {

		WebElement signInLink = driver
				.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
		Thread.sleep(2000);
		signInLink.click();

		Thread.sleep(5000);
		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement passInput = driver.findElement(By.id("pass"));
		WebElement buttonSignIn = driver.findElement(By.cssSelector(
				"body > div:nth-child(5) > main:nth-child(3) > div:nth-child(4) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > fieldset:nth-child(2) > div:nth-child(4) > div:nth-child(1) > button:nth-child(1)"));

		emailInput.sendKeys(emailText);
		passInput.sendKeys(pass);
		Thread.sleep(2000);
		buttonSignIn.click();

		Thread.sleep(3000);
		WebElement welcomHeader = driver.findElement(By.className("logged-in"));
		String actualWelcomeMsg = welcomHeader.getText();
		Assert.assertEquals(actualWelcomeMsg.contains("Welcome"), true, "this is cheack completed logIn process");
	}

	@Test(priority = 4)
	public void jackeOneMan() throws InterruptedException {

		driver.get("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");

		Thread.sleep(3000);
		WebElement container = driver.findElement(By.className("product-items"));
		List<WebElement> ProductItems = container.findElements(By.tagName("li"));
		ProductItems.get(0).click();

		Thread.sleep(3000);

		WebElement containerSize = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizeItem = containerSize.findElements(By.tagName("div"));
		int s = rand.nextInt(5);
		sizeItem.get(s).click();

		WebElement containerColor = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colorItem = containerColor.findElements(By.tagName("div"));
		int c = rand.nextInt(3);
		colorItem.get(c).click();

		Thread.sleep(3000);
		WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
		addToCartButton.click();
		
		
		Thread.sleep(3000);
		
		WebElement addToCart = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		  String addToCartActual = addToCart.getText();
	     Assert.assertEquals(addToCartActual.contains("added"),true,"this is check added item");
		

	}

	@AfterTest
	public void afterTesting() {

	}

}
