Feature: Target Find a Store page

  As a user
  I want to open Target Find a Store page
  So that I can verify the header navigation and store locations list

  @target @findStore
  Scenario: Navigate to Find a Store page and validate header menu and store locations section
    Given I open the Target home page
    When I navigate to the Target Find a Store page
    Then the Find a Store page should be displayed
    And the top header menu should be visible on Find a Store page
    And store locations section should be displayed on Find a Store page