package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/login.feature",
                "src/test/resources/features/homePage.feature",
                "src/test/resources/features/logout.feature",
                "src/test/resources/features/itemsCheckout.feature"
        },
//        dryRun = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:Resources/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        tags = ""
)
public class SauceDemoRunner extends AbstractTestNGCucumberTests {
}
