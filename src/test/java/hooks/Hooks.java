package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] shot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(shot, "image/png", scenario.getName());
                // You can also attach to Extent if desired:
                var test = utils.ExtentManager.getTest();
                if (test != null) {
                    test.addScreenCaptureFromBase64String(
                            ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64),
                            "Failure screenshot"
                    );
                }
            }
        } catch (Exception ignored) {}
        DriverFactory.quitDriver();
    }
}
