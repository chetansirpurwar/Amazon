package utilities;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;

public class logsUtility 
{
	public Set<String> getLogTypes(WebDriver driver) // 1 To get the log types
	{
		return driver.manage().logs().getAvailableLogTypes();
	}
	
	public LogEntries getBrowserLogs(WebDriver driver,String browserName) // 2 To get the browser logs
	{
		System.out.println("Browser logs: ");
		LogEntries bl = driver.manage().logs().get(browserName);
		return bl;
	}
	
	public LogEntries getBrowserDriverLogs(WebDriver driver, String browserDriver) // 3 To get the browser driver logs
	{
		System.out.println("Browser driver logs: ");
		LogEntries dl = driver.manage().logs().get(browserDriver);
		return dl;
	}
	public LogEntries getclientLogs(WebDriver driver, String client) // 4 To get the client logs
	{
		System.out.println("Client logs: ");
		LogEntries cl = driver.manage().logs().get("client");
		return cl;
	}

}
