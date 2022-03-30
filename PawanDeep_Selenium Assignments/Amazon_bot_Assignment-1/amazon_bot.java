package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class amazon_bot {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("books");
        driver.findElement(By.id("nav-search-submit-button")).click();
        driver.findElement(By.xpath("//img[@class='s-image']")).click();
    }
}
