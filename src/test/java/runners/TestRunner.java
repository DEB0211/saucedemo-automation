package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import utils.RetryListener;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs", "hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
        // tags = "@smoke"                               // Optional tag filtering
)
@Listeners(RetryListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {
}
