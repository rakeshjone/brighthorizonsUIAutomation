package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import root.brightHorizons;
import util.Browser;

public class findCentreFeatureStepDefinition {
    @And("I Click on Find a Center option on top header")
    public void iClickOnFindACenterOptionOnTopHeader() {
        brightHorizons.homePage().clickOnFindACenterButton();
    }

    @And("I am navigated to child care locator page")
    public void iAmNavigatedToChildCareLocatorPage() {
        brightHorizons.childCareLocatorPage().verifyPageURLContains("/child-care-locator");
    }

    @When("I Type {string} into search box and press Enter")
    public void iTypeIntoSearchBoxAndPressEnter(String searchText) {
        brightHorizons.childCareLocatorPage().enterTextInLocationInputBox(searchText);
    }

    @Then("I will see number of found centers is the same as a number of centers displayed on the list below")
    public void iWillSeeNumberOfFoundCentersIsTheSameAsANumberOfCentersDisplayedOnTheListBelow() {
        brightHorizons.childCareLocatorPage().verifyResultsReturnedAndCountAreSameForSearchText();
    }

    @And("I Click on the first center on the list")
    public void iClickOnTheFirstCenterOnTheList() {
        brightHorizons.childCareLocatorPage().clickOnFirstCenterForSearchedLocation();
    }

    @And("I will see center name and address are the same on the list and on the popup")
    public void iWillSeeCenterNameAndAddressAreTheSameOnTheListAndOnThePopup() {
        brightHorizons.childCareLocatorPage().verifyAddressAreSameOnSearchResultsAndPopup();
    }
}
