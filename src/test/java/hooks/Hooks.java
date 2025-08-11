package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.ExtentManager;

public class Hooks {

    @After(order = 50)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && DriverFactory.getDriver() != null) {
                byte[] png = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", scenario.getName());

                var test = ExtentManager.getTest();
                if (test != null) {
                    String b64 = ((TakesScreenshot) DriverFactory.getDriver())
                            .getScreenshotAs(OutputType.BASE64);
                    test.addScreenCaptureFromBase64String(b64, "Failure screenshot");
                }
            }
        } catch (Exception ignored) {}
        DriverFactory.quitDriver();
    }
}
