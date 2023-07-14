package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By emailTextBoxBy = By.id("mail");
	By ageUnder18By = By.id("under_18");
	By educationTextBoxBy = By.id("edu");
	By jobRole1By = By.id("job1");
	By developmentCheckBoxBy = By.id("development");
	By sliderOneBy = By.id("slider-1");
	By passWordBy = By.id("password");
	By ageBy = By.id("radio-disabled");
	By biographyBy = By.id("bio");
	By jobRole3By = By.id("job3");
	By checkBoxDisabledBy = By.xpath("//*[@id=\"check-disbaled\"]");
	By slider2By = By.id("slider-2");
	By javaCheckboxBy = By.id("java");
	By ageOver18By = By.id("over_18");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01_Element_Is_Displayed() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextBox = driver.findElement(By.id("mail"));
		if(emailTextBox.isDisplayed()){
			emailTextBox.sendKeys("test@hotmail.com");
			System.out.println("Email textbox is displayed");
			
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Radio button is displayed");
			
		} else {
			System.out.println("Radio button is not displayed");
		}
		WebElement educationTextBox = driver.findElement(By.id("edu"));
		if (educationTextBox.isDisplayed()) {
			educationTextBox.sendKeys("HUBT");
			System.out.println("Education textbox is displayed");
			
		} else {
			System.out.println("Education textbox is not displayed");
		}
	}
	@Test
	public void TC_02_Enabled_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	
			Assert.assertTrue(isElementEnabled(emailTextBoxBy));
		
			Assert.assertTrue(isElementEnabled(ageUnder18By));
	
			Assert.assertTrue(isElementEnabled(educationTextBoxBy));
		
			Assert.assertTrue(isElementEnabled(jobRole1By));
		
			Assert.assertTrue(isElementEnabled(developmentCheckBoxBy));

			Assert.assertTrue(isElementEnabled(sliderOneBy));
		
			Assert.assertTrue(isElementEnabled(passWordBy));
		
			Assert.assertFalse(isElementEnabled(ageBy));
		
			Assert.assertFalse(isElementEnabled(biographyBy));
		
			Assert.assertFalse(isElementEnabled(jobRole3By));
		
			Assert.assertFalse(isElementEnabled(checkBoxDisabledBy));
		
			Assert.assertFalse(isElementEnabled(slider2By));
		
	}
	public boolean isElementEnabled(By by) {
			WebElement element = driver.findElement(by);
			if (element.isEnabled()) {
				System.out.println("Element ["+ by + "] is enabled");
				return true;
			} else {
				System.out.println("Element ["+ by + "] is disabled");
				return false;
			}
		}
	
	@Test
	public void TC_03_Element_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(ageUnder18By).click();
		driver.findElement(javaCheckboxBy).click();
		Assert.assertTrue(isElementSelected(ageUnder18By));
		Assert.assertTrue(isElementSelected(javaCheckboxBy));
		
		driver.findElement(ageOver18By).click();
		driver.findElement(javaCheckboxBy).click();
		Assert.assertFalse(isElementSelected(ageUnder18By));
		Assert.assertFalse(isElementSelected(javaCheckboxBy));
	}

	public boolean isElementSelected(By by) {
			WebElement element = driver.findElement(by);
			if (element.isSelected()) {
				System.out.println("Element ["+ by + "] is selected");
				return true;
			} else {
				System.out.println("Element ["+ by + "] is deselected");
				return false;
			}
		}
		
	@Test
	public void TC_04_Register_MailChimp() {
			driver.get("https://login.mailchimp.com/signup/");
			
			By mailTextboxBy = By.xpath("//input[@id='email']");
			By usernameTextboxBy = By.id("new_username");
			By passwordBoxBy = By.id("new_password");
			By registerMailCheckboxBy = By.xpath("//*[@id=\"marketing_newsletter\"]");
			By signupButtonBy = By.id("create-account-enabled");
			By lowerCaseValidBy = By.xpath("//li[@class='lowercase-char completed']");
			By upperCaseValidBy = By.xpath("//li[@class='uppercase-char completed']");
			By numberValidBy = By.xpath("//li[@class='number-char completed']");
			By specialCharValidBy = By.xpath("//li[@class='special-char completed']");
			By eightcharValidBy = By.xpath("//li[@class='8-char completed']");
			
			driver.findElement(mailTextboxBy).sendKeys("quynhtrang341@yopmail.com");
			
			//lower case
			driver.findElement(passwordBoxBy).sendKeys("a");
			Assert.assertTrue(driver.findElement(lowerCaseValidBy).isDisplayed());
			
			//upper case
			driver.findElement(passwordBoxBy).sendKeys("B");
			Assert.assertTrue(driver.findElement(upperCaseValidBy).isDisplayed());
			
			//number
			driver.findElement(passwordBoxBy).sendKeys("12");
			Assert.assertTrue(driver.findElement(numberValidBy).isDisplayed());
			
			//special char
			driver.findElement(passwordBoxBy).sendKeys("@#");
			Assert.assertTrue(driver.findElement(specialCharValidBy).isDisplayed());
			
			//8 characters but not valid
			driver.findElement(passwordBoxBy).clear();
			sendkeyToElement(passwordBoxBy,"abcd12@#");
			Assert.assertTrue(driver.findElement(eightcharValidBy).isDisplayed());
			
			//valid password
			driver.findElement(passwordBoxBy).clear();
			sendkeyToElement(passwordBoxBy,"abCD12@#");
			Assert.assertFalse(driver.findElement(lowerCaseValidBy).isDisplayed());
			Assert.assertFalse(driver.findElement(upperCaseValidBy).isDisplayed());
			Assert.assertFalse(driver.findElement(numberValidBy).isDisplayed());
			Assert.assertFalse(driver.findElement(specialCharValidBy).isDisplayed());
			Assert.assertFalse(driver.findElement(eightcharValidBy).isDisplayed());
			
		}
		public boolean isElementDisplayed(By by) {
			WebElement element = driver.findElement(by);
			if (element.isSelected()) {
				return true;
			} else {
				return false;
			}
		}
			
		public void sendkeyToElement(By by, String value) {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		}
		
	public void clickToElement(By by) {
				WebElement element = driver.findElement(by);
				element.click();
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
