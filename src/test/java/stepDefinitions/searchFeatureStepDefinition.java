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
    public void iEnterSearchText(String arg0) {
    }

    @When("I click on search button")
    public void iClickOnSearchButton() {
    }

    @Then("I will see search results contains serach text")
    public void iWillSeeSearchResultsContainsSerachText() {
    }
}
