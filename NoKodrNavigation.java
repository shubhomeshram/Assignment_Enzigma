package Enzigma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NoKodrNavigation {

    public static void main(String[] args) throws InterruptedException {
    	//open the berowser
    	WebDriver driver=new ChromeDriver();
        //maximize the browser
    	driver.manage().window().maximize();
    	//waiting condition
    	Thread.sleep(2000);
    	//ento into enzigma web page
        driver.get("https://app-staging.nokodr.com/");
        Thread.sleep(2000);
        //close the browser
        driver.quit();
    }
}