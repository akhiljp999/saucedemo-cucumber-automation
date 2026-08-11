package stepDefinitions;

import context.TestContext;
import factory.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseTestSteps{

    public LoginSteps(TestContext context){
        super(context);
    }

    @Given("user navigates to the login screen")
    public void userNavigatesToTheLoginScreen(){
        context.setLoginPage(new LoginPage(DriverFactory.getDriver()));
        context.getLoginPage().loadTheLoginPage();

        Assert.assertTrue(context.getLoginPage().findThePageTitle().isDisplayed(), "The Swag Labs login page title displayed");
        Assert.assertEquals(context.getLoginPage().findThePageTitle().getText(), "Swag Labs", "The Swag Labs login page title displayed");
    }

    @When("user enter the username as {string}")
    public void userEnterTheUsernameAs(String userName) {
        Allure.addAttachment("Log1: ", "text/plain", "Sample log, login enter user name");
        context.getLoginPage().enterUserName(userName);
    }

    @And("user enter the password as {string}")
    public void userEnterThePasswordAs(String password){
        context.getLoginPage().enterPassword(password);
    }

    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        context.homePage = context.getLoginPage().clickOnLoginButton();
    }

    @Then("login successful and user navigates to the home screen")
    public void loginSuccessfulAndUserNavigatesToTheHomeScreen() {
        Assert.assertTrue(context.homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(context.homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }

    @But("login page displayed with error message")
    public void loginPageDisplayedWithErrorMessage() {
        Assert.assertTrue(context.getLoginPage().findTheLoginErrorMessage().isDisplayed(), "Login page error message displayed");
        Assert.assertEquals(context.getLoginPage().findTheLoginErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service"
                , "Login error message validated");

        context.getLoginPage().closeTheLoginErrorMessage();
        Assert.assertTrue(context.getLoginPage().checkIfTheLoginErrorMessageClosed(), "Login page error message displayed");
    }

    @When("user clicks on the logout button")
    public void userClicksOnTheLogoutButton() {
        context.hamburgerMenuPage = context.homePage.openHamburgerMenu();
        context.setLoginPage(context.hamburgerMenuPage.clickOnLogoutButton());
    }

    @Then("user should navigate to the login screen")
    public void userShouldNavigateToTheLoginScreen() {
        Assert.assertTrue(context.getLoginPage().findThePageTitle().isDisplayed(), "Validate that user logged out successfully");
        Assert.assertEquals(context.getLoginPage().findThePageTitle().getText(), "Swag Labs", "Validate that user logged out successfully");
    }
}
