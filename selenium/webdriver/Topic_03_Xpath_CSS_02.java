package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String name, emailAddress, password, phone;

	// Action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmemailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confrimpasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button");

	// Error msg
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confrimEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confrimPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		name = "Trang Ngyen";
		emailAddress = "12345@678";
		password = "12345678";
		phone = "0987654321";
	}

	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Epmty() {
		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confrimEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confrimPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
	}
	@Test
	public void TC_02_invalid_Email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("1234@123@");
		driver.findElement(confirmemailTextboxBy).sendKeys("1234@123@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confrimpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confrimEmailErrorMsgBy).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_Incorrect_Confirm_email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailTextboxBy).sendKeys("1234@123");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confrimpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confrimEmailErrorMsgBy).getText(),"Email nhập lại không đúng");
		
	}
	@Test
	public void TC_04_login_Incorrect_Confirm_Password() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confrimpasswordTextboxBy).sendKeys("1233456");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confrimPasswordErrorMsgBy).getText(),"Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_05_login_Password_Less_Than_6Char() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("12345");
		driver.findElement(confrimpasswordTextboxBy).sendKeys("12345");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confrimPasswordErrorMsgBy).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_06_login_Invalid_phone_number() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmemailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confrimpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys("0987654");
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(),"Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("123456");
		
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
