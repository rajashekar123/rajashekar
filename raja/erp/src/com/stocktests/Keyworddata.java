package com.stocktests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.stockmaster.stockaccmaster;

public class Keyworddata 
{
	 @Test
     public void driver() throws IOException
    {
	stockaccmaster sam=new stockaccmaster();
	String res=null;
	String xlpath="C:\\Stockacc\\raja\\erp\\src\\com\\stocktestdata\\keyword.xlsx";
	String xlout="C:\\Stockacc\\raja\\erp\\src\\com\\stockresults\\results.xlsx";
	FileInputStream fi=new FileInputStream(xlpath);
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet ws=wb.getSheet("TestCase");
	XSSFSheet ws1=wb.getSheet("TestSteps");
	int tcRc=ws.getLastRowNum();
	int tsRc=ws1.getLastRowNum();
	
	for (int i = 1; i <= tcRc; i++)
	{
		ws.getRow(i).createCell(3);
		String exec=ws.getRow(i).getCell(2).getStringCellValue();
		if (exec.equalsIgnoreCase("Y"))
		{
			String tcId=ws.getRow(i).getCell(0).getStringCellValue();
			for (int j = 1; j <= tsRc; j++) 
			{
				String tstcId=ws1.getRow(j).getCell(0).getStringCellValue();
				if (tcId.equalsIgnoreCase(tstcId))
				{
					String key=ws1.getRow(j).getCell(3).getStringCellValue();
					System.out.println(key);
					switch (key) 
					{
					case "sLanch":
						res=sam.stockAcc_Launch("http://webapp.qedgetech.com");
						break;
					case "sLogin":	
						res=sam.stockAcc_Login("admin", "master");
						break;
					case "sSupplier":
						res=sam.stockAcc_SupplierCreation("Sup003", "Ameerpet", "Hyderabad", "INDIA", "Venki", "1234567898", "test@gmail.com", "9878541155", "This is supplier1");
						break;
					case "sLogout":	
						res=sam.stockAcc_Logout();
						sam.stockAcc_Close();
						break;
					case "sCat":
						res=sam.stockAcc_stockcatCreation("cat4000333");
						break;
					case "sUom":
						res=sam.stockAcc_UnitofMcreation("U0005", "This is Unit of meassurement");
						break;
						
					default:
						System.out.println("Select a proper keyword");
						break;
					}
					ws1.getRow(j).createCell(4).setCellValue(res);
					if (!ws.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("Fail"))
					{
						ws.getRow(i).getCell(3).setCellValue(res);
					}
					else
					{
						ws.getRow(i).getCell(3).setCellValue("Fail");
					}
					
				}
			}
			
		}
		else
		{
			ws.getRow(i).createCell(3).setCellValue("BLOCKED");
		}
	}
	FileOutputStream fo=new FileOutputStream(xlout);
	wb.write(fo);
	wb.close();
}

}


