import org.testng.annotations.Test;
public class testClass2 {
   // @Test(priority = 1)
    @Test(groups = {"reg"})
    void testcase4()
    {
        System.out.println("Test_case:4");
    }
    //@Test
    @Test(groups ={"unit"})
    void testcase5()
    {
        System.out.println("Test_case:5");
    }
    //@Test(priority = 0)
    @Test(groups = {"unit","reg"})
    void testcase6()
    {
        System.out.println("Test_case:6");
    }
}
