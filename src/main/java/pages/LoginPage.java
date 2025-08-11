package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public void login(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(error).isEmpty();
    }
}
