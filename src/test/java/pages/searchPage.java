package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Browser;
import util.DriverManager;

import java.util.List;

public class searchPage extends basePage {

    public searchPage(String pageTitle) {
        super(pageTitle);
    }

    public void verifyFirstSearchResultContains(String text) {
        Browser.waitForPageReady();
        List<WebElement> searchResults = DriverManager.getInstance().Driver.findElements(By.xpath("//*[@id='mainContent']//a[@class='search-result']"));
        Assert.assertTrue("Search text does not appear in first search result", Browser.getTextFromElement(searchResults.get(0).findElement(By.xpath(".//h3"))).equals(text));
    }
}
