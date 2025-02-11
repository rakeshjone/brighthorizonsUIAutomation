package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DriverManager {
    private WebDriver webDriver = null;
    public SearchContext Driver = null;
    private static ThreadLocal<DriverManager> manager = new ThreadLocal<DriverManager>();


    private DriverManager() {
    }

    public static DriverManager getInstance(){
        if (manager.get()==null){
            manager.set(new DriverManager());
        }
        return manager.get();
    }

    public void LoadDriver(String browser) {
        switch (browser) {
            case "edge":
                setWebDriver(new EdgeDriver());
                break;
            case "firefox":
                setWebDriver(new FirefoxDriver());
                break;
            default:
                setWebDriver(new ChromeDriver());
                break;
        }
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))));
        webDriver.manage().window().maximize();
    }

    public void navigateToURL(String URL){
        webDriver.get(URL);
    }

    private void setWebDriver(WebDriver webDriver) {
        if (this.webDriver==null){
            this.webDriver = webDriver;
            Driver = webDriver;
        }
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }

    public void closeDriver(){
        if (webDriver !=null){
            webDriver.close();
        }
    }

    public void quitDriver(){
        if (webDriver !=null){
            webDriver.quit();
        }
    }

    public void killSession(){
        webDriver = null;
        Driver = null;
    }

    public void remove(){
        manager.remove();
    }

    public void pageReady(){
        Wait wait = fluentWait();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public Wait<WebDriver> fluentWait(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Timeout"))))
                .pollingEvery(Duration.ofMillis(Integer.parseInt(ConfigurationManager.getInstance().getProperty("Polling"))))
                .ignoring(NoSuchElementException.class);
        return wait;
    }
}
