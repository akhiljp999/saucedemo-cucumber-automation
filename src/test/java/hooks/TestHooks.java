package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestHooks {
    @Before
    public void setUp(){
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            TakesScreenshot takesScreenshot = (TakesScreenshot) DriverFactory.getDriver();
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
