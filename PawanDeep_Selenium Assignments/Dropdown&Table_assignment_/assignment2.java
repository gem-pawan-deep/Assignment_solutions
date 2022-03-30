/*
Assignment 2 -
1. Download the "Assignment.html" file attached in the mail.
2. Launch the file.
2. Read the table and find the unique rows from the table.
 */
package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
public class assignment2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///C://Users//pa.deep//Downloads//Assignment.html");
         int row=driver.findElements(By.xpath("/html/body/table/tbody//tr")).size();
         int col=driver.findElements(By.xpath("/html/body/table/tbody//th")).size();
        System.out.println(row+" "+col);
         List<String>no=new ArrayList<>();
         List<String>name=new ArrayList<>();
         List<String>age=new ArrayList<>();
         for(int i=2;i<=row;i++)
         {
             for(int j=1;j<=col;j++)
             {
                 if(!no.contains(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+1+"]")).getText())||!name.contains(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(2)+"]")).getText()) || !age.contains(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(3)+"]")).getText())){
                     no.add(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+1+"]")).getText());
                 }
                 if(!name.contains(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(2)+"]")).getText())){
                     name.add(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(2)+"]")).getText());
                 }
                 if(!age.contains(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(3)+"]")).getText())){
                     age.add(driver.findElement(By.xpath("//table/tbody/tr["+(i)+"]/td["+(3)+"]")).getText());
             }
             }
         }
        System.out.println(driver.findElement(By.xpath("/html/body/table/tbody//th[1]")).getText()+" "+driver.findElement(By.xpath("/html/body/table/tbody//th[2]")).getText()+"   "+driver.findElement(By.xpath("/html/body/table/tbody//th[3]")).getText());
         for(int i=0;i<no.size();i++)
         {
             System.out.println(no.get(i)+" "+name.get(i)+" "+age.get(i));
         }
        driver.quit();
    }
}
