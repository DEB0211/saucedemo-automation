Feature: Login functionality

  @smoke @login @positive
  Scenario: Valid user can login and logout
    Given user is on the login page
    When user logs in with valid credentials
    Then user should be navigated to the products page
    When user logs out
    Then user should be back on the login page

  @smoke @login @negative
  Scenario: Invalid login attempt
    Given user is on the login page
    When user logs in with invalid credentials
    Then user should see an error message
