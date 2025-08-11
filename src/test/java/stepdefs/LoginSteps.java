package stepdefs;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    private LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage(DriverFactory.getDriver());
        return loginPage;
    }
    private ProductsPage productsPage() {
        if (productsPage == null) productsPage = new ProductsPage(DriverFactory.getDriver());
        return productsPage;
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
        Assert.assertTrue(loginPage().isLoginPageLoaded(), "Login page did not load");
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage().login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String pass) {
        loginPage().login(user, pass);
    }

    @Then("user should land on Products page")
    public void user_should_land_on_products_page() {
        Assert.assertTrue(productsPage().isAt(), "Not on Products page");
    }

    @Then("login error message should be visible")
    public void login_error_message_should_be_visible() {
        Assert.assertTrue(loginPage().isErrorDisplayed(), "Error message not shown");
    }
}
