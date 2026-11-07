Feature: Shopping cart and checkout
  Scenario: Display an empty shopping cart
    Given the user is logged in with valid credentials
    When the user clicks on the cart icon
    Then the cart page should be displayed
    And the cart should be empty

  Scenario: Continue shopping from the cart page
    Given the user is logged in with valid credentials and is on the cart page
    When the user clicks on the continue shopping button
    Then the home page should be displayed

  Scenario: Display the selected items in the shopping cart
    Given the user is logged in with valid credentials
    When the user adds "Sauce Labs Backpack" and "Test.allTheThings() T-Shirt (Red)" to the cart
    And the user clicks on the cart icon
    Then the cart page should be displayed
    And the cart should contain only the items added by the user

  Scenario: Display the checkout information form
    Given the user is logged in with valid credentials
    And the user has added items to the cart and opened the cart page
    When the user clicks on the checkout button
    Then the checkout information page should display the required fields

  Scenario: Display the selected items and correct totals on the checkout overview page
    Given the user is logged in with valid credentials
    And the user has added items to the cart and opened the cart page
    And the user clicks on the checkout button
    When the user enters their checkout information and clicks on continue
    Then the checkout overview page should display the checkout details
    And the checkout overview should contain only the items added to the cart
    And the checkout overview should display the correct totals

  Scenario: Complete checkout successfully
    Given the user is logged in with valid credentials
    And the user has added items to the cart and opened the cart page
    And the user clicks on the checkout button
    And the user enters their checkout information and clicks on continue
    When the user clicks on the finish button on the overview page
    Then the order confirmation page should display a success message