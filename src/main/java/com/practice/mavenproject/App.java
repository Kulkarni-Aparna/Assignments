package com.practice.mavenproject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");  
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
         
        List<WebElement> links = driver.findElements(By.tagName("a"));
         
//        foreach loop
        for(WebElement link: links) {
            System.out.println(link.getAttribute("href"));
        }
        
//        stream
//        links.stream().forEach(i->{System.out.println(i.getAttribute("href")+"\n");});
         
        
//        parallel stream
//        links.parallelStream().forEach(i->{System.out.println(i.getAttribute("href")+"\n");});     
        
        driver.quit();
    }
}