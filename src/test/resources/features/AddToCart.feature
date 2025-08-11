Feature: Add to cart

  @smoke
  Scenario: Add first product to cart
    Given user is on the login page
    When user logs in with valid credentials
    Then user should land on Products page
    And user adds the first product to cart
    And user navigates to cart
