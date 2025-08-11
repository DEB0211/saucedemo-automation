package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import utils.RetryListener;

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
        // NOTE: DataProvider's 'parallel' argument is compile-time.
        // We still read a flag so you can switch to parallel=true later if desired.
        boolean parallel = Boolean.parseBoolean(ConfigReader.getProperty("parallelExecution"));
        System.setProperty("cucumber.execution.parallel.enabled", String.valueOf(parallel));
        return super.scenarios();
    }
}
