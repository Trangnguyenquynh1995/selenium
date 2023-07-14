package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Command_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	// kiểu dữ liệu nguyên thuỷ (primitive type)
	// Number
	// Interger: số nguyên không dấu
	// byte/ short/ int/ long
	//byte bnumber = 5;
	//short sNumber = 100;
	//int studentNumber = 1000;
	//long timeout = 20000;
	
	// Số thực (có dấu)
	// Float/ double
	//float studentPoint = 8.5f;
	//double employeeSalary = 28.5d;
	
	// Char (Kí tự)
	//char c = 'q';
	//char specialChar = '!';
	
	// Boolean (True/False)
	//boolean status = true;
	
	// Kiểu dữ liệu tham chiếu (Reference Type)
	// String
	//String studentAddress = new String("123 Hang Bai");
	//String studentName = "Trang Nguyen";
	
	// Array (tập hợp kiểu dữ liệu giống nhau)
	//String[] teacherName = {"Phuong Le","Phuong Dao"};
	
	// Class
	
	// Interface
	
	// Collection (Set/ Queue/ List)
	
	@Test
	public void TC_01_Browser() {
		// 1. Mở ra page Url 
		driver.get("https://www.messenger.com/");
		
		//2. Đóng 1 tab đang active
		// Handle Windows/Tab
		driver.close();
		
		//3. Đóng trình duyệt (ko care có bao nhiêu tab/window đang mở)
		driver.quit();
		
		//4. Lấy ID của tab/window đang active
		String messengerID = driver.getWindowHandle();
	
		//5. Lấy ra tất cả ID của các active tab/window
		Set<String> allIDs = driver.getWindowHandles();
		allIDs.clear();
		
		//6. Switch đến 1 tab/window nào
		driver.switchTo().window(messengerID);
		
		//7. Tìm ra 1 element vs locator nào
		WebElement emailTextbox = driver.findElement(By.id(""));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		//8. Tìm ra tất cả các element vs locator nào đó
		List<WebElement> textboxes = driver.findElements(By.id(""));
		
		//9. Trả về URL của page hiện tại
		String homePageURL = driver.getCurrentUrl();
		
		//10. Trả về HTML source của page hiện tại
		String homePageSource = driver.getPageSource();
		
		//11. Trả về title của page hiện 
		String homePageTitle = driver.getTitle();
		
		//12. Get/Delete côkie của page
		//Get cookie sau khi login -> truyền vào Class khác -> Reduce time login cho từng 
		driver.manage().deleteAllCookies();
		
		//13. Get log of browser 
		driver.manage().logs().getAvailableLogTypes();
		
		//14. Chờ việc tìm element (findElement/findElements)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//15. Chờ 1 page load thành công (Optional)
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//16. Chờ 1 script execute thành công (Optional)
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		//17. Mở browser full màn 
		driver.manage().window().fullscreen();
		
		//18. Mở browser maximize
		driver.manage().window().maximize();
		
		//19. Lấy ra vị trí hiện tại browser
		driver.manage().window().getPosition();
		
		//20. Set cho browser tại vị trí nào đó (ít sử dụng)
		//driver.manage().window().setPosition(new Point(0, 0));
		
		//21. Lấy ra kích thước hiện tại của browser (rộng/cao)
		driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension (1920,1080));
		
		//22. Back to page
		driver.navigate().back();
		
		//23. Forward to page
		driver.navigate().forward();
		
		//24. Refresh page = F5
		driver.navigate().refresh();
		
		//25.Keep History
		driver.navigate().to("");
		
		//26. Windows/Tab
		driver.switchTo().window("");
		
		//27. Alert
		driver.switchTo().alert();
		
		//28. Frame/ IFrame
		driver.switchTo().frame("");
		
	}
	
	@Test
	public void TC_02_Component() {
		 
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
