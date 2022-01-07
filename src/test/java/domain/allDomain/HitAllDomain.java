package domain.allDomain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HitAllDomain {

	public static WebDriver driver;
	public static HSSFCell domains;
	public static HSSFRow row;
	public void lunchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Chrome lunched");
		
		
	}
	
	public void fetchDomain() throws IOException, EmailException, MessagingException {
		
		File file = new File("/home/ramesh/eclipse-workspace/allDomain/AllDomains.xls");
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			try {
			row=sheet.getRow(i);
			domains=row.getCell(0);
			driver.get(domains.toString());
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Domain not working=:"+domains);
			TakeSS.takeScreenshot();
			SentMail emails= new SentMail();
			emails.sendEmail();
		}
		
	}
	
}
}
