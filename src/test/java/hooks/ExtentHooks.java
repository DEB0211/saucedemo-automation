package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utils.ExtentManager;

import java.util.stream.Collectors;

public class ExtentHooks {

    @Before(order = -50) // runs after Hooks.setUp(), before steps
    public void startExtent(Scenario scenario) {
        ExtentReports extent = ExtentManager.getExtent();

        String tags = scenario.getSourceTagNames().stream()
                .map(t -> t.replace("@", ""))
                .collect(Collectors.joining(", "));
        String title = (tags.isEmpty() ? "" : "[" + tags + "] ") + scenario.getName();

        ExtentTest test = extent.createTest(title);
        scenario.getSourceTagNames().forEach(t -> test.assignCategory(t.replace("@", "")));

        ExtentManager.setTest(test);
    }

    @After(order = 90)
    public void flushExtent(Scenario scenario) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            if (scenario.isFailed()) test.fail("Scenario failed: " + scenario.getName());
            else test.pass("Scenario passed: " + scenario.getName());
        }
        ExtentManager.getExtent().flush();
        ExtentManager.clearTest();
    }
}
