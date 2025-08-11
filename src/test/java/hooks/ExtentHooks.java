package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utils.ExtentManager;

import java.util.stream.Collectors;

public class ExtentHooks {

    @Before(order = 0)
    public void startExtent(Scenario scenario) {
        // Ensure singleton exists
        ExtentReports extent = ExtentManager.getExtent();

        // Build a readable test name: "<tags> | <scenario name>"
        String tags = scenario.getSourceTagNames()
                .stream().map(t -> t.replace("@",""))
                .collect(Collectors.joining(", "));
        String title = (tags.isEmpty() ? "" : "[" + tags + "] ") + scenario.getName();

        ExtentTest test = extent.createTest(title);
        // assign tags as categories
        scenario.getSourceTagNames().forEach(t -> test.assignCategory(t.replace("@","")));

        ExtentManager.setTest(test);
    }

    @AfterStep
    public void logAfterStep(Scenario scenario) {
        // Safe-guard: only log if test exists
        ExtentTest test = ExtentManager.getTest();
        if (test == null) return;

        // We don’t have step text here without a plugin; still log progress.
        switch (scenario.getStatus()) {
            case PASSED -> test.info("Step passed");
            case FAILED -> test.fail("Step failed");
            default -> test.info("Step: " + scenario.getStatus());
        }
    }

    @After(order = 100)
    public void flushExtent(Scenario scenario) {
        ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            if (scenario.isFailed()) {
                test.fail("Scenario failed: " + scenario.getName());
            } else {
                test.pass("Scenario passed: " + scenario.getName());
            }
        }
        ExtentManager.getExtent().flush();
        ExtentManager.clearTest();
    }
}
