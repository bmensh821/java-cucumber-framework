Feature: As a user I should be able to click on different registry and wish list buttons

  @wp
  Scenario Outline: Click on custom registry
    Given user is on Registry & WishList page
    When user clicks on "<custom>" button
    Then user redirected to "<customPage>" page

    Examples:
      | custom   | customPage |
      | College  | College    |
      | Birthday | Birthday   |

