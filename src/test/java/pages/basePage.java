package pages;

import util.Browser;

public class basePage {
    basePage(String pageTitle) {
        Browser.waitForTitle(pageTitle);
    }
}
