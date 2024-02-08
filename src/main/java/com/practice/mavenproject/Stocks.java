package com.practice.mavenproject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import jxl.read.biff.BiffException;

public class Stocks {
	public static HashMap<String, String> getStockPriceFromXLS;
	public static HashMap<String, String> getStockPriceFromWeb;

	public static HashMap<String, String> stocksInXLS() throws IOException, BiffException {
		FileInputStream fileInputStream = new FileInputStream("E:\\Books\\JavaPractice\\Stocks.xls");
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);

		HashMap<String, String> map = new HashMap<String, String>();
		for (int r = 0; r < sheet.getLastRowNum(); r++) {
			String key = sheet.getRow(r).getCell(0).getStringCellValue();
			String value = sheet.getRow(r).getCell(1).getStringCellValue();

			System.out.print("Key: " + key+" ");
			System.out.print("Value: " + value+"\n");
			map.put(key, value);
			System.out.println();
		}
		return map;
	}
	
	public static HashMap<String, String> stocksInWEB() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupall");
		driver.manage().window().maximize();
		
		List<WebElement> currentPrices = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
		List<WebElement> companies = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
		
		HashMap<String, String> sharePrices = new HashMap<String, String>();
		for (int i = 0; i < currentPrices.size(); i++) {
			sharePrices.put(companies.get(i).getText().trim(), currentPrices.get(i).getText().trim());
		}
		driver.quit();
		return sharePrices;
	}
}
