package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utils.RetryListener;
import utils.ConfigReader;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs", "hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
@Listeners(RetryListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        boolean parallel = Boolean.parseBoolean(ConfigReader.getProperty("parallelExecution"));
        System.setProperty("cucumber.execution.parallel.enabled", String.valueOf(parallel));
        return super.scenarios();
    }
}
