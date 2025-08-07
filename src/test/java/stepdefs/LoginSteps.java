package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);

    @Given("user is on the login page")
    public void userIsOnLoginPage() {
        driver.get(ConfigReader.getProperty("url"));
    }

    @When("user logs in with valid credentials")
    public void userLogsInValid() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    }

    @When("user logs in with invalid credentials")
    public void userLogsInInvalid() {
        loginPage.login("locked_out_user", ConfigReader.getProperty("password"));
    }

    @Then("user should be navigated to the products page")
    public void verifySuccessfulLogin() {
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @Then("user should be back on the login page")
    public void verifyLogoutSuccess() {
        Assert.assertTrue(loginPage.isOnLoginPage());
    }

    @Then("user should see an error message")
    public void userSeesErrorMessage() {
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }

    @When("user logs out")
    public void userLogsOut() {
        productsPage.logout();
    }
}