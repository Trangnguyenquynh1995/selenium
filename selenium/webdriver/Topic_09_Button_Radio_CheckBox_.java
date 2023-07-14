package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_CheckBox_ {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	
	public void TC_01_Button_Fahasa() {
	driver.get("https://www.fahasa.com/customer/account/create");
	
	By loginButtonBy = By.cssSelector("button.fhs-btn-login");
	By signinButtonBy = By.cssSelector("li.popup-login-tab-login");
	
	driver.findElement(signinButtonBy).click();
	Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
	
	driver.findElement(By.id("login_username")).sendKeys("trang132@hotail.com");
	driver.findElement(By.id("login_password")).sendKeys("12345678");
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
	
	String rgbaColor = driver.findElement(loginButtonBy).getCssValue("background-color");
	System.out.println("RGBA = " + rgbaColor);
	
	String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
	System.out.println("Hexa = " + hexaColor);
	
	Assert.assertEquals(hexaColor, "#C92127");
	
	driver.navigate().refresh();
	
	driver.get("https://www.fahasa.com/customer/account/create");
	driver.findElement(signinButtonBy).click();
	
	//Remove attribute disabled of login button
	jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButtonBy));
	sleepInSecond(2);
	
	driver.findElement(loginButtonBy).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
	
	
	@Test
	public void TC_02_Default_Checkbox_Radio_Button() {
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	
	By dualACCheckboxBy = By.xpath("//input[@id='eq5']");
	
	driver.findElement(dualACCheckboxBy).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(dualACCheckboxBy).isSelected());
	
	driver.findElement(dualACCheckboxBy).click();
	Assert.assertFalse(driver.findElement(dualACCheckboxBy).isSelected());
	
	}
	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {
		
	}
	
	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
			// TODO: handle exception
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
