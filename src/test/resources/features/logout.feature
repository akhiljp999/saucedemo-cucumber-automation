Feature: Logout
  Scenario: Log out of SauceDemo
    Given the user is logged in with valid credentials
    When the user clicks on the logout button
    Then the login page should be displayed
