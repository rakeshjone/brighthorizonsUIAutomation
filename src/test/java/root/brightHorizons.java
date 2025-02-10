package root;

import pages.homePage;

public class brightHorizons {
private static pages.homePage homePage = null;

public static homePage homePage() {
return new homePage("Welcome to Bright Horizons | Bright Horizons®");
}
}
