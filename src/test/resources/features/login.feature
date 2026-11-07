@login
Feature: Login
  @smoke
  Scenario: Log in with valid credentials
    Given the user navigates to the login page
    When the user enters the username "standard_user"
    And the user enters the password "secret_sauce"
    And the user submits valid login credentials
    Then login should succeed and the home page should be displayed

  Scenario Outline: Log in with different valid users
    Given the user navigates to the login page
    When the user enters the username "<username>"
    And the user enters the password "<password>"
    And the user submits valid login credentials
    Then login should succeed and the home page should be displayed
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
      | visual_user   | secret_sauce |

  Scenario: Reject invalid login credentials
    Given the user navigates to the login page
    When the user enters the username "username"
    And the user enters the password "password"
    And the user submits invalid login credentials
    Then the login page should display an error message
