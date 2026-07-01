package stepDefinitions;

import factory.ConfigReader;
import factory.DriverFactory;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class BaseTestSteps {
    LoginPage loginPage;
    HomePage homePage;

    public void successfulUserLoggedToTheApplicationWithValidCredential(){
        loginPage = new LoginPage(DriverFactory.getDriver());
        homePage = loginPage.loadTheLoginPage()
                .enterUserName(ConfigReader.getProperty("adminUser"))
                .enterPassword(ConfigReader.getProperty("adminPassword"))
                .clickOnLoginButton();
        Assert.assertTrue(homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }
}
