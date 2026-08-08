Feature: Validation inventory Item checkout
  Scenario: Validate inventory Checkout Page
    Given user logged to the application with valid credential
    When user clicks on the cart icon
    Then checkout page should display
    And no items should be available in checkout page

  Scenario: Validate the continue shopping action from checkout page
    Given user logged to the application with valid credential and navigated to the checkout page
    When use clicks on the continue shopping button
    Then user should navigate to the home page

  Scenario: Validate items checkout page after adding new items
    Given user logged to the application with valid credential
    When user add "Sauce Labs Backpack", "Test.allTheThings() T-Shirt (Red)" to the cart
    And user clicks on the cart icon
    Then checkout page should display
    And Added items should display in checkout page


  Scenario: Validate the items checkout user information page
    Given user logged to the application with valid credential
    And user navigated to checkout page after adding items to cart
    When user clicks on the checkout button
    Then Checkout user information details form page should display with required fields


  Scenario: Validate checkout overview page
    Given user logged to the application with valid credential
    And user navigated to checkout page after adding items to cart
    And user clicks on the checkout button
    When User enter information details and click on continue
    Then checkout over page should display with checkout details

  Scenario: Validate the checkout complete process
    Given user logged to the application with valid credential
    And user navigated to checkout page after adding items to cart
    And user clicks on the checkout button
    And User enter information details and click on continue
    When user clicks on finish button from overview page
    Then user should see the order success message with order details