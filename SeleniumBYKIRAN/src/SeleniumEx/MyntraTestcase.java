package SeleniumEx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestPassFailedstatus.class)
public class MyntraTestcase {
	static WebDriver driver;
	static Actions act;
	public static void commoncod() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("mantatest.properties");
		prop.load(file);
		String url = prop.getProperty("URL");
		System.out.println(url);
		String broswername = prop.getProperty("Broswer");
		System.out.println(broswername);
		String driverfilename = prop.getProperty("Driverfile");
		System.out.println(driverfilename);
		if(broswername.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", driverfilename);
			driver = new ChromeDriver();
		}else if(broswername.equals("Firefox")) {
			System.setProperty("webdriver.chrome.driver", driverfilename);
			driver = new FirefoxDriver();
		}else {
			System.out.println("Select aynone Broswer");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(url);
	}
	@Test(priority=1) //Test case-1
	public void Verify_Title() throws Exception {
		commoncod();
		String actTitle = driver.getTitle();
		System.out.println(actTitle);
	    String ExpTitle = "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
	    Assert.assertEquals(actTitle, ExpTitle);
	}
	@Test(priority=4)
	public void LinksCounts() throws Exception {
		commoncod();
		//Actions act = new Actions(driver);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		/*List<WebElement> links = driver.findElements(By.partialLinkText("shop"));
		
	     for (WebElement alinks : links) {
			act.moveToElement(alinks).click(alinks).pause(2000).build().perform();;
		}*/
		
	}
	@Test(priority=3)
	public void Verify_AllLinks_isworking() throws Exception {
		commoncod();
		driver.findElement(By.xpath("//a[contains(text(),'Men')]")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Women')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Kids')]")).click();
		driver.navigate().back();
		driver.close();
		
	}
	@Test(priority=2)
	public void Verify_Logolink_isworking() throws Exception {
		commoncod();
		boolean logo = driver.findElement(By.xpath("//div[@class='desktop-logoContainer']")).isEnabled();
		System.out.println(logo);
		driver.close();
	}
	@Test(priority=5)
	public void Verify_pagescroll() throws Exception {
		commoncod();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,4500)");
		driver.close();
	}
	@Test
	public void Verify_alllinkisopen() throws Exception {
		commoncod();
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("mantatest.properties");
		prop.load(file);
		act = new Actions(driver);
		WebElement toElements = driver.findElement(By.xpath("//a[contains(text(),'Men')]"));
		WebElement fromElement = driver.findElement(By.xpath("//a[contains(text(),'Sports Shoes')]"));
		act.moveToElement(toElements).pause(2000).click(fromElement).build().perform();
		driver.findElement(By.xpath(prop.getProperty("Search_xpath"))).sendKeys(prop.getProperty("search"));
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconSearch sprites-search']")).click();
	}
	
		
		
		
		
	
	
	
	

}
