package cucumberOptions;



import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith (Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\features",glue="StepsDefinition")

public class CreateAccountTestRunner extends AbstractTestNGCucumberTests {

}
