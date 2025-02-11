package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import util.Browser;
import util.DriverManager;

import java.util.List;

public class childCareLocatorPage extends basePage {

    public childCareLocatorPage(String pageTitle) {
        super(pageTitle);
    }

    private WebElement locationInputBox = DriverManager.getInstance().Driver.findElement(By.id("addressInput"));

    public void verifyPageURLContains(String text) {
        Assert.assertTrue("Page URI does not contain " + text
                + " as expected. New page URI is: " + Browser.getPageURI(), Browser.getPageURI().contains(text));
    }

    public void enterTextInLocationInputBox(String text) {
        Browser.waitForPageReady();
        Browser.waitForElementToBeClickable(locationInputBox);
        Browser.focusOnElement(locationInputBox);
        Browser.enterTextInEditBox(locationInputBox, text);
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
