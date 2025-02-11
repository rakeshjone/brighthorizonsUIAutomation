package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import root.brightHorizons;

public class searchFeatureStepDefinition {
    @Given("I navigate bright horizons website")
    public void iNavigateBrightHorizonsWebsite() {
        brightHorizons.homePage().acceptCookies();
    }

    @And("I click on magnifying glass button on home page")
    public void iClickOnMagnifyingGlassButtonOnHomePage() {
        brightHorizons.homePage().clickOnMagnifyingSearchIcon();
    }

    @And("I enter search text {string}")
    public void iEnterSearchText(String searchText) {
        brightHorizons.homePage().enterSearchText(searchText);
    }

    @When("I click on search button")
    public void iClickOnSearchButton() {
        brightHorizons.homePage().clickOnSearchButton();
    }

    @Then("I will see search results contains search text {string}")
    public void iWillSeeSearchResultsContainsSearchText(String searchText) {
        brightHorizons.searchPage().verifyFirstSearchResultContains(searchText);
    }
}
