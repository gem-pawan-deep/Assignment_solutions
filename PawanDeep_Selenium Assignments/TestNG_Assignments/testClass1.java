import org.testng.annotations.Test;
public class testClass1 {
  // @Test (priority = 1)
    @Test(groups = {"unit"})
  void testcase1()
   {
       System.out.println("Test_case:1");
   }
  // @Test
   @Test (groups = {"reg","unit"})
   void testcase2()
   {
       
       System.out.println("Test_case:2");
   }
  // @Test (priority = 2)
    @Test(groups = {"reg"})
   void testcase3()
   {
       System.out.println("Test_case:3");
   }
}
