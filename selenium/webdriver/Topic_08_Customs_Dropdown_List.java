package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Customs_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Wait de apply cho cac trang thai cua element (visible/ invisible/ presence/ click-able)
		explicitWait = new WebDriverWait(driver, 15);
		
		//Wait de tim element (find element)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		By parent = By.id("number-button");
		By child = By.cssSelector("ul#number-menu div");
		
		selectItemInDropdown(parent, child, "5");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));
		
		selectItemInDropdown(parent, child, "16");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='16']")));
		
		selectItemInDropdown(parent, child, "10");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")));
		
		selectItemInDropdown(parent, child, "19");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")));
		
	}
	
	
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		By parent = By.cssSelector("i.dropdown.icon");
		By child = By.cssSelector("div[role='option']>span");
		
		selectItemInDropdown(parent, child, "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Justen Kitsune']")));
		
		selectItemInDropdown(parent, child, "Matt");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Matt']")));
		
	}
	
	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		By parent = By.cssSelector("li.dropdown-toggle");
		By child = By.xpath("//ul[@class='dropdown-menu']//a[contains(.,'Second Option')]");
		
		selectItemInDropdown(parent, child, "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")));
	}
	
	
	public void TC_04_Editable() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		By parent = By.xpath("//i[@class='dropdown icon']");
		By child = By.xpath("//div[@class='visible menu transition']//span[@class='text' and text()='Argentina']");
	
		selectItemInDropdown(parent, child, "Argentina");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//*[@id=\"root\"]/div/div/input")));
		
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	public void selectItemInDropdown (By parentBy, By childBy, String expectedTextItem) {
		//1. Click vao 1 element cho no so ra tat ca item
				driver.findElement(parentBy).click();
				
				//2. Wait cho tat ca element dc load ra (co trong HTML/ DOM)
				//presence
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
				//2a. Wait cho icon loading trong dropdown vong 15 giay
				//exlicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
				
				
				//Store lai tat ca element (item cua drop-down)
				List<WebElement> allItems = driver.findElements(childBy);
				
				//System.out.println("All item = " + allItems.size());
				
				for (WebElement item : allItems) {
					if (item.getText().trim().equals(expectedTextItem)) {
						if (item.isDisplayed()) {//3. Neu item can chon nam trong view (nhin thay duoc) thi click vao
							item.click();
						}else { //4. Neu item can chon ko nhin thay thi scroll xuong & click vao
							jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
							item.click();
						}
					}
				}
	}
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element ["+ by + "] is displayed");
			return true;
		} else {
			System.out.println("Element ["+ by + "] is not displayed");
			return false;
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
