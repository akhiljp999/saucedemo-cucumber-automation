@login @smoke
Feature: Validate the login page functionalities
  Scenario: Login with valid credentials
    Given user navigates to the login screen
    When user enter the username as "standard_user"
    And user enter the password as "secret_sauce"
    And user clicks on the login button
    Then login successful and user navigates to the home screen

 Scenario Outline: Login with multiple valid users
    Given user navigates to the login screen
    When user enter the username as "<username>"
    And user enter the password as "<password>"
    And user clicks on the login button
    Then login successful and user navigates to the home screen
   Examples:
     |  username          | password      |
     |  standard_user     | secret_sauce  |
     |  locked_out_user   | secret_sauce  |

 Scenario: Validate login with invalid credentials
   Given user navigates to the login screen
   When user enter the username as "username"
   And user enter the password as "password"
   And user clicks on the login button
   But login page displayed with error message
