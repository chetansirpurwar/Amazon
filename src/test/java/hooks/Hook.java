package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {

    public static WebDriver driver;

    @Before
    public void setUp() {
    	// Configure Chrome options to ignore SSL certificate errors
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability("acceptInsecureCerts", true);
        
       
        // **Enable headless mode**
        options.addArguments("--headless"); // **<-- Added**
        options.addArguments("--disable-gpu"); // **<-- Added**
        options.addArguments("--window-size=1920,1080"); // **<-- Added**
        options.addArguments("--ignore-certificate-errors"); // **<-- Added**
       

        // Initialize the WebDriver with Chrome options
        driver = new ChromeDriver(options);

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Quit the WebDriver instance
         if (driver != null) {
            driver.quit();
        }
        
    }
}
