package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        String url = ConfigReader.getOrDefault("baseUrl", "https://www.saucedemo.com/");
        DriverFactory.getDriver().get(url);   // never null now
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String u, String p) {
        loginPage.login(u, p);
    }

    @Then("user should land on Products page")
    public void user_should_land_on_products_page() {
        Assert.assertTrue(productsPage.isAt(), "Products page not loaded");
    }

    @Then("login error message should be visible")
    public void login_error_message_should_be_visible() {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not shown");
    }
}
