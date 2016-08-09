package com.stockmaster;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class stockacc
{
	//Global Variables
		public static String strUrl="http://webapp.qedgetech.com";
		public static String expval,actval;
		public WebDriver driver;
		public String strUsername="admin",strPassword="master";
		
		/*Domain Name:ERP
		Project Name :Stock Accounting
		Function Name: stockAcc_Launch()
		Arguments      : url
		Description    : It will launch Stock Accounting Application
		Return Type    : String
		Author         :xxxxxxxxxxx
		Creation Date  :19/7/2016*/
		
		
		public String stockAcc_Launch(String strUrl)
		{
			expval="Login";
			driver=new FirefoxDriver();
			driver.get(strUrl);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			actval=driver.findElement(By.id("btnsubmit")).getText();
			if (expval.equalsIgnoreCase(actval)) 
			{
				return "Pass";
				
			}
			else
			{
				return "Fail";
			}
			
		}
		
		/*Domain Name:ERP
		Project Name :Stock Accounting
		Function Name: stockAcc_Login()
		Arguments      : strUsername,strPassword
		Description    : It will login  Stock Accounting Application
		Return Type    : String
		Author         :xxxxxxxxxxx
		Creation Date  :19/7/2016*/
		
		public String stockAcc_Login(String strUsername, String strPassword)

		{
			expval="administrator";
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(strUsername);
			
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(strPassword);
			
			driver.findElement(By.id("btnsubmit")).click();
			
			actval=driver.findElement(By.xpath(".//*[@id='msUserName']/font/strong")).getText();
			if (expval.equalsIgnoreCase(actval))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
	}


