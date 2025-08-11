package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstName = By.id("first-name");
    private final By lastName  = By.id("last-name");
    private final By postal    = By.id("postal-code");
    private final By contBtn   = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By complete  = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) { this.driver = driver; }

    public void enterCheckoutDetails(String f, String l, String z) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postal).sendKeys(z);
        driver.findElement(contBtn).click();
    }

    public void finishCheckout() {
        driver.findElement(finishBtn).click();
    }

    public boolean isCheckoutComplete() {
        return !driver.findElements(complete).isEmpty();
    }
}
