package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class AddToCartSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());

    @Given("user is logged in with valid credentials")
    public void user_is_logged_in_with_valid_credentials() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @When("user adds the first product to cart")
    public void user_adds_the_first_product_to_cart() {
        productsPage.addFirstProductToCart();
        Assert.assertTrue(productsPage.isProductAddedToCart());
    }

    @Then("user should see the product in the cart")
    public void user_should_see_the_product_in_the_cart() {
        productsPage.navigateToCart();
        // Optional: Add assertions here for cart page UI
    }
}
