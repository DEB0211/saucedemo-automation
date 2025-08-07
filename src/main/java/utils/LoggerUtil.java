package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoggerUtil {
    public static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    public static void logInfo(String message) {
        ExtentTest test = threadLocalTest.get();
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }
}