package hooks;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utils.ExtentManager;

public class StepListener {

    @AfterStep
    public void afterStep(Scenario scenario) {
        ExtentTest test = ExtentManager.getTest();
        if (test == null) return; // prevents NPE if @Before didn’t run

        switch (scenario.getStatus()) {
            case PASSED -> test.info("Step passed");
            case FAILED -> test.fail("Step failed");
            default -> test.info("Step status: " + scenario.getStatus());
        }
    }
}
