package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    private By title = By.className("title");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnProductsPage() {
        return driver.findElement(title).getText().equalsIgnoreCase("Products");
    }

    public void logout() {
        driver.findElement(menuButton).click();
        try {
            Thread.sleep(1000); // To wait for menu animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(logoutLink).click();
    }

    public boolean isLogoutSuccessful() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }
}
