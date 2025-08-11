package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent/index.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest getTest() { return TEST.get(); }
    public static void setTest(ExtentTest t) { TEST.set(t); }
    public static void clearTest() { TEST.remove(); }
}
