@homepage
Feature: Validate Home page
  Scenario: Validate the swag lab home screen layout
    Given user logged to the application with valid credential
    Then home page sections should be displayed

  Scenario: Validate Home Page Hamburger Menu
    Given user logged to the application with valid credential
    When user clicks on the hamburger icon
    Then Hamburger menu should be displayed