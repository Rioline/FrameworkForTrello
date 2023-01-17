@smoke
Feature: Tests on trello

  @id-01
  Scenario: I log in and log out
    Given Opened main page
    When I click log in
    And I enter user email and press continue
    And I enter password and press log in
    Then Boards page is opened
    When I click log out and confirm it
    Then Main page is opened

  @id-02
  Scenario: I log in and create boards
    Given Opened main page
    When I click log in
    And I enter user email and press continue
    And I enter password and press log in
    Then Boards page is opened
    When I create new board
    When I click log out and confirm it
    Then Main page is opened

  @id-03
  Scenario: I log in and delete all boards
    Given Opened main page
    When I click log in
    And I enter user email and press continue
    And I enter password and press log in
    Then Boards page is opened
    When I delete all boards
    Then All boards are deleted
    When I click log out and confirm it
    Then Main page is opened
