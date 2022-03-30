import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Final_Project {
    static Thread Goa = new Thread(){
        public void  run(){
            try {
                get_GOA_data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    static Thread Mp = new Thread(){
        public void  run(){
            try {
                get_MP_data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    static Thread Pb = new Thread(){
        public void  run(){
            try {
                get_PB_data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    static Thread Up = new Thread(){
        public void  run(){
            try {
                get_UP_data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    static Thread Uk = new Thread(){
        public void  run(){
            try {
                get_UK_data();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public static void main(String[] args) throws IOException {
        create_Excel_file("Election_data");
        create_Excel_Sheet("C:\\Users\\pa.deep\\IdeaProjects\\excel_practice\\src\\main\\Election_data.xlsx");
        Goa.start();
        Mp.start();
        Pb.start();
        Up.start();
        Uk.start();
    }
    public static void get_GOA_data() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\IdeaProjects\\Selenium_Mega_Assignment\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://results.eci.gov.in/ResultAcGenMar2022/ConstituencywiseS0510.htm?ac=10");
        Select drpState = new Select(driver.findElement(By.xpath("//*[@id='ddlState']")));
        drpState.selectByVisibleText("Goa");
        System.out.println("State : GOA ");
        Select drpcons = new Select(driver.findElement(By.xpath("//*[@id='ddlAC']")));
        List<WebElement> no_of_cons = drpcons.getOptions();
        int size_of_cons = no_of_cons.size() - 1;
        List<String> Winner = new ArrayList<>();
        List<Integer> Winner_vote = new ArrayList<>();
        List<Integer> Runner_up_votes = new ArrayList<>();
        List<Double> Winner_vote_per = new ArrayList<>();
        List<Double> Runner_up_vote_per = new ArrayList<>();
        List<String> Winner_constituency_name = new ArrayList<>();
        List<Integer> List1 = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> List2 = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Integer> nota_list = new ArrayList<>();
        List<Integer> can_50 = new ArrayList<>();
        System.out.println("NO of Constituency : " + size_of_cons);
        for (int cons = 1; cons <= size_of_cons; cons++) {
            Select drpAc = new Select(driver.findElement(By.xpath("//select[@id='ddlAC']")));
            drpAc.selectByIndex(cons);
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='div1']//tr"));
            int row_size = rows.size() - 1;
            int max_vote = Integer.MIN_VALUE;
            String candidate_name = "";
            String constituency_name = "";
            double per = 0;
            List<Integer> Votes = new ArrayList<>();
            List<Double> Votes_per = new ArrayList<>();
            int nota_vote = 0;
            int candidate_count = 0;
            for (int cand = 4; cand <= row_size - 1; cand++) {
                Votes.add(Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText()));
                Votes_per.add(Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText()));
                int curr_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[6]")).getText());
                double curr_per = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[7]")).getText());
                if (curr_vote > max_vote) {
                    max_vote = curr_vote;
                    candidate_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[2]")).getText();
                    constituency_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[1]/td")).getText();
                    per = curr_per;
                }
                int nota = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tbody/tr[" + (row_size - 1) + "]/td[6]")).getText());
                int c_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText());
                if (c_vote < nota) {
                    nota_vote++;
                }
                double cc_vote = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText());
                if (cc_vote > 50.00) {
                    candidate_count++;
                }
            }
            Winner_vote.add(max_vote);
            Winner_vote_per.add(per);
            Runner_up_vote_per.add(per);
            Runner_up_votes.add(max_vote);
            Winner.add(candidate_name);
            Winner_constituency_name.add(constituency_name);
            nota_list.add(nota_vote);
            can_50.add(candidate_count);
            System.out.println(cons + " : name :" + candidate_name + " - " + max_vote + " - " + constituency_name + " Total candidate get less than nota vote " + nota_vote);
            int obj = Collections.max(Votes);
            int row = Votes.indexOf(obj) + 4;
            Collections.sort(Votes);
            int obj2 = Votes.get(Votes.size() - 2);
            List1.add(obj - obj2);
            String names = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + row + "]/td[2]")).getText());
            name.add(names);

            double a = Collections.max(Votes_per);
            int b = Votes_per.indexOf(a) + 4;
            Collections.sort(Votes_per);
            double c = Votes_per.get(Votes_per.size() - 2);
            List2.add(a - c);
            String names1 = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + b + "]/td[2]")).getText());
            name2.add(names1);

        }
        System.out.println("Maxximum votes in Goa get by:-");
        Max_vote_candidate("GOA", Winner_vote, Winner, Winner_constituency_name);
        System.out.println("Maxximum percentage votes in Goa get by:-");
        Max_per_vote("GOA", Winner_vote_per, Winner, Winner_constituency_name);
        if (List1.size() == size_of_cons) {
            Max_vote_diff("GOA", List1, Winner_constituency_name, name);
        }
        if (List2.size() == size_of_cons) {
            Max_vote_per_diff("GOA", List2, Winner_constituency_name, name2);
        }
        Min_vote_diff("GOA", List1, Winner_constituency_name, name);
        Min_vote_per_diff("GOA", List2, Winner_constituency_name, name);
        int a = candidate_less_than_nota(nota_list);
        int b = candidates_get_greater_than_50per(can_50);
        System.out.println("Total number of candidate got less than nota votes in state : " + a);
        System.out.println("Total number of candidate got more than 50% votes in state : " + b);
        write_Into_excel_misc("Election_data", "GOA", "Details", a, b);
        candidate_got_minimum_vote("GOA",Winner_vote, Winner, Winner_constituency_name);
        driver.quit();
    }

    public static void get_MP_data() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\IdeaProjects\\Selenium_Mega_Assignment\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://results.eci.gov.in/ResultAcGenMar2022/ConstituencywiseS0510.htm?ac=10");
        Select drpState = new Select(driver.findElement(By.xpath("//*[@id='ddlState']")));
        drpState.selectByVisibleText("Manipur");
        System.out.println("State : Manipur ");
        Select drpcons = new Select(driver.findElement(By.xpath("//*[@id='ddlAC']")));
        List<WebElement> no_of_cons = drpcons.getOptions();
        int size_of_cons = no_of_cons.size() - 1;
        List<String> Winner = new ArrayList<>();
        List<Integer> Winner_vote = new ArrayList<>();
        List<Integer> Runner_up_votes = new ArrayList<>();
        List<Double> Winner_vote_per = new ArrayList<>();
        List<Double> Runner_up_vote_per = new ArrayList<>();
        List<String> Winner_constituency_name = new ArrayList<>();
        List<Integer> List1 = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> List2 = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Integer> nota_list = new ArrayList<>();
        List<Integer> can_50 = new ArrayList<>();
        System.out.println("NO of Constituency : " + size_of_cons);
        for (int cons = 1; cons <= size_of_cons; cons++) {
            Select drpAc = new Select(driver.findElement(By.xpath("//select[@id='ddlAC']")));
            drpAc.selectByIndex(cons);
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='div1']//tr"));
            int row_size = rows.size() - 1;
            int max_vote = Integer.MIN_VALUE;
            String candidate_name = "";
            String constituency_name = "";
            double per = 0;
            List<Integer> Votes = new ArrayList<>();
            List<Double> Votes_per = new ArrayList<>();
            int nota_vote = 0;
            int candidate_count = 0;
            for (int cand = 4; cand <= row_size - 1; cand++) {
                Votes.add(Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText()));
                Votes_per.add(Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText()));
                int curr_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[6]")).getText());
                double curr_per = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[7]")).getText());
                if (curr_vote > max_vote) {
                    max_vote = curr_vote;
                    candidate_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[2]")).getText();
                    constituency_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[1]/td")).getText();
                    per = curr_per;
                }
                int nota = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tbody/tr[" + (row_size - 1) + "]/td[6]")).getText());
                int c_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText());
                if (c_vote < nota) {
                    nota_vote++;
                }
                double cc_vote = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText());
                if (cc_vote > 50.00) {
                    candidate_count++;
                }
            }
            Winner_vote.add(max_vote);
            Winner_vote_per.add(per);
            Runner_up_vote_per.add(per);
            Runner_up_votes.add(max_vote);
            Winner.add(candidate_name);
            Winner_constituency_name.add(constituency_name);
            nota_list.add(nota_vote);
            can_50.add(candidate_count);
            System.out.println(cons + " : name :" + candidate_name + " - " + max_vote + " - " + constituency_name + " Total candidate get less than nota vote " + nota_vote);
            int obj = Collections.max(Votes);
            int row = Votes.indexOf(obj) + 4;
            Collections.sort(Votes);
            int obj2 = Votes.get(Votes.size() - 2);
            List1.add(obj - obj2);
            String names = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + row + "]/td[2]")).getText());
            name.add(names);

            double a = Collections.max(Votes_per);
            int b = Votes_per.indexOf(a) + 4;
            Collections.sort(Votes_per);
            double c = Votes_per.get(Votes_per.size() - 2);
            List2.add(a - c);
            String names1 = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + b + "]/td[2]")).getText());
            name2.add(names1);

        }
        System.out.println("Maxximum votes in Manipur get by:-");
        Max_vote_candidate("Manipur", Winner_vote, Winner, Winner_constituency_name);
        System.out.println("Maxximum percentage votes in Manipur get by:-");
        Max_per_vote("Manipur", Winner_vote_per, Winner, Winner_constituency_name);
        if (List1.size() == size_of_cons) {
            Max_vote_diff("Manipur", List1, Winner_constituency_name, name);
        }
        if (List2.size() == size_of_cons) {
            Max_vote_per_diff("Manipur", List2, Winner_constituency_name, name2);
        }
        Min_vote_diff("Manipur", List1, Winner_constituency_name, name);
        Min_vote_per_diff("Manipur", List2, Winner_constituency_name, name);
        int a = candidate_less_than_nota(nota_list);
        int b = candidates_get_greater_than_50per(can_50);
        System.out.println("Total number of candidate got less than nota votes in state : " + a);
        System.out.println("Total number of candidate got more than 50% votes in state : " + b);
        write_Into_excel_misc("Election_data", "Manipur", "Details", a, b);
        candidate_got_minimum_vote("Manipur",Winner_vote, Winner, Winner_constituency_name);
        driver.quit();
    }

    public static void get_PB_data() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\IdeaProjects\\Selenium_Mega_Assignment\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://results.eci.gov.in/ResultAcGenMar2022/ConstituencywiseS0510.htm?ac=10");
        Select drpState = new Select(driver.findElement(By.xpath("//*[@id='ddlState']")));
        drpState.selectByVisibleText("Punjab");
        System.out.println("State : Punjab ");
        Select drpcons = new Select(driver.findElement(By.xpath("//*[@id='ddlAC']")));
        List<WebElement> no_of_cons = drpcons.getOptions();
        int size_of_cons = no_of_cons.size() - 1;
        List<String> Winner = new ArrayList<>();
        List<Integer> Winner_vote = new ArrayList<>();
        List<Integer> Runner_up_votes = new ArrayList<>();
        List<Double> Winner_vote_per = new ArrayList<>();
        List<Double> Runner_up_vote_per = new ArrayList<>();
        List<String> Winner_constituency_name = new ArrayList<>();
        List<Integer> List1 = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> List2 = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Integer> nota_list = new ArrayList<>();
        List<Integer> can_50 = new ArrayList<>();
        System.out.println("NO of Constituency : " + size_of_cons);
        for (int cons = 1; cons <= size_of_cons; cons++) {
            Select drpAc = new Select(driver.findElement(By.xpath("//select[@id='ddlAC']")));
            drpAc.selectByIndex(cons);
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='div1']//tr"));
            int row_size = rows.size() - 1;
            int max_vote = Integer.MIN_VALUE;
            String candidate_name = "";
            String constituency_name = "";
            double per = 0;
            List<Integer> Votes = new ArrayList<>();
            List<Double> Votes_per = new ArrayList<>();
            int nota_vote = 0;
            int candidate_count = 0;
            for (int cand = 4; cand <= row_size - 1; cand++) {
                Votes.add(Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText()));
                Votes_per.add(Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText()));
                int curr_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[6]")).getText());
                double curr_per = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[7]")).getText());
                if (curr_vote > max_vote) {
                    max_vote = curr_vote;
                    candidate_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[2]")).getText();
                    constituency_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[1]/td")).getText();
                    per = curr_per;
                }
                int nota = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tbody/tr[" + (row_size - 1) + "]/td[6]")).getText());
                int c_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText());
                if (c_vote < nota) {
                    nota_vote++;
                }
                double cc_vote = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText());
                if (cc_vote > 50.00) {
                    candidate_count++;
                }
            }
            Winner_vote.add(max_vote);
            Winner_vote_per.add(per);
            Runner_up_vote_per.add(per);
            Runner_up_votes.add(max_vote);
            Winner.add(candidate_name);
            Winner_constituency_name.add(constituency_name);
            nota_list.add(nota_vote);
            can_50.add(candidate_count);
            System.out.println(cons + " : name :" + candidate_name + " - " + max_vote + " - " + constituency_name + " Total candidate get less than nota vote " + nota_vote);
            int obj = Collections.max(Votes);
            int row = Votes.indexOf(obj) + 4;
            Collections.sort(Votes);
            int obj2 = Votes.get(Votes.size() - 2);
            List1.add(obj - obj2);
            String names = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + row + "]/td[2]")).getText());
            name.add(names);

            double a = Collections.max(Votes_per);
            int b = Votes_per.indexOf(a) + 4;
            Collections.sort(Votes_per);
            double c = Votes_per.get(Votes_per.size() - 2);
            List2.add(a - c);
            String names1 = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + b + "]/td[2]")).getText());
            name2.add(names1);

        }
        System.out.println("Maxximum votes in Punjab get by:-");
        Max_vote_candidate("Punjab", Winner_vote, Winner, Winner_constituency_name);
        System.out.println("Maxximum percentage votes in Punjab get by:-");
        Max_per_vote("Punjab", Winner_vote_per, Winner, Winner_constituency_name);
        if (List1.size() == size_of_cons) {
            Max_vote_diff("Punjab", List1, Winner_constituency_name, name);
        }
        if (List2.size() == size_of_cons) {
            Max_vote_per_diff("Punjab", List2, Winner_constituency_name, name2);
        }
        Min_vote_diff("Punjab", List1, Winner_constituency_name, name);
        Min_vote_per_diff("Punjab", List2, Winner_constituency_name, name);
        int a = candidate_less_than_nota(nota_list);
        int b = candidates_get_greater_than_50per(can_50);
        System.out.println("Total number of candidate got less than nota votes in state : " + a);
        System.out.println("Total number of candidate got more than 50% votes in state : " + b);
        write_Into_excel_misc("Election_data", "Punjab", "Details", a, b);
        candidate_got_minimum_vote("Punjab",Winner_vote, Winner, Winner_constituency_name);
        driver.quit();
    }

    public static void get_UP_data() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\IdeaProjects\\Selenium_Mega_Assignment\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://results.eci.gov.in/ResultAcGenMar2022/ConstituencywiseS0510.htm?ac=10");
        Select drpState = new Select(driver.findElement(By.xpath("//*[@id='ddlState']")));
        drpState.selectByVisibleText("Uttar Pradesh");
        System.out.println("State : Uttar Pradesh");
        Select drpcons = new Select(driver.findElement(By.xpath("//*[@id='ddlAC']")));
        List<WebElement> no_of_cons = drpcons.getOptions();
        int size_of_cons = no_of_cons.size() - 1;
        List<String> Winner = new ArrayList<>();
        List<Integer> Winner_vote = new ArrayList<>();
        List<Integer> Runner_up_votes = new ArrayList<>();
        List<Double> Winner_vote_per = new ArrayList<>();
        List<Double> Runner_up_vote_per = new ArrayList<>();
        List<String> Winner_constituency_name = new ArrayList<>();
        List<Integer> List1 = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> List2 = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Integer> nota_list = new ArrayList<>();
        List<Integer> can_50 = new ArrayList<>();
        System.out.println("NO of Constituency : " + size_of_cons);
        for (int cons = 1; cons <= size_of_cons; cons++) {
            Select drpAc = new Select(driver.findElement(By.xpath("//select[@id='ddlAC']")));
            drpAc.selectByIndex(cons);
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='div1']//tr"));
            int row_size = rows.size() - 1;
            int max_vote = Integer.MIN_VALUE;
            String candidate_name = "";
            String constituency_name = "";
            double per = 0;
            List<Integer> Votes = new ArrayList<>();
            List<Double> Votes_per = new ArrayList<>();
            int nota_vote = 0;
            int candidate_count = 0;
            for (int cand = 4; cand <= row_size - 1; cand++) {
                Votes.add(Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText()));
                Votes_per.add(Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText()));
                int curr_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[6]")).getText());
                double curr_per = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[7]")).getText());
                if (curr_vote > max_vote) {
                    max_vote = curr_vote;
                    candidate_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[2]")).getText();
                    constituency_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[1]/td")).getText();
                    per = curr_per;
                }
                int nota = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tbody/tr[" + (row_size - 1) + "]/td[6]")).getText());
                int c_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText());
                if (c_vote < nota) {
                    nota_vote++;
                }
                double cc_vote = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText());
                if (cc_vote > 50.00) {
                    candidate_count++;
                }
            }
            Winner_vote.add(max_vote);
            Winner_vote_per.add(per);
            Runner_up_vote_per.add(per);
            Runner_up_votes.add(max_vote);
            Winner.add(candidate_name);
            Winner_constituency_name.add(constituency_name);
            nota_list.add(nota_vote);
            can_50.add(candidate_count);
            System.out.println(cons + " : name :" + candidate_name + " - " + max_vote + " - " + constituency_name + " Total candidate get less than nota vote " + nota_vote);
            int obj = Collections.max(Votes);
            int row = Votes.indexOf(obj) + 4;
            Collections.sort(Votes);
            int obj2 = Votes.get(Votes.size() - 2);
            List1.add(obj - obj2);
            String names = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + row + "]/td[2]")).getText());
            name.add(names);

            double a = Collections.max(Votes_per);
            int b = Votes_per.indexOf(a) + 4;
            Collections.sort(Votes_per);
            double c = Votes_per.get(Votes_per.size() - 2);
            List2.add(a - c);
            String names1 = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + b + "]/td[2]")).getText());
            name2.add(names1);

        }
        System.out.println("Maxximum votes in Uttar Pradesh get by:-");
        Max_vote_candidate("Uttar Pradesh", Winner_vote, Winner, Winner_constituency_name);
        System.out.println("Maxximum percentage votes in Uttar Pradesh get by:-");
        Max_per_vote("Uttar Pradesh", Winner_vote_per, Winner, Winner_constituency_name);
        if (List1.size() == size_of_cons) {
            Max_vote_diff("Uttar Pradesh", List1, Winner_constituency_name, name);
        }
        if (List2.size() == size_of_cons) {
            Max_vote_per_diff("Uttar Pradesh", List2, Winner_constituency_name, name2);
        }
        Min_vote_diff("Uttar Pradesh", List1, Winner_constituency_name, name);
        Min_vote_per_diff("Uttar Pradesh", List2, Winner_constituency_name, name);
        int a = candidate_less_than_nota(nota_list);
        int b = candidates_get_greater_than_50per(can_50);
        System.out.println("Total number of candidate got less than nota votes in state : " + a);
        System.out.println("Total number of candidate got more than 50% votes in state : " + b);
        write_Into_excel_misc("Election_data", "Uttar Pradesh", "Details", a, b);
        candidate_got_minimum_vote("Uttar Pradesh",Winner_vote, Winner, Winner_constituency_name);
        driver.quit();
    }

    public static void get_UK_data() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pa.deep\\IdeaProjects\\Selenium_Mega_Assignment\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://results.eci.gov.in/ResultAcGenMar2022/ConstituencywiseS0510.htm?ac=10");
        Select drpState = new Select(driver.findElement(By.xpath("//*[@id='ddlState']")));
        drpState.selectByVisibleText("Uttarakhand");
        System.out.println("State : Uttarakhand ");
        Select drpcons = new Select(driver.findElement(By.xpath("//*[@id='ddlAC']")));
        List<WebElement> no_of_cons = drpcons.getOptions();
        int size_of_cons = no_of_cons.size() - 1;
        List<String> Winner = new ArrayList<>();
        List<Integer> Winner_vote = new ArrayList<>();
        List<Integer> Runner_up_votes = new ArrayList<>();
        List<Double> Winner_vote_per = new ArrayList<>();
        List<Double> Runner_up_vote_per = new ArrayList<>();
        List<String> Winner_constituency_name = new ArrayList<>();
        List<Integer> List1 = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> List2 = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Integer> nota_list = new ArrayList<>();
        List<Integer> can_50 = new ArrayList<>();
        System.out.println("NO of Constituency : " + size_of_cons);
        for (int cons = 1; cons <= size_of_cons; cons++) {
            Select drpAc = new Select(driver.findElement(By.xpath("//select[@id='ddlAC']")));
            drpAc.selectByIndex(cons);
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='div1']//tr"));
            int row_size = rows.size() - 1;
            int max_vote = Integer.MIN_VALUE;
            String candidate_name = "";
            String constituency_name = "";
            double per = 0;
            List<Integer> Votes = new ArrayList<>();
            List<Double> Votes_per = new ArrayList<>();
            int nota_vote = 0;
            int candidate_count = 0;
            for (int cand = 4; cand <= row_size - 1; cand++) {
                Votes.add(Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText()));
                Votes_per.add(Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText()));
                int curr_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[6]")).getText());
                double curr_per = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']//tr[" + cand + "]/td[7]")).getText());
                if (curr_vote > max_vote) {
                    max_vote = curr_vote;
                    candidate_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[2]")).getText();
                    constituency_name = driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[1]/td")).getText();
                    per = curr_per;
                }
                int nota = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']//tbody/tr[" + (row_size - 1) + "]/td[6]")).getText());
                int c_vote = Integer.parseInt(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[6]")).getText());
                if (c_vote < nota) {
                    nota_vote++;
                }
                double cc_vote = Double.parseDouble(driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + cand + "]/td[7]")).getText());
                if (cc_vote > 50.00) {
                    candidate_count++;
                }
            }
            Winner_vote.add(max_vote);
            Winner_vote_per.add(per);
            Runner_up_vote_per.add(per);
            Runner_up_votes.add(max_vote);
            Winner.add(candidate_name);
            Winner_constituency_name.add(constituency_name);
            nota_list.add(nota_vote);
            can_50.add(candidate_count);
            System.out.println(cons + " : name :" + candidate_name + " - " + max_vote + " - " + constituency_name + " Total candidate get less than nota vote " + nota_vote);
            int obj = Collections.max(Votes);
            int row = Votes.indexOf(obj) + 4;
            Collections.sort(Votes);
            int obj2 = Votes.get(Votes.size() - 2);
            List1.add(obj - obj2);
            String names = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + row + "]/td[2]")).getText());
            name.add(names);

            double a = Collections.max(Votes_per);
            int b = Votes_per.indexOf(a) + 4;
            Collections.sort(Votes_per);
            double c = Votes_per.get(Votes_per.size() - 2);
            List2.add(a - c);
            String names1 = (driver.findElement(By.xpath("//*[@id='div1']/table[1]/tbody/tr[" + b + "]/td[2]")).getText());
            name2.add(names1);

        }
        System.out.println("Maxximum votes in Uttarakhand get by:-");
        Max_vote_candidate("Uttarakhand", Winner_vote, Winner, Winner_constituency_name);
        System.out.println("Maxximum percentage votes in Uttarakhand get by:-");
        Max_per_vote("Uttarakhand", Winner_vote_per, Winner, Winner_constituency_name);
        if (List1.size() == size_of_cons) {
            Max_vote_diff("Uttarakhand", List1, Winner_constituency_name, name);
        }
        if (List2.size() == size_of_cons) {
            Max_vote_per_diff("Uttarakhand", List2, Winner_constituency_name, name2);
        }
        Min_vote_diff("Uttarakhand", List1, Winner_constituency_name, name);
        Min_vote_per_diff("Uttarakhand", List2, Winner_constituency_name, name);
        int a = candidate_less_than_nota(nota_list);
        int b = candidates_get_greater_than_50per(can_50);
        System.out.println("Total number of candidate got less than nota votes in state : " + a);
        System.out.println("Total number of candidate got more than 50% votes in state : " + b);
        write_Into_excel_misc("Election_data", "Uttarakhand", "Details", a, b);
        candidate_got_minimum_vote("Uttarakhand",Winner_vote, Winner, Winner_constituency_name);
        driver.quit();
    }


    //1. get the candidate which has got the maximum vote in each state with their constituency name.
    public static void Max_vote_candidate(String state, List<Integer> Winner_vote, List<String> Winner, List<String> Winner_constituency_name) {
        int max_vote = Collections.max(Winner_vote);
        int index = Winner_vote.indexOf(max_vote);
        System.out.println("Name:" + Winner.get(index) + " votes " + Winner_vote.get(index) + " constituency " + Winner_constituency_name.get(index));
        write_Into_excel("Election_data", state, "Maximum_vote", Winner_constituency_name.get(index), Winner.get(index), Winner_vote.get(index).toString());
    }

    //2. get the candidate which has got the maximum percentage of vote in each state with their constituency name.(percentage)
    public static void Max_per_vote(String state, List<Double> Winner_vote_per, List<String> Winner, List<String> Winner_constituency_name) {
        double max_vote = Collections.max(Winner_vote_per);
        int index = Winner_vote_per.indexOf(max_vote);
        System.out.println("Name:" + Winner.get(index) + " got " + Winner_vote_per.get(index) + "% votes" + " constituency " + Winner_constituency_name.get(index));
        write_Into_excel("Election_data", state, "Maximum_per_vote", Winner_constituency_name.get(index), Winner.get(index), Winner_vote_per.get(index).toString());
    }

    //3. candidate who won with maximum vote difference.
    public static void Max_vote_diff(String state, List<Integer> List1, List<String> Winner_constituency_name, List<String> name) {
        int maxindex = List1.indexOf(Collections.max(List1));
        System.out.println("The candidate who won with maximum vote difference is :  " + name.get(maxindex) + "  with vote difference of : " + Collections.max(List1) + "  in constituency :" + Winner_constituency_name.get(maxindex));
        write_Into_excel_type_2("Election_data", state, "maximum_vote_difference", Winner_constituency_name.get(maxindex), name.get(maxindex), List1.get(maxindex).toString());
    }

    //4. candidate who won with maximum vote percentage difference.
    public static void Max_vote_per_diff(String state, List<Double> List2, List<String> Winner_constituency_name, List<String> name2) {
        int maxindex = List2.indexOf(Collections.max(List2));
        System.out.println("The candidate who won with maximum vote percentage difference is :  " + name2.get(maxindex) + "  with vote difference of : " + Collections.max(List2) + "  in constituency :" + Winner_constituency_name.get(maxindex));
        write_Into_excel_type_2("Election_data", state, "maximum_vote_per_difference", Winner_constituency_name.get(maxindex), name2.get(maxindex), List2.get(maxindex).toString());
    }

    //5. candidate who won with minimum vote difference.
    public static void Min_vote_diff(String state, List<Integer> List1, List<String> Winner_constituency_name, List<String> name3) {
        int maxindex = List1.indexOf(Collections.min(List1));
        System.out.println("The candidate who won with minimum vote difference is :  " + name3.get(maxindex) + "  with vote difference of : " + Collections.min(List1) + "  in constituency :" + Winner_constituency_name.get(maxindex));
        write_Into_excel_type_2("Election_data", state, "minimum_vote_difference", Winner_constituency_name.get(maxindex), name3.get(maxindex), List1.get(maxindex).toString());
    }

    // 6. candidate who won with minimum vote percentage.
    public static void Min_vote_per_diff(String state, List<Double> List2, List<String> Winner_constituency_name, List<String> name4) {
        int maxindex = List2.indexOf(Collections.min(List2));
        System.out.println("The candidate who won with minimum vote percentage difference is :  " + name4.get(maxindex) + "  with vote difference of : " + Collections.min(List2) + "  in constituency :" + Winner_constituency_name.get(maxindex));
        write_Into_excel_type_2("Election_data", state, "minimum_vote_per_difference", Winner_constituency_name.get(maxindex), name4.get(maxindex), List2.get(maxindex).toString());
    }

    //7. total count of candidate who have got less vote than nota.
    public static int candidate_less_than_nota(List<Integer> nota_list) {
        int sum = 0;
        for (int i = 0; i < nota_list.size(); i++) {
            sum = sum + nota_list.get(i);
        }
        return sum;
    }

    //8. total count of candidates who have got greater than 50% vote.
    public static int candidates_get_greater_than_50per(List<Integer> can_50) {
        int sum = 0;
        for (int i = 0; i < can_50.size(); i++) {
            sum += can_50.get(i);
        }
        return sum;
    }

    //9. name of candidate who have got minimum vote in each state.
    public static void candidate_got_minimum_vote(String state,List<Integer> Winner_vote, List<String> Winner, List<String> Winner_constituency) {
        int a = Collections.min(Winner_vote);
        int b = Winner_vote.indexOf(a);
        System.out.println("Candidate got Minimum Vote and won in the State is :\n Name: " + Winner.get(b) + " votes: " + Winner_vote.get(b) + " from : " + Winner_constituency.get(b));
        write_Into_excel_type_2("Election_data", state, "minimum_vote_winner", Winner_constituency.get(b), Winner.get(b), Winner_vote.get(b).toString());
    }

    //Dump Into Excel...
    public static void create_Excel_file(String fileName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\pa.deep\\IdeaProjects\\excel_practice\\src\\main\\" + fileName + ".xlsx"));
        workbook.write(out);
        out.close();
        System.out.println(fileName + ".xlsx written successfully");
    }

    public static void create_Excel_Sheet(String file_Path) throws IOException {
        String excelFilePath = file_Path;
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            //sheet1
            Sheet sheet1 = workbook.createSheet("Maximum_vote");
            sheet1.setColumnWidth(0, 6000);
            sheet1.setColumnWidth(1, 4000);
            Row header1 = sheet1.createRow(0);
            CellStyle headerStyle1 = workbook.createCellStyle();
            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setBold(true);
            headerStyle1.setFont(font);
            Cell headerCell1 = header1.createCell(0);
            headerCell1.setCellValue("State Name");
            headerCell1 = header1.createCell(1);
            headerCell1.setCellValue("Constituency Name");
            headerCell1 = header1.createCell(2);
            headerCell1.setCellValue("Candidate Name");
            headerCell1 = header1.createCell(3);
            headerCell1.setCellValue("Total Votes");
            //sheet2
            Sheet sheet2 = workbook.createSheet("Maximum_per_vote");
            sheet2.setColumnWidth(0, 6000);
            sheet2.setColumnWidth(1, 4000);
            Row header2 = sheet2.createRow(0);
            CellStyle headerStyle2 = workbook.createCellStyle();
            headerStyle2.setFont(font);
            Cell headerCell2 = header2.createCell(0);
            headerCell2.setCellValue("State Name");
            headerCell2 = header2.createCell(1);
            headerCell2.setCellValue("Constituency Name");
            headerCell2 = header2.createCell(2);
            headerCell2.setCellValue("Candidate Name");
            headerCell2 = header2.createCell(3);
            headerCell2.setCellValue("Total Votes Percentage");
            //sheet3
            Sheet sheet3 = workbook.createSheet("maximum_vote_difference");
            sheet3.setColumnWidth(0, 6000);
            sheet3.setColumnWidth(1, 4000);
            Row header3 = sheet3.createRow(0);
            CellStyle headerStyle3 = workbook.createCellStyle();
            headerStyle3.setFont(font);
            Cell headerCell3 = header3.createCell(0);
            headerCell3.setCellValue("State Name");
            headerCell3 = header3.createCell(1);
            headerCell3.setCellValue("Constituency Name");
            headerCell3 = header3.createCell(2);
            headerCell3.setCellValue("Candidate Name");
            headerCell3 = header3.createCell(3);
            headerCell3.setCellValue("Total Difference");
            //sheet4
            Sheet sheet4 = workbook.createSheet("maximum_vote_per_difference");
            sheet4.setColumnWidth(0, 6000);
            sheet4.setColumnWidth(1, 4000);
            Row header4 = sheet4.createRow(0);
            CellStyle headerStyle4 = workbook.createCellStyle();
            headerStyle4.setFont(font);
            Cell headerCell4 = header4.createCell(0);
            headerCell4.setCellValue("State Name");
            headerCell4 = header4.createCell(1);
            headerCell4.setCellValue("Constituency Name");
            headerCell4 = header4.createCell(2);
            headerCell4.setCellValue("Candidate Name");
            headerCell4 = header4.createCell(3);
            headerCell4.setCellValue("Total Percentage Difference");
            //sheet5
            Sheet sheet5 = workbook.createSheet("minimum_vote_difference");
            sheet5.setColumnWidth(0, 6000);
            sheet5.setColumnWidth(1, 4000);
            Row header5 = sheet5.createRow(0);
            CellStyle headerStyle5 = workbook.createCellStyle();
            headerStyle5.setFont(font);
            Cell headerCell5 = header5.createCell(0);
            headerCell5.setCellValue("State Name");
            headerCell5 = header5.createCell(1);
            headerCell5.setCellValue("Constituency Name");
            headerCell5 = header5.createCell(2);
            headerCell5.setCellValue("Candidate Name");
            headerCell5 = header5.createCell(3);
            headerCell5.setCellValue("Total Difference");
            //sheet6
            Sheet sheet6 = workbook.createSheet("minimum_vote_per_difference");
            sheet6.setColumnWidth(0, 6000);
            sheet6.setColumnWidth(1, 4000);
            Row header6 = sheet6.createRow(0);
            CellStyle headerStyle6 = workbook.createCellStyle();
            headerStyle6.setFont(font);
            Cell headerCell6 = header6.createCell(0);
            headerCell6.setCellValue("State Name");
            headerCell6 = header6.createCell(1);
            headerCell6.setCellValue("Constituency Name");
            headerCell6 = header6.createCell(2);
            headerCell6.setCellValue("Candidate Name");
            headerCell6 = header6.createCell(3);
            headerCell6.setCellValue("Total Percentage Difference");
            //sheet7
            Sheet sheet7 = workbook.createSheet("minimum_vote_winner");
            sheet7.setColumnWidth(0, 6000);
            sheet7.setColumnWidth(1, 4000);
            Row header7 = sheet7.createRow(0);
            CellStyle headerStyle7 = workbook.createCellStyle();
            headerStyle7.setFont(font);
            Cell headerCell7 = header7.createCell(0);
            headerCell7.setCellValue("State Name");
            headerCell7 = header7.createCell(1);
            headerCell7.setCellValue("Constituency Name");
            headerCell7 = header7.createCell(2);
            headerCell7.setCellValue("Candidate Name");
            headerCell7 = header7.createCell(3);
            headerCell7.setCellValue("Total Votes");
            //sheet8
            Sheet sheet8 = workbook.createSheet("Details");
            sheet8.setColumnWidth(0, 6000);
            sheet8.setColumnWidth(1, 4000);
            Row header8 = sheet8.createRow(0);
            CellStyle headerStyle8 = workbook.createCellStyle();
            headerStyle8.setFont(font);
            Cell headerCell8 = header8.createCell(0);
            headerCell8.setCellValue("State Name");
            headerCell8 = header8.createCell(1);
            headerCell8.setCellValue("Candidate Less Than NOTA");
            headerCell8 = header8.createCell(2);
            headerCell8.setCellValue("Candidate Greater than 50% Votes");


            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }

    }

    public static void write_Into_excel(String fileName, String StateName, String SheetName, String ConstName, String CandName, String Vote) {


        String excelFilePath ="C:\\Users\\pa.deep\\IdeaProjects\\excel_practice\\src\\main\\"+ fileName+".xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(SheetName);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(StateName);
            cell = row.createCell(1);
            cell.setCellValue(ConstName);
            cell = row.createCell(2);
            cell.setCellValue(CandName);
            cell = row.createCell(3);
            cell.setCellValue(Vote);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

    public static void write_Into_excel_type_2(String fileName, String StateName, String SheetName, String ConstName, String WinnerName, String margin) {
        String excelFilePath ="C:\\Users\\pa.deep\\IdeaProjects\\excel_practice\\src\\main\\"+ fileName+".xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(SheetName);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(StateName);
            cell = row.createCell(1);
            cell.setCellValue(ConstName);
            cell = row.createCell(2);
            cell.setCellValue(WinnerName);
            cell = row.createCell(3);
            cell.setCellValue(margin);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

    public static void write_Into_excel_misc(String fileName, String StateName, String SheetName, int less_than_NOTA, int greater_Than_50_percentage) {
        String excelFilePath ="C:\\Users\\pa.deep\\IdeaProjects\\excel_practice\\src\\main\\"+ fileName+".xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(SheetName);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(StateName);
            cell = row.createCell(1);
            cell.setCellValue(less_than_NOTA);
            cell = row.createCell(2);
            cell.setCellValue(greater_Than_50_percentage);

            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }
}