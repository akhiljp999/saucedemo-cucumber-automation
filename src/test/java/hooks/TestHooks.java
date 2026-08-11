package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestHooks {
    private boolean failureScreenshotAttached;
    Scenario scenario;

    @Before
    public void setUp(Scenario scenario){
        DriverFactory.initializeDriver();
        this.scenario = scenario;
    }

    //@AfterStep only because Allure displays attachments from @After under the fixture/tear-down section, which is less obvious in the UI.
    // With @AfterStep, the screenshot appears directly under the failed step.
    @AfterStep
    public void attachScreenshotOnFailure(){
//        addInfoLogs("Test info log sample ==================");
        if (scenario.isFailed() && !failureScreenshotAttached){
            TakesScreenshot takesScreenshot = (TakesScreenshot) DriverFactory.getDriver();
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
            failureScreenshotAttached = true;
        }
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
    }

    public void addInfoLogs(String message){
        scenario.attach(message, "text/plain", "Info Log");
    }
}
