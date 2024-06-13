package utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class BrowserWindowUtility 
{
	public void maximizeBrowser(WebDriver driver) //1
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeBrowser(WebDriver driver) //2
	{
		driver.manage().window().minimize();
	}
	
	public void fullscreenBrowser(WebDriver driver) //3
	{
		driver.manage().window().fullscreen();
	}
	
	public void GetBrowserSize(WebDriver driver) //4
	{
		int a = driver.manage().window().getSize().getWidth();
		int b = driver.manage().window().getSize().getHeight();
		System.out.println("Height: "+a+", Width: "+b);
	}
	
	public void setBrowserSize(WebDriver driver, int a, int b) //5
	{
		Dimension c = new Dimension(a,b);
		driver.manage().window().setSize(c);
	}
	
	public void setBrowserPosition(WebDriver driver, int a, int b) //6
	{
		Point p = new Point(a,b);
		driver.manage().window().setPosition(p);
	}
	
	
	
}
