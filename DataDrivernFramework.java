package com.SignUp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DataDrivernFramework {
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook wb;
	XSSFSheet ws;
	XSSFRow ro;
	File srcFile;

	@BeforeTest
	public void launch() {
		report = new ExtentReports("./Reports/Datadrivern.html");
		test = report.startTest("Datadriver");
		test.assignAuthor("Rangu Satish");
		test.assignCategory("Datadriventesting");
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\Satish_Selenium\\selenium_soft\\Selenium1\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Verify_Login() throws IOException {
		fi = new FileInputStream("D:/Satish_Selenium/Logindata.xlsx");
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet("Login");
		int rc = ws.getLastRowNum();
		ro = ws.getRow(0);
		int cc = ro.getLastCellNum();
		Reporter.log("no of rows are::" + rc + " "
				+ "no of columns in first row::" + cc, true);
		for (int i = 1; i <= rc; i++)

		{
			// test case starts here
			test = report.startTest("Login", "Verify Login");
			driver.get("https://opensource-demo.orangehrmlive.com/");
			test.log(LogStatus.INFO, driver.getCurrentUrl());
			String username = ws.getRow(i).getCell(0).getStringCellValue();
			String password = ws.getRow(i).getCell(1).getStringCellValue();
			test.log(LogStatus.INFO, username + "   " + password);
			
			
			driver.findElement(By.name("txtUsername")).sendKeys(username);
			driver.findElement(By.name("txtPassword")).sendKeys(password);
			driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
			String Expval = "dash";
			String Acval = driver.getCurrentUrl();
			if (Acval.toLowerCase().contains(Expval.toLowerCase())) {
				// take screen shot for pass
				srcFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(
						"D:/Testng_Framework/Iteration" + i + " .png"));
				test.log(
						LogStatus.PASS,
						"Login success",
						test.addScreenCapture("D:/Testng_Framework/Iteration"
								+ i + " .png"));
				// write into results columns as login success
				ws.getRow(i).createCell(2).setCellValue("Login success");
				// write into status columns as pass
				ws.getRow(i).createCell(3).setCellValue("Pass");
				Reporter.log("Login success", true);
			} else {
				// take screen shot for fail
				srcFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(
						"D:/Testng_Framework/Iteration" + i + " .png"));
				test.log(
						LogStatus.FAIL,
						"Login fail",
						test.addScreenCapture("D:/Testng_Framework/Iteration"
								+ i + " .png"));
				// get message
				String message = driver.findElement(By.id("spanMessage"))
						.getText();
				// write into results columns as login success
				ws.getRow(i).createCell(2).setCellValue(message);
				// write into status columns as pass
				ws.getRow(i).createCell(3).setCellValue("Fail");
				Reporter.log("Login Fail:::" + message, true);
			}
		}
		fi.close();
		fo = new FileOutputStream(new File("./Output/HrmResults.xlsx"));
		wb.write(fo);
		fo.close();
		wb.close();

	}

	@AfterTest
	public void endtest() {
		report.flush();
		report.endTest(test);
		driver.close();
	}
}
