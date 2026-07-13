package stepDefinitions;

import context.TestContext;
import factory.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseTestSteps{

    public LoginSteps(TestContext context){
        super(context);
    }

    @Given("user navigates to the login screen")
    public void userNavigatesToTheLoginScreen(){
        context.loginPage = new LoginPage(DriverFactory.getDriver());
        context.loginPage.loadTheLoginPage();

        Assert.assertTrue(context.loginPage.findThePageTitle().isDisplayed(), "The Swag Labs login page title displayed");
        Assert.assertEquals(context.loginPage.findThePageTitle().getText(), "Swag Labs", "The Swag Labs login page title displayed");
    }

    @When("user enter the username as {string}")
    public void userEnterTheUsernameAs(String userName) {
        context.loginPage.enterUserName(userName);
    }

    @And("user enter the password as {string}")
    public void userEnterThePasswordAs(String password){
        context.loginPage.enterPassword(password);
    }

    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        context.homePage = context.loginPage.clickOnLoginButton();
    }

    @Then("login successful and user navigates to the home screen")
    public void loginSuccessfulAndUserNavigatesToTheHomeScreen() {
        Assert.assertTrue(context.homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(context.homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }

    @But("login page displayed with error message")
    public void loginPageDisplayedWithErrorMessage() {
        Assert.assertTrue(context.loginPage.findTheLoginErrorMessage().isDisplayed(), "Login page error message displayed");
        Assert.assertEquals(context.loginPage.findTheLoginErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service"
                , "Login error message validated");

        context.loginPage.closeTheLoginErrorMessage();
        Assert.assertTrue(context.loginPage.checkIfTheLoginErrorMessageClosed(), "Login page error message displayed");
    }

    @When("user clicks on the logout button")
    public void userClicksOnTheLogoutButton() {
        context.hamburgerMenuPage = context.homePage.openHamburgerMenu();
        context.loginPage = context.hamburgerMenuPage.clickOnLogoutButton();
    }

    @Then("user should navigate to the login screen")
    public void userShouldNavigateToTheLoginScreen() {
        Assert.assertTrue(context.loginPage.findThePageTitle().isDisplayed(), "Validate that user logged out successfully");
        Assert.assertEquals(context.loginPage.findThePageTitle().getText(), "Swag Labs", "Validate that user logged out successfully");
    }
}
