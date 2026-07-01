package stepDefinitions;

import factory.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseTestSteps{

    @Given("user navigates to the login screen")
    public void userNavigatesToTheLoginScreen(){
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loadTheLoginPage();

        Assert.assertTrue(loginPage.findThePageTitle().isDisplayed(), "The Swag Labs login page title displayed");
        Assert.assertEquals(loginPage.findThePageTitle().getText(), "Swag Labs", "The Swag Labs login page title displayed");
    }

    @When("user enter the username as {string}")
    public void userEnterTheUsernameAs(String userName) {
        loginPage.enterUserName(userName);
    }

    @And("user enter the password as {string}")
    public void userEnterThePasswordAs(String password){
        loginPage.enterPassword(password);
    }

    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        homePage = loginPage.clickOnLoginButton();
    }

    @Then("login successful and user navigates to the home screen")
    public void loginSuccessfulAndUserNavigatesToTheHomeScreen() {
        Assert.assertTrue(homePage.findThePageTitleElement().isDisplayed(), "Home page title element displayed");
        Assert.assertEquals(homePage.findThePageTitleElement().getText(), "Swag Labs", "Home page title element displayed");
    }

    @But("login page displayed with error message")
    public void loginPageDisplayedWithErrorMessage() {
        Assert.assertTrue(loginPage.findTheLoginErrorMessage().isDisplayed(), "Login page error message displayed");
        Assert.assertEquals(loginPage.findTheLoginErrorMessage().getText(), "Epic sadface: Username and password do not match any user in this service"
                , "Login error message validated");

        loginPage.closeTheLoginErrorMessage();
        Assert.assertTrue(loginPage.checkIfTheLoginErrorMessageClosed(), "Login page error message displayed");
    }

}
