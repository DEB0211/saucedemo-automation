package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestUtils {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getExtentTest() {
        return test;
    }

    public static void setExtentTest(String name) {
        test = extent.createTest(name);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
