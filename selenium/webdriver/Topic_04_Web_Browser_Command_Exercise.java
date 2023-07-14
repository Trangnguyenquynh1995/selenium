package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Command_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Verify_URL() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();
		String registerPageURL = driver.getCurrentUrl();
		Assert.assertEquals(registerPageURL, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Tile() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();
		
		String registerPageURL = driver.getCurrentUrl();
		Assert.assertEquals(registerPageURL, "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
	}
	@Test
	public void TC_04_Get_Page_Source_code() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();
		String createAccountPageSource = driver.getPageSource();
		Assert.assertTrue(createAccountPageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
