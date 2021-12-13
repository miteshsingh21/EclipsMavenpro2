package SeleniumEx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstEX {
	
	@Test
	public void test01() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.lambdatest.com/blog/create-testng-project-in-eclipse-run-selenium-test-script/");
	}
	
	
	
}
        
