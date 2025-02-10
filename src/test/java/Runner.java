import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
        glue= {"stepDefinitions","setup"},
        monochrome = true,
        plugin = {"pretty","html:target/HtmlReports/testResult.html",
                "json:target/JSONReports/testResult.json",
                "junit:target/XMLReports/testResult.xml"},
        tags = "@bh")
public class Runner {
}
