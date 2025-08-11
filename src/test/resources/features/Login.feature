Feature: Login

  @smoke
  Scenario: Valid login
    Given user is on the login page
    When user logs in with valid credentials
    Then user should land on Products page

  @smoke @negative
  Scenario Outline: Invalid login shows error
    Given user is on the login page
    When user logs in with username "<user>" and password "<pass>"
    Then login error message should be visible

    Examples:
      | user            | pass         |
      | standard_user   | wrong_pass   |
      | locked_out_user | secret_sauce |
