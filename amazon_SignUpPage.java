package com.SignUp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class amazon_SignUpPage {

	static WebDriver driver;

	@BeforeClass
	public void before_singup() {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Satish_Selenium\\selenium_soft\\Selenium1\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void Signup() throws InterruptedException {
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[contains(text(),'Hello, Sign in')]")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("Sateesh");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='ap_phone_number']")).sendKeys("9381013241");
		String phoneNo;
			

		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("satishchary2007@gmail.com");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Satti@2505");
		
		String sample2 = null;
		if (sample2.length() < 6) {
			System.out.println("username Should be greater than 6 Chars");
		}

		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='auth-pv-enter-code']")).sendKeys("345734");
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//input[@id='auth-verify-button']")).click();
		Thread.sleep(3000);
	
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
