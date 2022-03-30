/*
QUESTION A. - Code
1.Launch website: www.geminisolutions.com on different browsers - chrome, firefox, internet explorer.
2.Validate if website has launched successfully on all browsers.
3.Compare times taken on different browsers for the above activity and display in ascending order.
4.Validate titles displayed on differet browsers are correct.
*/
package selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class Ques_1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pa.deep\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver","C:\\Users\\pa.deep\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver","C:\\Users\\pa.deep\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        //chrome browser.
        long start1 = System.currentTimeMillis();
        WebDriver cr = new ChromeDriver();
        cr.get("https://www.geminisolutions.com/");
        long end1 = System.currentTimeMillis();
        double cr_time=(end1-start1)/1000F;

        //firefox browser.
        long start2 = System.currentTimeMillis();
        WebDriver ff= new FirefoxDriver();
        ff.get("https://www.geminisolutions.com/");
        long end2 = System.currentTimeMillis();
        double ff_time=(end2-start2)/1000F;

        //microsoft edge.
        long start3 = System.currentTimeMillis();
        WebDriver ee = new EdgeDriver();
        ee.get("https://www.geminisolutions.com/");
        long end3 = System.currentTimeMillis();
        double ee_time=(end3-start3)/1000F;

        System.out.println(cr.getCurrentUrl());
        System.out.println(ff.getCurrentUrl());
        System.out.println(ee.getCurrentUrl());
        if(cr.getCurrentUrl().equals(ff.getCurrentUrl())&& ff.getCurrentUrl().equals(ee.getCurrentUrl()))
        {
            System.out.println("********Website launched successfully********");
        }

        System.out.println("Time taken by the browsers:-");
        System.out.println("Chrome taken in Seconds : "+cr_time);
        System.out.println("Firefox taken in Seconds : "+ff_time);
        System.out.println("Edge taken in Seconds : "+ee_time);

        System.out.println(cr.getTitle());
        System.out.println(ff.getTitle());
        System.out.println(ee.getTitle());
        if(cr.getTitle().equals(ff.getTitle()) && ff.getTitle().equals(ee.getTitle()))
        {
            System.out.println("*********Tittles validated successfully************");
        }
    }
}

