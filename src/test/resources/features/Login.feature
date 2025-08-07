Feature: Sauce Demo Login

  @smoke
  Scenario: Successful login with valid credentials
    Given User is on the SauceDemo login page
    When User enters valid username and password
    And Clicks on the login button
    Then User should be navigated to the products page