/*
Assignment 1 -
1.Launch https://codepen.io/abdulmlik/pen/dJOJov
2.Select date 05-05-2005 from the dropdown and validate the same.
3.Fetch the year from the dropdown and validate the year is in Ascending Order.
*/
package selenium;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class assignment1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();

        //Launch https://codepen.io/abdulmlik/pen/dJOJov
        driver.get("https://codepen.io/abdulmlik/pen/dJOJov");

        //Select date 05-05-2005 drom the dropdown and validate the same.
        driver.switchTo().frame("result");
        Select drop_year = new Select(driver.findElement(By.xpath("//*[@id='year']")));
        drop_year.selectByValue("2005");
        Select drop_month = new Select(driver.findElement(By.xpath("//*[@id='month']")));
        drop_month.selectByValue("5");
        Select drop_day = new Select(driver.findElement(By.xpath("//*[@id='day']")));
        drop_day.selectByValue("5");
        if(drop_year.getFirstSelectedOption().getText().equals("2005"))
        {
            if(drop_month.getFirstSelectedOption().getText().equals("5"))
            {
                if(drop_day.getFirstSelectedOption().getText().equals("5"))
                {
                    System.out.println("Validation Successful");
                }
            }
        }
        System.out.print(drop_day.getFirstSelectedOption().getText()+" ");
        System.out.print(drop_month.getFirstSelectedOption().getText()+" ");
        System.out.print(drop_year.getFirstSelectedOption().getText()+"\n");

        //Fetch the year from the dropdown and validate the year is in Ascending Order.
        List original = new ArrayList();
        List<WebElement>year_list=drop_year.getOptions();
        for(WebElement ele : year_list)
        {
            original.add(ele.getText());
        }
        Collections.sort(original);
        for(int i=0;i<original.size();i++)
        {
            System.out.print(original.get(i)+" ");
        }
        List temp= new ArrayList();
        temp.addAll(original);
        Assert.isTrue(temp.equals(original),"Not validated");
        driver.quit();
    }
}
