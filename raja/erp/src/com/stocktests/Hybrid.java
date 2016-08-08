package com.stocktests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.Test;

import com.stockmaster.stockaccmaster;

public class Hybrid
{

	@Test
	public void driver() throws IOException
	{
		stockaccmaster sam=new stockaccmaster();
		String res=null;
		String xlpath="C:\\Stockacc\\raja\\erp\\src\\com\\stocktestdata\\Hybrid.xlsx";
		String xlout="C:\\Stockacc\\raja\\erp\\src\\com\\stockresults\\hybrid.xlsx";
		FileInputStream fi=new FileInputStream(xlpath);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet("TestCase");
		XSSFSheet ws1=wb.getSheet("TestSteps");
		XSSFSheet uomSheet=wb.getSheet("UOMData");
		int tcRc=ws.getLastRowNum();
		int tsRc=ws1.getLastRowNum();
		int uomRc=uomSheet.getLastRowNum(); 
		for (int i = 1; i <= tcRc; i++)
		{
			String exec=ws.getRow(i).getCell(2).getStringCellValue();
			if (exec.equalsIgnoreCase("Y"))
			{
				ws.getRow(i).createCell(3);
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
							for (int k = 1; k <= uomRc; k++)
							{
								String uId=uomSheet.getRow(k).getCell(0).getStringCellValue();
								String uDesc=uomSheet.getRow(k).getCell(1).getStringCellValue();
								res=sam.stockAcc_UnitofMcreation(uId,uDesc);
								uomSheet.getRow(k).createCell(2).setCellValue(res);
							}
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


