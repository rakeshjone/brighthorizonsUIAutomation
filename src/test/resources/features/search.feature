@SearchFeature @BH
Feature: Search

  @test1
  Scenario: Verify search functionality
    Given I navigate bright horizons website
    And I click on magnifying glass button on home page
    And I enter search text "Employee Education in 2018: Strategies to Watch"
    When I click on search button
    Then I will see search results contains search text "Employee Education in 2018: Strategies to Watch"
