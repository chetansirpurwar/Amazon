package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.FluentWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class WebSiteUtility 
{

	public WebDriver openBrowser(String bn)
    {
		WebDriver driver = null;
		if(bn.equalsIgnoreCase("chrome"))
		{
			driver =  new ChromeDriver();
		}
		else if(bn.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(bn.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Check browser name");
			System.exit(0);
		}
		return driver;

	}
	
	public FluentWait<WebDriver> defineExplicitWait(WebDriver driver,int timeoutsec,int intervalmillis)
    {
    	FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
    	wait.withTimeout(Duration.ofSeconds(timeoutsec));
    	wait.pollingEvery(Duration.ofMillis(intervalmillis)); 
		return(wait);
    }

	public void launchSite(WebDriver driver, String url) 
	{
		driver.get(url);
	}
	// To get page screenshot
	public String capturePageScreenshotAsFile(WebDriver driver) throws Exception
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss-SSS");
		Date date = new Date();
		File dest = new File("target/"+simpleFormat.format(date)+".png");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot,dest);
		return(dest.getAbsolutePath());
	}
	// To get element screenshot
	public String captureElementScreenshotAsFile(WebElement e) throws Exception
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss-SSS");
		Date date = new Date();
		File dest = new File("target/"+simpleFormat.format(date)+".png");
		File screenshot = e.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot,dest);
		return(dest.getAbsolutePath());
	}
	// To get full page scrolling screenshot
	public String captureFullPageScrollingScreenshotAsFile(WebDriver driver) throws Exception
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss-SSS");
		Date date = new Date();
		File dest = new File("target/"+simpleFormat.format(date)+".png");
		AShot as = new AShot();
		ShootingStrategy shs = ShootingStrategies.viewportPasting(1000); // 1 sec delay 
		Screenshot ss = as.shootingStrategy(shs).takeScreenshot(driver); 
		ImageIO.write(ss.getImage(),"PNG", dest);
		return(dest.getAbsolutePath());
	}
	public void closeSite(WebDriver driver)
	{
		driver.quit();
	}
}
