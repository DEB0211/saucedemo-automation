package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Step One: Your Information
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    // Step Two: Overview
    private final By overviewAnySummaryValue = By.cssSelector(".summary_value_label");
    private final By finishBtn = By.id("finish");

    // Complete
    private final By checkoutCompleteHeader = By.className("complete-header");
    private final By backHomeBtn = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCheckoutDetails(String firstName, String lastName, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(zip);
        driver.findElement(continueBtn).click();
    }

    public boolean isOnOverviewPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(overviewAnySummaryValue)).isDisplayed();
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isCheckoutComplete() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompleteHeader)).isDisplayed();
    }

    public void backToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeBtn)).click();
    }
}
