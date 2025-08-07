package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inventoryContainer = By.id("inventory_container");
    private final By firstAddToCartButton = By.cssSelector(".inventory_item button");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOnProductsPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton)).click();
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
    }

    public boolean isProductAddedToCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
    }

    public void logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
    }
}