package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v132.network.model.ResourceType;
import util.Browser;
import util.DriverManager;

import java.util.List;

public class childCareLocatorPage extends basePage {

    public childCareLocatorPage(String pageTitle) {
        super(pageTitle);
    }

    public void verifyPageURLContains(String text) {
        DriverManager.getInstance().startListeningToAPIResponses("AutocompletionService.GetPredictions", ResourceType.SCRIPT);
        Assert.assertTrue("Page URI does not contain " + text
                + " as expected. New page URI is: " + Browser.getPageURI(), Browser.getPageURI().contains(text));
    }

    public void enterTextInLocationInputBox(String text) {
        Browser.waitForPageReady();
        WebElement locationInputBox = DriverManager.getInstance().Driver.findElement(By.cssSelector("#addressInput"));
        //Browser.waitForElementToBeClickable(locationInputBox);
        //Browser.clickOnElement(locationInputBox);
        //Browser.waitForAttributeValue(locationInputBox,"class", "pac-target-input focus-visible");
        //Browser.enterTextUsingJS(locationInputBox);
        Browser.enterTextUsingActions(locationInputBox, "new");
        Browser.waitForAPIResponse(200);
        WebElement pacContainer = DriverManager.getInstance().Driver.findElement(By.xpath("//div[@class='pac-container pac-logo']"));

        //Browser.onBlurEvent1(pacContainer);
        //Browser.onBlurEvent(pacContainer);
        Browser.waitForElementChildren(pacContainer, By.xpath("//div"), 1);
        locationInputBox.sendKeys(Keys.ENTER);
    }

    public String getNumberOfResultsReturned() {
        WebElement numberOfCenters = DriverManager.getInstance().Driver.findElement(By.xpath("//*[@id='centerLocator_list']//span[@class='resultsNumber']"));
        return Browser.getTextFromElement(numberOfCenters);
    }

    public List<WebElement> getListOfCentersReturned() {
        return DriverManager.getInstance().Driver.findElements(By.xpath("//*[@id='center-results-container']/div[contains(@class,'centerResult')]"));
    }

    public void verifyResultsReturnedAndCountAreSameForSearchText() {
        Assert.assertTrue("Count displayed on top of page and numbers of returned results are not equal for search text", Integer.parseInt(getNumberOfResultsReturned()) == getListOfCentersReturned().size());
    }

    public void clickOnFirstCenterForSearchedLocation() {
        List<WebElement> listOfCentersOnLocation = DriverManager.getInstance().Driver.findElements(By.xpath("//*[@id='center-results-container']/div[contains(@class,'centerResult')]"));
        Browser.clickOnElement(listOfCentersOnLocation.get(0));
    }

    public void verifyAddressAreSameOnSearchResultsAndPopup() {
        List<WebElement> listOfCentersOnLocation = DriverManager.getInstance().Driver.findElements(By.xpath("//*[@id='center-results-container']/div[contains(@class,'centerResult')]"));
        String firstAddress = Browser.getTextFromElement(listOfCentersOnLocation.get(0).findElement(By.xpath(".//span[@class='centerResult__address']")));
        WebElement popupAddress = DriverManager.getInstance().Driver.findElement(By.xpath("//*[@id='map']//div[@role='dialog']//div[@class='mapTooltip__address']"));
        Assert.assertTrue("Addresses are not same on search results and popup. Search result value: "
                + firstAddress + " and popup value: " + Browser.getTextFromElement(popupAddress), firstAddress.equals(Browser.getTextFromElement(popupAddress)));
    }
}
