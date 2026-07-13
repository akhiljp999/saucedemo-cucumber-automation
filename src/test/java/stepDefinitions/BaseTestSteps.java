package stepDefinitions;

import context.TestContext;
import factory.ConfigReader;
import factory.DriverFactory;
import org.testng.Assert;
import pages.LoginPage;

public class BaseTestSteps {
    protected TestContext context;

    public BaseTestSteps(TestContext context){
        this.context = context;
    }
   public void successfulUserLoggedToTheApplicationWithValidCredential(){
       context.loginPage = new LoginPage(DriverFactory.getDriver());
       context.homePage = context.loginPage.loadTheLoginPage()
                .enterUserName(ConfigReader.getProperty("adminUser"))
                .enterPassword(ConfigReader.getProperty("adminPassword"))
                .clickOnLoginButton();
        Assert.assertTrue(context.homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(context.homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }
}
