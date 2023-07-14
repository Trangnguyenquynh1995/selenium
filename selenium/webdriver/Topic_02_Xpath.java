package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_login_Empty_Email_empty_Password() {
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.name("txtPassword")).sendKeys("");
		driver.findElement(By.name("txtCPassword")).sendKeys("");
		driver.findElement(By.name("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
	}
	
	@Test
	public void TC_02_login_Invavlid_Email() {
		driver.navigate().refresh();
		driver.findElement(By.id("txtFirstname")).sendKeys("Trang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("1234567");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234567");
		driver.findElement(By.name("txtPassword")).sendKeys("12345678");
		driver.findElement(By.name("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.name("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_login_Incorrect_Confirm_Password() {
		driver.navigate().refresh();
		driver.findElement(By.id("txtFirstname")).sendKeys("Trang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@567");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@56");
		driver.findElement(By.name("txtPassword")).sendKeys("12345678");
		driver.findElement(By.name("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.name("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_login_Password_Less_Than_6Char() {
		driver.navigate().refresh();
		driver.findElement(By.id("txtFirstname")).sendKeys("Trang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@567");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@567");
		driver.findElement(By.name("txtPassword")).sendKeys("1234");
		driver.findElement(By.name("txtCPassword")).sendKeys("1234");
		driver.findElement(By.name("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_login_Incorrect_Confirm_Password() {
		driver.navigate().refresh();
		driver.findElement(By.id("txtFirstname")).sendKeys("Trang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@567");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@567");
		driver.findElement(By.name("txtPassword")).sendKeys("1234567");
		driver.findElement(By.name("txtCPassword")).sendKeys("123456");
		driver.findElement(By.name("txtPhone")).sendKeys("0123456789");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_login_Invalid_phone_number() {
		driver.navigate().refresh();
		driver.findElement(By.id("txtFirstname")).sendKeys("Trang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@567");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@567");
		driver.findElement(By.name("txtPassword")).sendKeys("1234567");
		driver.findElement(By.name("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.name("txtPhone")).sendKeys("0123456");
		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div/div[8]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(By.name("txtPhone")).clear();
		driver.findElement(By.name("txtPhone")).sendKeys("123456");
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

