package utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

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
        String runMode = System.getProperty("runMode", "local");
        ChromeOptions options = new ChromeOptions();

        // Standard 2026 Production Flags
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox"); // Essential for Docker/Linux
        options.addArguments("--disable-dev-shm-usage"); // Prevents memory crashes in containers
        
        if (runMode.equalsIgnoreCase("remote")) {
            // High-Performance Headless for CI
            options.addArguments("--headless=new"); 
            
            // Note: If running inside the same Docker network, 
            // use "selenium-hub" instead of "localhost"
            String hubUrl = "http://localhost:4444/wd/hub";
            driver = new RemoteWebDriver(new URL(hubUrl), options);
            System.out.println("Running in REMOTE mode on Docker...");
        } else {
            driver = new ChromeDriver(options);
            System.out.println("Running in LOCAL mode...");
        }

        // Standard browser configurations
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Cookie Handling - Using a more robust check
        handleCookies();

    } catch (Exception e) {
        ReportManager.logFail("Failed to instantiate browser: " + e.getMessage());
        throw e; // Re-throw to fail the test officially
    }
}

private static void handleCookies() {
    try {
        // Find by partial ID or text is often safer for dynamic cookie banners
        WebElement cookieBtn = driver.findElement(By.xpath("//a[contains(@id,'cookie-accept') or contains(text(),'Accept')]"));
        cookieBtn.click();
    } catch (Exception e) {
        System.out.println("Cookie banner not found or already dismissed.");
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
