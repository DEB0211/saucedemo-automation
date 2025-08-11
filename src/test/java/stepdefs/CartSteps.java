package stepdefs;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.ProductsPage;
import utils.DriverFactory;

public class CartSteps {

    ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());

    @And("user adds the first product to cart")
    public void user_adds_the_first_product_to_cart() {
        productsPage.addFirstProductToCart();
        Assert.assertTrue(productsPage.isProductAddedToCart(), "Product was not added to cart");
    }

    @And("user navigates to cart")
    public void user_navigates_to_cart() {
        productsPage.navigateToCart();
    }
}
