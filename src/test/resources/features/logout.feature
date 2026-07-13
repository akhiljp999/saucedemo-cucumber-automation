Feature: Validate Logout feature
  Scenario: Validate logout from sauce demo
    Given user logged to the application with valid credential
    When user clicks on the logout button
    Then user should navigate to the login screen