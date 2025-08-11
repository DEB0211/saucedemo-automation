package hooks;

import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import utils.ExtentManager;

public class StepListener {

    @AfterStep
    public void afterStep(Scenario scenario) {
        var test = ExtentManager.getTest();
        if (test == null) return; // prevent NPEs

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Step failed");
        } else {
            test.log(Status.PASS, "Step passed");
        }
    }
}
