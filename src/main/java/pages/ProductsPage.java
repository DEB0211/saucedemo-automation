package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private final WebDriver driver;
    private final By inventoryItem = By.cssSelector(".inventory_item");
    private final By firstAddToCart = By.cssSelector(".inventory_item:first-of-type button.btn_inventory");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By checkoutBtn = By.id("checkout"); // on cart page

    public ProductsPage(WebDriver driver) { this.driver = driver; }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public void addFirstProductToCart() {
        driver.findElement(firstAddToCart).click();
    }

    public void navigateToCart() {
        driver.findElement(cartIcon).click();
    }

    public void clickCheckout() {
        // assumes you're on the cart page after navigateToCart()
        driver.findElement(checkoutBtn).click();
    }

    public boolean isProductAddedToCart() {
        // cart badge exists when >= 1 item
        return !driver.findElements(By.cssSelector(".shopping_cart_badge")).isEmpty();
    }
}
