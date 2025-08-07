Feature: Add Products to Cart

  @smoke
  Scenario: Add a product to cart
    Given user is logged in with valid credentials
    When user adds the first product to cart
    Then user should see the product in the cart