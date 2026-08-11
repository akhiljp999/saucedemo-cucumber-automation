package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features"
        },
//        dryRun = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:Resources/Reports/cucumber-report.html",
                "json:Resources/Reports/cucumber-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
//        ,
//        tags = "@login"
)
public class SauceDemoRunner extends AbstractTestNGCucumberTests {
}
