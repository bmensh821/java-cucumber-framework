  @wip
Feature: Access Target Circle 360 from hamburger menu
 Verify that users can navigate to Target Circle 360

  Scenario: Click Target Circle 360 from hamburger menu
    Given I open the Target homepage
    When I click the hamburger menu
    And I click the Target Circle 360 link
    Then I should be on the Target Circle 360 page

