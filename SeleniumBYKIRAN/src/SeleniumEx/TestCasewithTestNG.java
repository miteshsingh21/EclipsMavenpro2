package SeleniumEx;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCasewithTestNG {
	//HomePage Test
	static WebDriver driver;
	@BeforeMethod
	public void Setup() throws Exception {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("Readfile.properties");
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
		}else if(broswername.equals("FireFox")) {
			System.setProperty("webdriver.chrome.driver", driverfilename);
		}else {
			System.out.println("Select valid Broswer");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.get(url);
		
	}
	@Test
	public void verifyWebSiteTitleTest() {
		String ActTitle = driver.getTitle();
		System.out.println(ActTitle);
		String ExpTitle = "The world's largest network of professionals and companies";
		AssertJUnit.assertEquals(ActTitle, ExpTitle);
	}
	@Test
	public void VerifyFindWorktab() {
		Actions act = new Actions(driver);
		WebElement toElement = driver.findElement(By.xpath("//a[contains(text(),' Find Work')]"));
		WebElement fromElement = driver.findElement(By.xpath("//a[contains(text(),'Contract to Hire')]"));
		act.moveToElement(toElement).pause(2000).click(fromElement).build().perform();
		//driver.findElement(By.xpath("//a[contains(text(),'View Work')]")).click();
		act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'View Work')]"))).click().perform();
		driver.findElement(By.xpath("//a[contains(text(),' Home')]")).click();
	}
	@Test
	public void VerifyLogoTest() {
		boolean flag = driver.findElement(By.xpath("//img[@class ='protimesheet_logo']")).isDisplayed();
		Assert.assertTrue(flag);
		System.out.println(flag);
	}
		
		
		
		
	
	/*@AfterMethod
	public void TearDown() {
		driver.close();
	}*/
	
	
	

}
