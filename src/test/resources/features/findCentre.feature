@findCentreFeature @BH
Feature: Find Centre

  @test2
  Scenario: Verify comments
    Given I navigate bright horizons website
    And I Click on Find a Center option on top header
    And I am navigated to child care locator page
    When I Type "New York" into search box and press Enter
    Then I will see number of found centers is the same as a number of centers displayed on the list below
    And I Click on the first center on the list
    And I will see center name and address are the same on the list and on the popup