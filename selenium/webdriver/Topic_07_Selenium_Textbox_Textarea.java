package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Selenium_Textbox_Textarea {

	// Biến toàn cục (Global variable): sử dụng cho toàn bộ class/ function/ block
	// code:
	WebDriver driver;
	String userID, password, getLoginPageUrl, name, gender, dateOfBirthInput, dateOfBirthOutput, addressInput,
			addressOutput, city, state, pin, phone, email, customerID;

	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;

	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor JsExecutor;

	By nameTextboxby = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']");
	By genderTextboxBy = By.name("gender");
	By dateOfBirthTextboxBy = By.name("dob");
	By addressTextareaBy = By.name("addr");
	By cityTextboxby = By.name("city");
	By stateTextboxby = By.name("state");
	By pinTextboxby = By.name("pinno");
	By phoneTextboxby = By.name("telephoneno");
	By emailTextboxby = By.name("emailid");
	By passwordTextboxby = By.name("password");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		// Add kiểu tưởng minh trong Java:
		JsExecutor = (JavascriptExecutor) driver;

		name = "Angela Jolie";
		gender = "female";
		dateOfBirthInput = "01/01/1990";
		dateOfBirthOutput = "1990-01-01";
		addressInput = "234 PO Briged\nNew"; // ký tự \n => khi sendkys java hiểu chỗ đấy phải xuống
		addressOutput = "234 PO Briged New";
		city = "Los Angeles";
		state = "California";
		pin = "225588";
		phone = "0987678921";
		email = "angela" + getRandomNumber() + "@mail.net";

		editAddressInput = "865 PO Briged\\nNew York";
		editAddressOutput = "865 PO Briged New York";
		editCity = "Newyork";
		editState = "Nevada";
		editPin = "867212";
		editPhone = "0756866345";
		editEmail = "mangala" + getRandomNumber() + "@hotmail.net";

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://demo.guru99.com/v4/");

	}

	@Test
	public void TC_01_Register() {
		getLoginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);

		driver.findElement(By.name("btnLogin")).click();

		// Biến cục bộ (Local variable):
		// chỉ dùng trong phạm vi 1 function/ block code:
		// String userID = driver.findElement(By
		// .xpath("//td[text()='User ID :']/following-sibling::td")).getText();

		// String password = driver.findElement(By
		// .xpath("//td[text()='Password :']/following-sibling::td")).getText();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();

		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		// Cách 1:
		// driver.navigate().back();
		// driver.navigate().back();

		// Cách 2:
		// driver.get(loginURL);

		// Cách 3:
		driver.get(getLoginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//marquee[@class='heading3' and " + "text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxby).sendKeys(name);
		driver.findElement(genderRadioBy).click();

		JsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthTextboxBy));

		driver.findElement(dateOfBirthTextboxBy).sendKeys(dateOfBirthInput);
		driver.findElement(addressTextareaBy).sendKeys(addressInput);
		driver.findElement(cityTextboxby).sendKeys(city);
		driver.findElement(stateTextboxby).sendKeys(state);
		driver.findElement(pinTextboxby).sendKeys(pin);
		driver.findElement(phoneTextboxby).sendKeys(phone);
		driver.findElement(emailTextboxby).sendKeys(email);
		driver.findElement(passwordTextboxby).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());

		String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/ following-sibling::td"))
				.getText();
		String customerGender = driver.findElement(By.xpath("//td[text()='Gender']/ following-sibling::td")).getText();
		String customerBirthDate = driver.findElement(By.xpath("//td[text()='Birthdate']/ following-sibling::td"))
				.getText();
		String customerAddress = driver.findElement(By.xpath("//td[text()='Address']/ following-sibling::td"))
				.getText();
		String customerCity = driver.findElement(By.xpath("//td[text()='City']/ following-sibling::td")).getText();
		String customerState = driver.findElement(By.xpath("//td[text()='State']/ following-sibling::td")).getText();
		String customerPin = driver.findElement(By.xpath("//td[text()='Pin']/ following-sibling::td")).getText();
		String customerMobilePhone = driver.findElement(By.xpath("//td[text()='Mobile No.']/ following-sibling::td"))
				.getText();
		String customerEmail = driver.findElement(By.xpath("//td[text()='Email']/ following-sibling::td")).getText();

		Assert.assertEquals(customerName, name);
		Assert.assertEquals(customerGender, gender);
		Assert.assertEquals(customerBirthDate, dateOfBirthOutput);
		Assert.assertEquals(customerAddress, addressOutput);
		Assert.assertEquals(customerCity, city);
		Assert.assertEquals(customerState, state);
		Assert.assertEquals(customerPin, pin);
		Assert.assertEquals(customerMobilePhone, phone);
		Assert.assertEquals(customerEmail, email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/ following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// Verify dữ liệu thông tin khách hàng được show ra trong phần edit:
		Assert.assertEquals(driver.findElement(nameTextboxby).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextboxBy).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextboxBy).getAttribute("value"), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(addressTextareaBy).getText(), addressInput);
		Assert.assertEquals(driver.findElement(cityTextboxby).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextboxby).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxby).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxby).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxby).getAttribute("value"), email);

		// edit dữ liệu:
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAddressInput);

		driver.findElement(cityTextboxby).clear();
		driver.findElement(cityTextboxby).sendKeys(editCity);

		driver.findElement(stateTextboxby).clear();
		driver.findElement(stateTextboxby).sendKeys(editState);

		driver.findElement(pinTextboxby).clear();
		driver.findElement(pinTextboxby).sendKeys(editPin);

		driver.findElement(phoneTextboxby).clear();
		driver.findElement(phoneTextboxby).sendKeys(editPhone);

		driver.findElement(emailTextboxby).clear();
		driver.findElement(emailTextboxby).sendKeys(editEmail);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customerdetails  Successfully!!!']"))
						.isDisplayed());

		String editCustomerID = driver.findElement(By.xpath("//td[text()='CustomerID']/ following-sibling::td"))
				.getText();
		String editCustomerName = driver.findElement(By.xpath("//td[text()='Customer Name']/ following-sibling::td"))
				.getText();
		String editCustomerGender = driver.findElement(By.xpath("//td[text()='Gender']/ following-sibling::td"))
				.getText();
		String editCustomerBirthDate = driver.findElement(By.xpath("//td[text()='Birthdate']/ following-sibling::td"))
				.getText();
		String editCustomerAddress = driver.findElement(By.xpath("//td[text()='Address']/ following-sibling::td"))
				.getText();
		String editCustomerCity = driver.findElement(By.xpath("//td[text()='City']/ following-sibling::td")).getText();
		String editCustomerState = driver.findElement(By.xpath("//td[text()='State']/ following-sibling::td"))
				.getText();
		String editCustomerPin = driver.findElement(By.xpath("//td[text()='Pin']/ following-sibling::td")).getText();
		String editCustomerMobilePhone = driver
				.findElement(By.xpath("//td[text()='Mobile No.']/ following-sibling::td")).getText();
		String editCustomerEmail = driver.findElement(By.xpath("//td[text()='Email']/ following-sibling::td"))
				.getText();

		Assert.assertEquals(editCustomerID, customerID);
		Assert.assertEquals(editCustomerName, name);
		Assert.assertEquals(editCustomerGender, gender);
		Assert.assertEquals(editCustomerBirthDate, dateOfBirthOutput);

		Assert.assertEquals(editCustomerAddress, editAddressOutput);
		Assert.assertEquals(editCustomerCity, editCity);
		Assert.assertEquals(editCustomerState, editState);
		Assert.assertEquals(editCustomerPin, editPin);
		Assert.assertEquals(editCustomerMobilePhone, editPhone);
		Assert.assertEquals(editCustomerEmail, editEmail);

	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
