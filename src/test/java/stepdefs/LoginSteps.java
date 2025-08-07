package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.LoggerUtil;

import java.util.Properties;

public class LoginSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage;
    Properties prop = ConfigReader.initProperties();

    @Given("User is on the SauceDemo login page")
    public void user_is_on_login_page() {
        LoggerUtil.logInfo("User is on the SauceDemo login page");
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @When("User enters valid username and password")
    public void enter_valid_credentials() {
        LoggerUtil.logInfo("User enters valid username and password");
        loginPage.enterUsername(prop.getProperty("username"));
        loginPage.enterPassword(prop.getProperty("password"));
    }

    @And("Clicks on the login button")
    public void click_login_button() {
        LoggerUtil.logInfo("Clicks on the login button");
        loginPage.clickLogin();
    }

    @Then("User should be navigated to the products page")
    public void should_be_on_product_page() {
        LoggerUtil.logInfo("User should be navigated to the products page");
        Assert.assertTrue(loginPage.isOnProductPage());
    }
}