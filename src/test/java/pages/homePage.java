package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Browser;
import util.DriverManager;

import java.util.List;

public class homePage extends basePage {
    private WebElement manageCookies = DriverManager.getInstance().Driver.findElement(By.id("onetrust-accept-btn-handler"));


    public homePage(String pageTitle) {
        super(pageTitle);
    }

    public void acceptCookies() {
        Browser.clickOnElement(manageCookies);
    }

    public void clickOnMagnifyingSearchIcon() {
        List<WebElement> magnifyingGlass = DriverManager.getInstance().Driver.findElements(By.xpath("//a[contains(@class,'nav-link-search track_nav_interact')]/span[@class='icon-search bhc-icon-search-rounded']"));
        Browser.clickOnElement(magnifyingGlass.get(1));
    }

    public void clickOnFindACenterButton() {
        List<WebElement> findACenterButton = DriverManager.getInstance().Driver.findElements(By.xpath("//li[@class='nav-item displayed-desktop']/a[contains(text(),'Find a Center')]"));
        Browser.clickOnElement(findACenterButton.get(1));
        Browser.waitForPageReady();
    }

    public void enterSearchText(String searchText) {
        List<WebElement> searchInput = DriverManager.getInstance().Driver.findElements(By.id("search-field"));
        //Browser.waitForElementToBeClickable(searchInput);
        Browser.enterTextInEditBox(searchInput.get(1), searchText);
    }

    public void clickOnSearchButton() {
        WebElement searchButton = DriverManager.getInstance().Driver.findElement(By.xpath("//*[@id='subnav-search-desktop-top']//button"));
        Browser.waitForElementToBeClickable(searchButton);
        Browser.clickOnElement(searchButton);
    }
}
