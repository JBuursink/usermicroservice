Feature: User
  Scenario: Retrieving a user
    Given I have a user "username"
    When I try and POST the user
    Then I can GET the user
      | username |
