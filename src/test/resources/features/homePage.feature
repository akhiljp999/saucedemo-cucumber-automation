@homepage
Feature: Home page
  Scenario: Display the SauceDemo home page layout
    Given the user is logged in with valid credentials
    Then the home page sections should be displayed

  Scenario: Display the hamburger menu options
    Given the user is logged in with valid credentials
    When the user clicks on the hamburger icon
    Then the hamburger menu should be displayed
    And the expected menu options should be displayed
