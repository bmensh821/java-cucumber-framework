@wip
Feature: Validate Target.com monitoring scripts

  Verify that Target.com includes key monitoring scripts
  to ensure error handling and bot detection are functioning

  Background:
    Given I open the Target homepage

  Scenario: Verify PerimeterX bot detection script
    When I inspect the page scripts
    Then I should see a script with id "humanParameters"
    And the script should contain "window._pxAppId"

  Scenario: Verify BlueTriangle error listener script
    When I inspect the page scripts
    Then I should see a script with id "blueTriangle"
    And the script should contain "addEventListener('error'"
    And the script should contain "addEventListener('securitypolicyviolation'"


  Scenario: Verify Quantum Metric event listener script
    When I inspect the page scripts
    Then I should see a script with id "quantum-event-listener"
