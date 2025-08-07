package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        if (tlDriver.get() == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications", "--incognito");
                options.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));
                WebDriver driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                tlDriver.set(driver);
            }
            // You can add Firefox, Edge support here later
            else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return tlDriver.get();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
