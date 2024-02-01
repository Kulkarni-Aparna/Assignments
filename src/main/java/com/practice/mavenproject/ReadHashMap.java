package com.practice.mavenproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import jxl.read.biff.BiffException;

public class ReadHashMap {
	public void test() throws BiffException, IOException {
		HashMap<String, String> xlsData = Stocks.stocksInXLS();
		HashMap<String, String> webData = Stocks.stocksInWEB();
		String stockPriceInXLS = xlsData.get("City Crops Agro");
		String stockPriceInWEB = webData.get("City Crops Agro");

		System.out.println("Current Stock Price In XLS: " + stockPriceInXLS);
		System.out.println("Current Stock Price In WEB: " + stockPriceInWEB);

		if (stockPriceInXLS.equals(stockPriceInWEB)) {
			System.out.println("Stock price is equal");
		} else {
			System.out.println("Stock price is NOT equal");
		}

		Set<String> keyList = xlsData.keySet();
		for (String key : keyList) {
			System.out.println(key);
			if (xlsData.get(key).equals(webData.get(key)))
				System.out.println("Pass for the " + key);

			else
				System.out.println("Fail for the " + key);

		}
	}
}
