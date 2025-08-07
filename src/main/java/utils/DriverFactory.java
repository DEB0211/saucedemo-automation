package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications", "--incognito");
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false
            ));

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
