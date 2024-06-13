@SmokeTest
Feature: Login Validation

  @SmokeTest
  Scenario: Login with valid credentials
    Given User is on Amazon login page
    When User provide username
    And provide password
    And click the sign in button
    Then User should login successfully
    And I should see my profile name "Hello, chetan"