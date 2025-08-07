package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.LoggerUtil;

public class Hooks {

    WebDriver driver;
    static ExtentReports extent;
    static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.initDriver();

        if (extent == null) {
            extent = ExtentReportManager.createInstance();
        }

        String scenarioName = scenario.getName();
        String tags = scenario.getSourceTagNames().toString();
        ExtentTest test = extent.createTest(scenarioName + " " + tags);
        test.info("Scenario Started: " + scenarioName);
        scenarioThread.set(test);
        LoggerUtil.threadLocalTest.set(test);
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = scenarioThread.get();

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            String base64Screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);

            // Attach screenshot to Cucumber HTML report
            scenario.attach(screenshot, "image/png", "Failed Screenshot");

            // Attach to Extent Report
            test.fail("Scenario failed: " + scenario.getName(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, "Failed Screenshot").build()
            );
        } else {
            test.pass("Scenario passed");
        }

        DriverFactory.quitDriver();

        if (extent != null) {
            extent.flush();
        }
    }
}