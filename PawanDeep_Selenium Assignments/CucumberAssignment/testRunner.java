package TestRunnerFile;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/Login.feature",glue="stepDefinations",monochrome = true,tags = "@type1 and @type2")
public class testRunner {
}
