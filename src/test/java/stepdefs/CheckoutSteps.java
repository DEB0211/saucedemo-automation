package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class CheckoutSteps {
    ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
    CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

    @And("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        productsPage.clickCheckout();
    }

    @When("user provides valid checkout details")
    public void user_provides_valid_checkout_details() {
        checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
    }

    @And("user finishes the checkout")
    public void user_finishes_the_checkout() {
        checkoutPage.finishCheckout();
    }

    @Then("user should see order confirmation")
    public void user_should_see_order_confirmation() {
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout confirmation not displayed");
    }
}
