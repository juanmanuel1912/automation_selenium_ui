Feature: Login on website nopCommerce

  Scenario Outline: Successful Login with valid Credentials
    Given user is on Login Page
    When User enters "<username>" and "<password>"
    And User click on Login Button
    Then the account option should be displayed
    Examples:
      | username                  | password     |
      | oweneneblett234@gustr.com | admin123     |
      | standard_user             | secret_sauce |

