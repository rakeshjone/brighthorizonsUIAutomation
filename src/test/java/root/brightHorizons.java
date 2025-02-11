package root;

import pages.*;

public class brightHorizons {
    private static homePage homePage = null;
    private static childCareLocatorPage childCareLocatorPage = null;
    private static searchPage searchPage = null;

    public static homePage homePage() {
        return new homePage("Welcome to Bright Horizons | Bright Horizons");
    }

    public static childCareLocatorPage childCareLocatorPage() {
        return new childCareLocatorPage("Find a Child Care Center or Preschool | Bright Horizons");
    }

    public static searchPage searchPage() {
        return new searchPage("Search | Bright Horizons");
    }
}
