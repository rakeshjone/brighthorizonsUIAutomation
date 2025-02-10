@SearchFeature @BH
Feature: Search

  @test1
  Scenario: Verify search functionality
    Given I navigate bright horizons website
    And I click on magnifying glass button on home page
    And I enter search text ""
    When I click on search button
    Then I will see search results contains serach text
