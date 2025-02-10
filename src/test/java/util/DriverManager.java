package util;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

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
    public String getPageURI() { return webDriver.getCurrentUrl();}

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
}
