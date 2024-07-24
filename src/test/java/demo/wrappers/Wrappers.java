package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    //  public static boolean wrapperNavigate(ChromeDriver driver, String url){
    
    public static boolean wrapperNavigate(ChromeDriver driver, String url) {
        try {
            if (driver.getCurrentUrl().equals(url)) {
                return true;
            } else {
                driver.get(url);
                return driver.getCurrentUrl().equals(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public static boolean wrapperAdvancedClick(WebElement button) throws InterruptedException{
		try{
			button.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

    public static boolean wrapperEnterText(WebElement textBox, String inputText) throws InterruptedException {
		try {
			textBox.clear();
			textBox.sendKeys(inputText);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

    public static boolean wrapperAdvancedScroll(ChromeDriver driver, WebElement element) throws InterruptedException {
		try {
			 	JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView();", element);
		        return true;
		}catch(Exception e) {
			return false;
		}
	}

    public static boolean wrapperAdvancedScrollWithOutElement(ChromeDriver driver, int yaxis) throws InterruptedException{
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            /* to scroll down to specific pixel */
		    js.executeScript("window.scrollBy(0,"+yaxis+")");
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static String extractGenre(String input) {

        String[] parts = input.split("•");
        return parts[0].trim();
    }
}