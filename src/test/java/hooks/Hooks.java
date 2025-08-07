package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.LoggerUtil;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks {

    WebDriver driver;
    static ExtentReports extent;
    static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.initDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Wait until DOM is fully loaded
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete")
        );

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
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            test.fail("Scenario failed").addScreenCaptureFromBase64String(
                    ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64),
                    scenario.getName()
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