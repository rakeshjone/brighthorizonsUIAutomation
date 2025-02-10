package setup;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.ConfigurationManager;
import util.DriverManager;
import java.sql.Time;
import java.time.LocalTime;

public class Setup {
    private Scenario scenario;

    @BeforeAll
    public static void setupDriver() {
        resolvePropertiesFile();
    }

    @Before
    public void scenarioSetup(Scenario scenario){
        this.scenario = scenario;
        System.out.println("\u001B[32m" + "########starting thread: " + Thread.currentThread().getName() + " at " + Time.valueOf(LocalTime.now()) + "\u001B[0m");
        switch (ConfigurationManager.getInstance().getProperty("Local").toUpperCase()) {
            case "TRUE":
                //Run tests locally
                DriverManager.getInstance().LoadDriver(ConfigurationManager.getInstance().getProperty("browser"));
                break;
        }
        DriverManager.getInstance().navigateToURL(ConfigurationManager.getInstance().getProperty("URL"));
    }

    @After
    public void scenarioReport(){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().Driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        if (!ConfigurationManager.getInstance().getProperty("browser").equals("firefox")){
            DriverManager.getInstance().closeDriver();
        }

        DriverManager.getInstance().quitDriver();
        DriverManager.getInstance().killSession();
    }

    @AfterAll
    public static void looseDriverManager(){
        DriverManager.getInstance().remove();
    }

    private static void resolvePropertiesFile(){
        switch (ConfigurationManager.getInstance().getProperty("Environment").toUpperCase()) {
            case "TEST" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Test.properties");
            case "ACCEPTANCE" -> ConfigurationManager.getInstance().LoadAdditionalProperties("Acceptance.properties");
            default -> ConfigurationManager.getInstance().LoadAdditionalProperties("Delivery.properties");
        }
    }


}
