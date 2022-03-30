/*\
Assignment
1. Launch https://chercher.tech/practice/practice-pop-ups-selenium-webdriver
2. Click on Alert and accept it
3. Click on confirmation box and get the text and cancel it
4. Click on prompt and enter text and accept it
5. Upload any sample file
6. Double click on Double click me and get the text from alert
7. Move mouse to Sub menu and click on it
8. Press tab key and select google and click on it

 */

package selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
public class Assignment {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //1. Launch https://chercher.tech/practice/practice-pop-ups-selenium-webdriver
        driver.get("https://chercher.tech/practice/practice-pop-ups-selenium-webdriver");

        //2.Click on Alert and accept it.
        driver.findElement(By.xpath("//input[@name='alert']")).click();
        Thread.sleep(1000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        //3. Click on confirmation box and get the text and cancel it.
        driver.findElement(By.xpath("//input[@name='confirmation']")).click();
        Thread.sleep(1000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();

        //4. Click on prompt and enter text and accept it.
        driver.findElement(By.xpath("//input[@name='prompt']")).click();
        //This sendKeys() method is not working here. as disabled by website.
        driver.switchTo().alert().sendKeys("Pawan");
        Thread.sleep(1000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        //5. Upload any sample file
        driver.findElement(By.xpath("//input[@name='upload']")).sendKeys("C:\\Users\\pa.deep\\Desktop\\Question.docx");
        Thread.sleep(1000);

        //6. Double click on Double click me and get the text from alert
        WebElement link=driver.findElement(By.xpath("//input[@id='double-click']"));
        Actions builder = new Actions(driver);
        Action double_click = builder.moveToElement(link).doubleClick().build();
        double_click.perform();
        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(1000);
        driver.switchTo().alert().accept();

        //7. Move mouse to Sub menu and click on it
        WebElement submenu = driver.findElement(By.xpath("//button[@id='sub-menu']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(submenu).click();
        actions.perform();
        Thread.sleep(1000);

        //8. Press tab key and select google and click on it
          //logic-1
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER);
        actions.perform();
        Thread.sleep(1000);

        //logic-2
       /*
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).click(driver.findElement(By.xpath("//a[@id='link2']")));
        actions.perform();
        Thread.sleep(1000);
        */

        driver.quit();
    }
}
