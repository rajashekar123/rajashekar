package com.stockmaster;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Testngmaster 
{
	public static String url="http://webapp.qedgetech.com";
public static WebDriver driver;
public FileInputStream fi;
public Properties pr;
public Actions act;
public static String expval,actval;
public stockaccmaster sm;
public static String uname="admin",pwd="master";
public static String supname="Reddiess Lab1234",add="Ameerpet";
public static String city="Hyderabad",cntry="INDIA",cperson="Rakesh";
public static String pno="04022558877",email="Rakesh@redddieslab.com";
public static String mno="9842888877",notes="This will deliver tablets";
public static String catname="QEdge1234";
public static String Uid="cotton445678",Udesc="12 Items";
@BeforeSuite
public void stockacc_Launch() throws IOException
{
	fi=new FileInputStream("C:\\Stockacc\\raja\\erp\\src\\com\\stockproperties\\stock.properties");
pr=new Properties();
pr.load(fi);
expval="btnsubmit";
String br=pr.getProperty("browser");

if (br.equalsIgnoreCase("firefox")) 
{
	driver=new FirefoxDriver();
}
else if (br.equalsIgnoreCase("chrome"))
{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajashekar.g\\Downloads\\chromedriver.exe");
	driver=new ChromeDriver();
}
else if (br.equalsIgnoreCase("ie")) 
{
	System.setProperty("webdriver.ie.driver", "C:\\Users\\rajashekar.g\\Downloads\\IEDriverServer.exe");
	driver=new InternetExplorerDriver();
}
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	actval=driver.findElement(By.id("btnsubmit")).getAttribute("id");
	org.testng.Assert.assertEquals(actval, expval,"launch has failed");
	
}
@BeforeTest
public void stockAcc_Login()
{
	expval="Administrator";
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(uname);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(pwd);
	driver.findElement(By.id("btnsubmit")).click();
	actval=driver.findElement(By.xpath(".//*[@id='msUserName']/font/strong")).getText();
	org.testng.Assert.assertEquals(actval, expval,"login has failed");
}
@AfterTest
public void stockacc_logout() 
{
	expval="Login";
	driver.findElement(By.id("logout")).click();
	Sleeper.sleepTight(1000);
	driver.findElement(By.xpath("html/body/div[.]/div[2]/div/div[4]/div[2]/button[1]")).click();
	List<WebElement> button=driver.findElements(By.tagName("button"));
	for (int i = 0; i < button.size(); i++) 
	{
		String bText=button.get(i).getText();
		if (bText.equalsIgnoreCase("ok!"))
		{
			button.get(i).click();
			break;
		}
		
	}
	actval=driver.findElement(By.id("btnsubmit")).getText();
	org.testng.Assert.assertEquals(actval, expval,"lougout has failed");

}
@AfterSuite
public void stockAcc_Close()
{
	driver.close();
}
}
