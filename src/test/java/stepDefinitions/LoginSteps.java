package stepDefinitions;

import context.TestContext;
import factory.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseTestSteps{

    public LoginSteps(TestContext context){
        super(context);
    }

    @Given("the user navigates to the login page")
    public void userNavigatesToTheLoginScreen(){
        context.setLoginPage(new LoginPage(DriverFactory.getDriver()));
        context.getLoginPage().loadTheLoginPage();

        Assert.assertTrue(context.getLoginPage().findThePageTitle().isDisplayed(), "The Swag Labs login page title displayed");
        Assert.assertEquals(context.getLoginPage().findThePageTitle().getText(), "Swag Labs", "The Swag Labs login page title displayed");
    }

    @When("the user enters the username {string}")
    public void userEnterTheUsernameAs(String userName) {
        Allure.addAttachment("Log1: ", "text/plain", "Sample log, login enter user name");
        context.getLoginPage().enterUserName(userName);
    }

    @And("the user enters the password {string}")
    public void userEnterThePasswordAs(String password){
        context.getLoginPage().enterPassword(password);
    }

    @And("the user submits valid login credentials")
    public void userSubmitsValidLoginCredentials() {
        context.homePage = context.getLoginPage().loginExpectingSuccess();
    }

    @Then("login should succeed and the home page should be displayed")
    public void loginSuccessfulAndUserNavigatesToTheHomeScreen() {
        Assert.assertTrue(context.homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(context.homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }

    @But("the login page should display an error message")
    public void loginPageDisplayedWithErrorMessage() {
        Assert.assertTrue(context.getLoginPage().findTheLoginErrorMessage().isDisplayed(), "Login page error message displayed");
        Assert.assertEquals(context.getLoginPage().findTheLoginErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service"
                , "Login error message validated");

        context.getLoginPage().closeTheLoginErrorMessage();
        Assert.assertTrue(context.getLoginPage().checkIfTheLoginErrorMessageClosed(), "Login page error message displayed");
    }

    @When("the user clicks on the logout button")
    public void userClicksOnTheLogoutButton() {
        context.hamburgerMenuPage = context.homePage.openHamburgerMenu();
        context.setLoginPage(context.hamburgerMenuPage.clickOnLogoutButton());
    }

    @Then("the login page should be displayed")
    public void userShouldNavigateToTheLoginScreen() {
        Assert.assertTrue(context.getLoginPage().findThePageTitle().isDisplayed(), "Validate that user logged out successfully");
        Assert.assertEquals(context.getLoginPage().findThePageTitle().getText(), "Swag Labs", "Validate that user logged out successfully");
    }

    @And("the user submits invalid login credentials")
    public void userSubmitsInvalidLoginCredentials() {
        context.getLoginPage().loginExpectingFailure();
    }
}
