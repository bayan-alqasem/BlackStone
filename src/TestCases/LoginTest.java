package TestCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends Parameter {

	@BeforeTest
	public void setUp() {

		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void logInTest() throws InterruptedException {

		driver.get(URL);

		WebElement emailAddress = driver.findElement(By.id("mat-input-0"));
		emailAddress.sendKeys(email);

		WebElement thePassword = driver.findElement(By.id("mat-input-1"));
		thePassword.sendKeys(password);

		WebElement sginInButton = driver.findElement(By.cssSelector(".block.primary"));
		sginInButton.click();

		Thread.sleep(2000);

		String expectedSuccessfulLoginMessage = "Welcome To Dashboard";
		String actualSuccessfulLoginMessage = driver
				.findElement(By.xpath("/html/body/app-root/admin-layout/section/div[3]/dashboard/p")).getText();

		assertEquals(actualSuccessfulLoginMessage, expectedSuccessfulLoginMessage);
	}

	@Test(priority = 2)
	public void homePageTitle() {
		String homePageTitle = driver.getTitle();
		System.out.println("HomePageTitle-->" + homePageTitle);

	}

	@Test(priority = 3)
	public void logOutTest() throws InterruptedException {
		WebElement img = driver.findElement(By.xpath(
				"/html/body/app-root/admin-layout/section/div[1]/header/section/navbar/div/div[2]/nav-actions/div/ul/li[3]/div/div[2]/img"));
		img.click();

		WebElement ulContainer = driver.findElement(By.xpath(
				"/html/body/app-root/admin-layout/section/div[1]/header/section/navbar/div/div[2]/nav-actions/div/ul/li[3]/div/div[2]/ul"));
		Thread.sleep(2000);
		ulContainer.findElements(By.tagName("li")).get(2).click();

		boolean actual = driver.findElement(By.className("login")).getText().contains("Sign In");
		assertEquals(actual, true);

	}

	@AfterTest
	public void postTesting() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}
}
