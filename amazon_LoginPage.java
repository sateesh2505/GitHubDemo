package com.SignUp; 
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class amazon_LoginPage {

	static WebDriver driver;

	@BeforeClass
	public void before_login() {

		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\Satish_Selenium\\selenium_soft\\Selenium1\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void login() throws InterruptedException {

		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.findElement(
				By.xpath("//span[contains(text(),'Hello, Sign in')]")).click();
		Thread.sleep(5000);

		/* CASE- 1. Both User name and Password are entered correctly */
		// Check Email field exists or not
		try {
			WebElement element = driver.findElement(By
					.xpath("//input[@id='ap_email']"));
			System.out.println("eamil exists");
			element.sendKeys("satishchary2007@gmail.com");
			Thread.sleep(3000);
		} catch (Throwable e) {
			System.out.println("Emailid not found: " + e.getMessage());
		}

		driver.findElement(By.xpath("//input[@id='continue']")).click();
		Thread.sleep(3000);

		// Check Password field exists or not
		try {
			WebElement element1 = driver.findElement(By
					.xpath("//input[@id='ap_password']"));
			String sample2 = driver.findElement(
					By.xpath("//input[@id='ap_password']")).getAttribute(
					"value");
			System.out.println("Password Exsits");
			element1.sendKeys("Satti@2505");
			if (sample2.length() < 6) {
				System.out.println("username Should be greater than 6 Cahrs");
			}

			Thread.sleep(3000);
		}

		catch (Throwable e) {
			System.out.println("Password not found" + e.getMessage());
		}
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Category')]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Headphones')]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("//div[@id='100_dealView_0']//div[@class='a-row layer backGround']"))
				.click();
		Thread.sleep(3000);
		String value = driver.getWindowHandle();
		WebElement element = driver.findElement(By.
			xpath("//div[@class='s-result-list s-search-results sg-row']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//span[1]//a[1]//div[1]//img[1]"));
		element.click();
		
		Thread.sleep(3000);

		Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        driver.findElement(By.id("add-to-cart-button")).click();
        Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[@id='hlb-view-cart-announce']")).click(); 
        Thread.sleep(3000);
      /*  
        driver.switchTo().frame(0);
        driver.findElement(By.
        	xpath("//*[@id='sc-item-C6707db0b-4946-4c54-b893-0ed92aa3a383']/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input")).click();
        Thread.sleep(3000);*/
                 
       	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}
}
