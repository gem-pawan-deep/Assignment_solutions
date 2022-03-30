Feature: Application Login
  @type1
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login into application with username and password
    Then Home page is populated
    And Cards are displayed
  @type1
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login into application with "jin" and password "1234"
    Then Home page is populated
    And Cards displayed are "true"
  @type2
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login into application with "john" and password "4321"
    Then Home page is populated
    And Cards displayed are "false"
  @type1 @type2
  Scenario Outline: Home page default login
    Given User is on NetBanking landing page
    When User login in to application with <Username> and password <password>
    Then Home page is populated
    And Cards displayed are "true"

    Examples:
      |Username |password|
      |user1    |pass1   |
      |user2    |pass2   |
      |user3    |pass3   |
      |user4    |pass4   |


