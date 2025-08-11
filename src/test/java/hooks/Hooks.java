package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.ExtentManager;

public class Hooks {

    // Ensure WebDriver exists before any step runs
    @Before(order = -100)
    public void setUp() {
        DriverFactory.initDriver();
    }

    // Keep your teardown & screenshot on failure
    @After(order = 100)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] png = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", scenario.getName());

                var test = ExtentManager.getTest();
                if (test != null) {
                    String b64 = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
                    test.addScreenCaptureFromBase64String(b64, "Failure screenshot");
                }
            }
        } catch (Exception ignored) {}
        DriverFactory.quitDriver();
    }
}
