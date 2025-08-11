package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void initDriver() {
        if (TL.get() == null) {
            ChromeOptions options = new ChromeOptions();
            // Uncomment if you run headless in CI:
            // options.addArguments("--headless=new");
            options.addArguments("--start-maximized");
            TL.set(new ChromeDriver(options));
        }
    }

    public static WebDriver getDriver() {
        return TL.get();
    }

    public static void quitDriver() {
        WebDriver d = TL.get();
        if (d != null) {
            try { d.quit(); } catch (Exception ignored) {}
            TL.remove();
        }
    }
}
