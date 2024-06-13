package utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookiesUtility 
{
	public String getCookiesCount(WebDriver driver) // 1 Get count of cookies
	{
		int c = driver.manage().getCookies().size();
		return "Count of cookies found : "+c;
	}
	
	public List<String> getCookiesList(WebDriver driver) // 2 Get detailed list of cookies
	{
		Set<Cookie> sc = driver.manage().getCookies();
		List<Cookie> cl = new ArrayList<Cookie>(sc);
		List<String> cdl = new ArrayList<String>();
		for(Cookie c:cl)
		{
			cdl.add("Name: "+c.getName()+", Value: "+c.getValue()+", Domain: "+c.getDomain()+", Expiry: "+c.getExpiry());
		}
		return cdl;
	}

	public List<String> getCookiesTypes(WebDriver driver, String samedomain, String superdomain) // 3 Get type of cookies, if no superdomain then add null
	{
		Set<Cookie> sc = driver.manage().getCookies();
		List<Cookie> cl = new ArrayList<Cookie>(sc);
		List<String> ctl = new ArrayList<String>();
		for(Cookie c:cl)
		{
			String res = c.getName()+" is ";
			if(c.isHttpOnly())
			{
				res =  res+"HTTP-Only,";
			}
			if(c.isSecure())
			{
				res = res+"Secured,";
			}
			if(c.getExpiry()!=null)
			{
				res = res+"Persistant,";
			}
			else 
			{
				res = res+"Session";
			}
			if(c.getDomain().contains(samedomain))
			{
				res = res+"samedomain,";
			}
			else if (superdomain != null && c.getDomain().contains(superdomain))
			{
				res = res+"superdomain";
			}
			else
			{
				res = res+"third-party cookie and it came from "+c.getDomain();
			}
			ctl.add(res); // adding all details of each cookie (c of cl) one by one in loop
		}
		return ctl; // returning whole cookie types list
	}
	
	public void addNewCookie(WebDriver driver, String name, String value, String domain, String path, Date expiry, boolean isSecure, boolean isHttpOnly, String sameSite)
	// 4 Adding cookie to browser
	{
		Cookie c = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly, sameSite);
		driver.manage().addCookie(c);
	}
	
	public void addNewCookieViaBuilder(WebDriver driver, String name, String value, String domain, String path, Date expiry, boolean isSecure, boolean isHttpOnly, String sameSite)
	// 5 Adding cookie to browser
	{
		Cookie.Builder b = new Cookie.Builder(name, value);
		Cookie c = b.domain(domain).path(path).expiresOn(expiry).isSecure(isSecure).isHttpOnly(isHttpOnly).sameSite(sameSite).build();
		driver.manage().addCookie(c);
	}
	
	public void deleteCookies(WebDriver driver) // 6  to delete a cookie
	{
		driver.manage().deleteAllCookies();
	}

	public String getCookieByName(WebDriver driver, String s) 
	{
		Set<Cookie> sc = driver.manage().getCookies();
		List<Cookie> cl = new ArrayList<Cookie>(sc);
		String nameDetails = null;
		for(Cookie c:cl)
		{
			if(c.getName().equals(s))
			{
				nameDetails =  "Name: "+c.getName()+", Value: "+c.getValue()+", Domain: "+c.getDomain()+", Expiry: "+c.getExpiry();
			}
		}
		return nameDetails;
		
	}

	public String deleteCookieByIndex(WebDriver driver, int i) 
 {
		    Set<Cookie> cookies = driver.manage().getCookies();
		    List<Cookie> cookieList = new ArrayList<>(cookies);
		    if (i >= 0 && i < cookieList.size())
		    {
		        Cookie cookieToDelete = cookieList.get(i);
		        driver.manage().deleteCookie(cookieToDelete);
		    }
			return "Cookie deleted from index "+i;
		

 }

	public String deleteCookieByName(WebDriver driver, String string) 
	{
		driver.manage().deleteCookieNamed(string);
		return "Cookie deleted with name "+string;
	}

	
}
