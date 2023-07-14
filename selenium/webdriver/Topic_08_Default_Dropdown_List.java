package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	Actions action;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		
	}
	
	
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		
		
		String firstName = "Trang";
		String lastName = "Nguyen";
		String emailAddress = "qwerty" + getRandomNumber() + "@hotmail.com";
		String dayOfBirth = "15";
		String monthOfBirth = "December";
		String yearOfBirth = "1999";
		String companyName = "QWERTY";
		String password = "123456789";
		String confirmPassword = "123456789";
		
		By genderFemaleBy = By.id("gender-female");
		By firstNameBy = By.id("FirstName");
		By lastNameBy = By.id("LastName");
		By dateDropdpwnBy = By.name("DateOfBirthDay");
		By monthDropdownBy = By.name("DateOfBirthMonth");
		By yearDropdownBy = By.name("DateOfBirthYear");
		By emailAddressBy = By.id("Email");
		By companyNameBy = By.id("Company");
		
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(genderFemaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		
		select = new Select(driver.findElement(dateDropdpwnBy));
		
		//Select 1 item A
		//select.selectByIndex(15);
		
		//select.selectByValue("6923");
		
		select.selectByVisibleText(dayOfBirth);
		
		
		//De-select 1 item A: select.deselectAll();
		
		//Check a drop-down is  multiple select or not
		Assert.assertFalse(select.isMultiple());
		
		//CHeck if item A is selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"15");
		
		//get the total selected item number in a drop-down
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(monthDropdownBy));
		select.selectByVisibleText(monthOfBirth);
		
		select = new Select(driver.findElement(yearDropdownBy));
		select.selectByVisibleText(yearOfBirth);
		
		
		driver.findElement(emailAddressBy).sendKeys(emailAddress);
		driver.findElement(companyNameBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.className("email")).sendKeys(emailAddress);
		driver.findElement(By.className("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertTrue(driver.findElement(genderFemaleBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"),lastName);
		
		select = new Select(driver.findElement(dateDropdpwnBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dayOfBirth);
		
		select = new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), monthOfBirth);
		
		select = new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), yearOfBirth);
		
		Assert.assertEquals(driver.findElement(emailAddressBy).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(companyNameBy).getAttribute("value"), companyName);
	}
	
	@Test
	public void TC_02_Rode() {
		driver.get("https://rode.com/en/support/where-to-buy");
		
		select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("Vietnam");
		
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");
		
		driver.findElement(By.id("map_search_query")).sendKeys("Ha Noi");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		
		
		
	}
	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {
		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
