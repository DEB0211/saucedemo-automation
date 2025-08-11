package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Products page
    private final By productsHeader = By.cssSelector(".title"); // "Products"
    private final By firstAddToCartBtn = By.cssSelector("button.btn_inventory");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    // Cart page
    private final By checkoutButton = By.id("checkout");

    // Menu/logout
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    // Login page (post-logout)
    private final By loginButton = By.id("login-button");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnProductsPage() {
        boolean urlOk = wait.until(ExpectedConditions.urlContains("inventory.html"));
        boolean headerOk = wait.until(ExpectedConditions.textToBePresentInElementLocated(productsHeader, "Products"));
        return urlOk && headerOk;
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartBtn)).click();
    }

    public boolean isProductAddedToCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public boolean isOnLoginPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}
