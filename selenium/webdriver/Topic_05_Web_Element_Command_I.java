package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_I {
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
		//WebBrowser command/method/ API
		//Driver instance/ variable
		
		//1
		driver.findElement(By.name("login")).click();
		
		//2
		WebElement emailTextBox = driver.findElement(By.id(""));
		emailTextBox.clear();
		emailTextBox.sendKeys("");
		emailTextBox.isDisplayed();
		
		WebElement element = driver.findElement(By.id(""));
		
		//Delete data in editable field (textbox/ textarea/ dropdown)
		emailTextBox.clear();
		
		//Input data in editable field (textbox/ textarea/ dropdown)
		element.sendKeys("..");
		element.sendKeys(Keys.ENTER);
		
		//Click on button/ link/ radio/ checkbox/ image/..
		element.click();
		
		// Return data in attribute of an element -> Email/phone number
		element.getAttribute("placeholder");
		
		//Get attribute of an element: font/ size/ color/ style (convert RGBa -> HEXA)
		element.getCssValue("background-color");
		
		//GUI
		element.getLocation();
		element.getSize();
		element.getRect();
		
		//take a screenshot and attach to a HTML report
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		//HTML tag name (By.id/class/css/name)
		//input of this step -> output of other step
		element = driver.findElement(By.cssSelector("#save-info-button"));
		String saveButtonTagname = element.getTagName();
		
		//Get text of a header/label/error msg...
		element.getText();
		
		//verify data: user can see and interact with
		element.isDisplayed();
		
		//Check if an element is enabled or not
		element.isEnabled();
		
		//Check if an element is selected (radio, check-box, drop-down)
		element.isSelected();
		
		//Submit a form
		element.submit();
		
		
		
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
