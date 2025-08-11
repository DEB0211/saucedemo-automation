package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<com.aventstack.extentreports.ExtentTest> TL_TEST = new ThreadLocal<>();

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent/ExtentReport_" + ts + ".html");
            spark.config().setDocumentTitle("SauceDemo Automation Report");
            spark.config().setReportName("UI Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        }
        return extent;
    }

    public static void setTest(com.aventstack.extentreports.ExtentTest test) { TL_TEST.set(test); }
    public static com.aventstack.extentreports.ExtentTest getTest() { return TL_TEST.get(); }
    public static void clearTest() { TL_TEST.remove(); }
}
