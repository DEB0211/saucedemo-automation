package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.WaitUtils;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
        WaitUtils.waitForDomReady(DriverFactory.getDriver());
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }

    @Then("user should land on Products page")
    public void user_should_land_on_products_page() {
        Assert.assertTrue(productsPage.isOnProductsPage(), "Not on Products page after login");
    }

    // Negative login (data-driven)
    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String u, String p) {
        loginPage.login(u, p);
    }

    @Then("login error message should be visible")
    public void login_error_message_should_be_visible() {
        Assert.assertTrue(loginPage.isErrorVisible(), "Expected error message was not visible");
    }
}
