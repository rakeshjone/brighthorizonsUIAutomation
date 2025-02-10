package util;

import org.openqa.selenium.WebElement;

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
