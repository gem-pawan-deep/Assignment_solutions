/*
1. Open - https://www.techlistic.com/p/demo-selenium-practice.html
2. Print the values of the table column-wise. (Google-Meta-Microsoft and so on).
3. Print the values of the table row-wise. (Google - Maria Anders - Germany)
4. Verify that Roland Mendel works in Microsoft and lives in Austria.
 */

package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Assignmentt {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //1. Open - https://www.techlistic.com/p/demo-selenium-practice.html
        driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int row=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr")).size();
        int col=driver.findElements(By.xpath("//table[@id='customers']//tbody//th")).size();

       // 2. Print the values of the table column-wise. (Google-Meta-Microsoft and so on)
        List<String> l1=new ArrayList<>();
        System.out.println("************COLUMN WISE PRINTED START*******************");
        for(int j=1;j<=col;j++)
        {
            for(int i=2;i<=row;i++)
            {
                l1.add(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr[" + i + "]//td["+ j +"]")).getText());
            }
        }
        for (int i=0;i<l1.size();i++)
        {
            System.out.println(l1.get(i));
        }
        System.out.println("****************COLUMN WISE PRINTED END*************");

       // 3. Print the values of the table row-wise. (Google - Maria Anders - Germany)
        System.out.println("*****************ROW WISE PRINTED START*************");
        List<String>l2=new ArrayList<>();
        for(int i=2;i<=row;i++)
        {
            for(int j=1;j<=col;j++)
            {
                l2.add(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr[" + i + "]//td["+ j +"]")).getText());
            }
        }
        for (int i=0;i<l2.size();i++)
        {
            System.out.println(l2.get(i));
        }
        System.out.println("*******************COLUMN WISE PRINTED END**************");

        //4. Verify that Roland Mendel works in Microsoft and lives in Austria.
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='customers']")));
        for(int i=2;i<=row;i++) {
            for(int j=1;j<=col;j++) {
                if ("Microsoft".equals(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr[" + i + "]//td[" + j + "]")).getText())) {
                    j++;
                    if ("Roland Mendel".equals(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr[" + i + "]//td[" + j + "]")).getText())) {
                        j++;
                        if ("Austria".equals(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr[" + i + "]//td[" + j + "]")).getText())) {
                            System.out.println("******************Verified successfully!!******************\n********** Roland Mendel Works in Microsoft and Lives in Austria*********");
                            break;
                        }
                    }
                }
            }
        }
        driver.quit();
    }
}
