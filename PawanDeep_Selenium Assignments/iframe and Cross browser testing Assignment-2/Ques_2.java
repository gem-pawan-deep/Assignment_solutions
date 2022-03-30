/*
QUESTION B. - Code
1.Navigate to website: https://techtuts.in/
2.Display total number of iFrames available on page.
3.Switch to all the iFrames on page one by one using loop.
4.Click on the iFrame whose name = “google_esf”.
5.Click on the iFrame whose index = n-1, where n is the total number of iFrames on page.
 */
package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Ques_2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://techtuts.in/");
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println(size);
        for(int i=0;i<size;i++)
        {
            try
            {
                driver.switchTo().frame(i);
                driver.switchTo().parentFrame();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        driver.switchTo().defaultContent();
        try {
            driver.switchTo().frame("google_esf");
            driver.findElement(By.xpath("//iframe[@name='google_esf']")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            driver.switchTo().frame(size-1);
            driver.findElement(By.xpath("/html/body/img")).click();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
