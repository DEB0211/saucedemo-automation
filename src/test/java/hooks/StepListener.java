package hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.LoggerUtil;

public class StepListener {

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Optional: You can log before step here
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        ExtentTest test = LoggerUtil.threadLocalTest.get();
        if (scenario.isFailed()) {
            String stepName = scenario.getName();
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            test.fail("Step Failed")
                    .addScreenCaptureFromBase64String(
                            ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64),
                            stepName
                    );
        } else {
            test.log(Status.PASS, "Step passed");
        }
    }
}