package hooks;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import utils.DriverFactory;
import utils.ExtentManager;

public class ExtentHooks {

    @Before(order = 0)
    public void startExtent(Scenario scenario) {
        DriverFactory.initDriver(); // ensure driver exists before first step
        String name = scenario.getName();
        ExtentTest test = ExtentManager.getExtent().createTest(name);
        scenario.getSourceTagNames().forEach(t -> test.assignCategory(t.replace("@", "")));
        ExtentManager.setTest(test);
    }

    @After(order = 100)
    public void flushExtent() {
        // don’t crash the run if something is null
        try { ExtentManager.getExtent().flush(); } catch (Exception ignored) {}
        ExtentManager.clearTest();
    }
}
