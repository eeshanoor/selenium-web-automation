@Inventory @Regression
Feature: Inventory page functionality

  Background:
    Given the user is logged in as "standard_user"

  @Smoke
  Scenario: Inventory page displays correct number of products
    Then the inventory page should display 6 products

  Scenario: Add single item to cart
    When the user adds the first product to the cart
    Then the cart badge should show "1"

  Scenario: Add multiple items to cart
    When the user adds product at index 0 to the cart
    And the user adds product at index 1 to the cart
    And the user adds product at index 2 to the cart
    Then the cart badge should show "3"

  Scenario: Sort products by price low to high
    When the user sorts products by "Price (low to high)"
    Then the first product price should be "$7.99"

  Scenario: Sort products by name Z to A
    When the user sorts products by "Name (Z to A)"
    Then the products should be sorted in reverse alphabetical order