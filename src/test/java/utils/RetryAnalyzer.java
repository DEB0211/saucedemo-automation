package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 5;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            LoggerUtil.logInfo("Retrying test: " + result.getName() + " | Retry attempt: " + retryCount);
            return true;
        } else {
            LoggerUtil.logInfo("Test failed after " + maxRetryCount + " retries: " + result.getName());
            return false;
        }
    }
}

