package SeleniumEx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestPassFailedstatus.class)
public class AmazonTestcase {
	 static WebDriver driver;
	// static Properties prop;
	 static Actions act;
	/* public static void properties() throws Exception {
		 Properties prop = new Properties();
			FileInputStream file = new FileInputStream("mantatest.properties");
			prop.load(file);
	 }*/
	 
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
	@Test()
	public void VerifyLogin() throws Exception {
		commoncod();
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("mantatest.properties");
		prop.load(file);
		act = new Actions(driver);
		WebElement toElement = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		WebElement fromElement = driver.findElement(By.xpath("//span[contains(text(),'Sign in')]"));
		act.moveToElement(toElement).pause(2000).click(fromElement).build().perform();
		driver.findElement(By.id(prop.getProperty("Email_id"))).sendKeys(prop.getProperty("Email"));
		driver.findElement(By.id(prop.getProperty("Continue_id"))).click();
		driver.findElement(By.id(prop.getProperty("Password_id"))).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.id(prop.getProperty("SignIn_id"))).click();
		String ActTitle = driver.getTitle();
		System.out.println(ActTitle);
		String ExpTitle = "Amazon.com. Spend less. Smile more.";
		Assert.assertEquals(ActTitle, ExpTitle);
	}
	@Test
	public void VerifyCardisopen() throws Exception {
		commoncod();
		Thread.sleep(2000);
		driver.findElement(By.id("nav-cart-count")).click();
	}
	
	

}
