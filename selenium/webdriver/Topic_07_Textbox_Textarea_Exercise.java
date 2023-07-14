package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		By userNameBy = By.xpath("//input[contains(@name,'username')]");
		By passworbBy = By.xpath("//input[contains(@name,'password')]");
		By signinButtonBy = By.xpath("//button[@type='submit']");
		By pimButtonBy = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='PIM']/parent::a");
		By addEmployeeBy = By.xpath("//a[@class='oxd-topbar-body-nav-tab-item' and text()='Add Employee']/parent::li");
		
		driver.findElement(userNameBy).sendKeys("Admin");
		driver.findElement(passworbBy).sendKeys("admin123");
		driver.findElement(signinButtonBy).click();
		
		driver.findElement(pimButtonBy).click();
		driver.findElement(addEmployeeBy).click();
		
		
	}
	
	@Test
	public void TC_02_() {
		
	}
	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
