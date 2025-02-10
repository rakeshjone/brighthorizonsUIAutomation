package pages;

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
}
