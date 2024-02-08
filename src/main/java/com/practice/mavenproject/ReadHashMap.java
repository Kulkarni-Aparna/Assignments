package com.practice.mavenproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import jxl.read.biff.BiffException;

public class ReadHashMap {
public static void main(String args[]) throws BiffException, IOException {
		HashMap<String, String> xlsData = Stocks.stocksInXLS();
		HashMap<String, String> webData = Stocks.stocksInWEB();

		Set<String> keyList = xlsData.keySet();
		for (String key : keyList) {
			System.out.println(key);
			if (xlsData.get(key).equals(webData.get(key)))
				System.out.println("Stock price is same for " + key);
			else
				System.out.println("Stock price is different for " + key);
		}
	}
}
