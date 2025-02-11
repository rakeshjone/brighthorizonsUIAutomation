package util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.function.BooleanSupplier;

public class Browser {

    public static void waitForTitle(String title){
        Retry(()-> DriverManager.getInstance().getPageTitle().contains(title));
    }

    public static void waitForElementVisibility(WebElement element){
        Retry(()-> element.isDisplayed() && element.isEnabled());
    }

    public static void clickOnElement(WebElement element){
        waitForElementVisibility(element);
        element.click();
    }

    public static void waitForElementToBeClickable(WebElement element) {
        getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    private static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<WebDriver>((WebDriver) DriverManager.getInstance().Driver)
                .withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))))
                .pollingEvery(Duration.ofMillis(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"))))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static void enterTextInEditBox(WebElement element, String text) {
        waitForElementVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public static void waitForPageReady(){
        DriverManager.getInstance().pageReady();
    }

    public static String getTextFromElement(WebElement element) {
        waitForElementVisibility(element);
        return element.getText();
    }

    public static void onBlurEvent(WebElement element) {
        ((JavascriptExecutor)DriverManager.getInstance().Driver).executeScript(
                "arguments[0].dispatchEvent(new Event('blur'))",
                element);
    }

    public static void focusOnElement(WebElement element){
        String javaScript = "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "arguments[0].dispatchEvent(evObj);";
        ((JavascriptExecutor) DriverManager.getInstance().Driver).executeScript(javaScript, element);
    }

    public static String getPageURI() { return ((WebDriver) DriverManager.getInstance().Driver).getCurrentUrl();}

    private static void Retry(BooleanSupplier function)
    {
        int count = 0;
        Exception exception = null;
        String exceptionMessage = "";
        int retryInterval = Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"));
        int timeOut = Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"));
        float temp = ((float) retryInterval/1000) % 60;
        int retryCount = (int) (timeOut/ temp);
        do
        {
            try
            {
                if (function.getAsBoolean()) {
                    return;
                }
                else{
                    Thread.sleep(retryInterval);
                    count++;
                }
            }
            catch (Exception ex)
            {
                exception = ex;
                count++;
            }
        } while (count != retryCount);
        System.out.println(exceptionMessage = "Retry Timed Out while trying to execute - " + new Throwable().getStackTrace()[1].getMethodName());
        throw new RuntimeException(exceptionMessage + exception);
    }
}
