package utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {
	public static WebDriver driver;

	/**
	 * Method to instantiate web browser
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static void instantiateWebBrowser(String url) throws Exception {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("URL : " + url);
			
			try {
				driver.findElement(By.xpath("//a[@id='cookie-accept']")).click();
			} catch (Exception e) {
				ReportManager.logInfo("Accept cookies didn't display");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Failed to instantiate the browser");
			System.out.println("Failed to instantiate the browser");
		}
	}

	/**
	 * method to scroll to the element
	 * 
	 * @param Element
	 * @throws Exception
	 */
	public void scrollIntoView(WebElement Element) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", Element);
		} catch (Exception e) {
			e.printStackTrace();
			ReportManager.logFail("Element not visible: " + Element);
			throw new Exception("Element not visible: " + Element);
		}
	}

	/**
	 * Method to quit the driver
	 * 
	 * @throws Exception
	 */
	public static void tearDown() throws Exception {
		try {
			driver.close();
			driver.quit();
			driver = null;
		} catch (Exception e) {
			ReportManager.logFail("Getting some problem while closing session");
			e.printStackTrace();
		}
	}
}
