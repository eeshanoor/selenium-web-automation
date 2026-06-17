@Login @Regression
Feature: Login functionality for SauceDemo

  Background:
    Given the user is on the SauceDemo login page

  @Smoke
  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page
    And the page title should contain "Swag Labs"

  @Smoke
  Scenario: Login with locked out user
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then an error message containing "locked out" should be displayed

  Scenario: Login with invalid credentials
    When the user enters username "invalid_user" and password "wrong_password"
    And the user clicks the login button
    Then an error message containing "Username and password do not match" should be displayed

  Scenario Outline: Login with multiple invalid users
    When the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then an error message containing "<errorMessage>" should be displayed

    Examples:
      | username         | password      | errorMessage                          |
      | invalid_user     | wrong_pass    | Username and password do not match    |
      | locked_out_user  | secret_sauce  | locked out                            |
      | standard_user    | wrong_pass    | Username and password do not match    |

  Scenario: Login with empty username
    When the user enters username "" and password "secret_sauce"
    And the user clicks the login button
    Then an error message containing "Username is required" should be displayed

  Scenario: Login with empty password
    When the user enters username "standard_user" and password ""
    And the user clicks the login button
    Then an error message containing "Password is required" should be displayed