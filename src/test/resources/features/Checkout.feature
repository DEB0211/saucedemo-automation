Feature: Checkout Process

  @smoke @regression
  Scenario: Complete a product checkout
    Given user is on the login page
    When user logs in with valid credentials
    Then user should land on Products page
    And user adds the first product to cart
    And user navigates to cart
    And user proceeds to checkout
    When user provides valid checkout details
    And user finishes the checkout
    Then user should see order confirmation
