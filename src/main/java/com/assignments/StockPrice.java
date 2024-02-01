package com.assignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
 
public class  StockPrice 
{
	public static HashMap<String,String> getStockPriceFromXLS;
	public static HashMap<String,String> getStockPriceFromWeb;
 
	public static void main(String args[]) throws BiffException, IOException
	{
		 
		getStockPriceFromXLS = readXLSFile("E:\\Books\\JavaPractice\\Stocks.xls");
		getStockPriceFromWeb = readDatafromWEB();		  
		String expected = getStockPriceFromXLS.get("City Crops Agro");
		String actual   = getStockPriceFromWeb.get("City Crops Agro");
		
		System.out.println("ACTUAL PRICE: "+actual);
		System.out.println("EXPECTED PRICE: "+expected);
		
		if(actual.equals(expected))
		{
			System.out.println("Stock price is equal");
		}
		else
		{
			System.out.println("Stock price is NOT equal");
		}
 
		Set<String> keyList = getStockPriceFromXLS.keySet();
		for(String key : keyList)
		{
			System.out.println(key);
			if(getStockPriceFromXLS.get(key).equals(getStockPriceFromWeb.get(key)))
				System.out.println("Pass for the " + key);
 
			else
				System.out.println("Fail for the " + key);
 
		}
	}
	
	public static HashMap<String,String> readDatafromWEB()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupall");
		driver.manage().window().maximize();
		List<WebElement> currentPrice = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
		List<WebElement> company = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
		System.out.println("Size of the List ::" +currentPrice.size() );
		HashMap<String,String> sharePrices = new HashMap<String,String>();
		for(int i = 0 ;i <7;i++)
		{
			System.out.println("Key ::" +company.get(i).getText().trim()  +"Value ::"+currentPrice.get(i).getText().trim() );
			sharePrices.put(company.get(i).getText().trim(), currentPrice.get(i).getText().trim());
		}
		driver.quit();
		return sharePrices;
 
	}
	public static HashMap<String,String> readXLSFile(String fileName) throws IOException, BiffException
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		HashMap<String,String> sharePrices = new HashMap<String,String>();
		for(int i = 0;i<rows;i=i+1) 
               { 
                      	Cell cell = sheet.getCell(0,i);
                      	String key = cell.getContents();
 
                      	cell  = sheet.getCell(1,i);
                      	String value= cell.getContents();
 
                      	System.out.print("Key  " + key);
                      	System.out.print("Value " + value);
                      	sharePrices.put(key,value);
                      	System.out.println();
               }
		return sharePrices;
	}
	
 
}