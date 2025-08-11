package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int attempt = 0;
    private final int max;

    public RetryAnalyzer() {
        int configured = 0;
        try { configured = Integer.parseInt(ConfigReader.getProperty("retryCount")); } catch (Exception ignored) {}
        this.max = Math.max(0, configured);
    }

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < max) {
            attempt++;
            System.out.println("[Retry] " + result.getName() + " attempt " + attempt + "/" + max);
            return true;
        }
        return false;
    }
}
