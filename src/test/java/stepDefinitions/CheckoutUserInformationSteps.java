package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutUserInformationSteps extends BaseTestSteps{
    public CheckoutUserInformationSteps(TestContext context){
        super(context);
    }
    @Then("the checkout information page should display the required fields")
    public void checkoutUserInformationDetailsFormPageShouldDisplayWithRequiredFields() {
        Assert.assertTrue(context.checkoutUserInformationPage.findThePageTitle().isDisplayed(), "User information page displayed");
        Assert.assertEquals(context.checkoutUserInformationPage.findThePageTitle().getText(), "Checkout: Your Information", "Validated User information page title");

        Assert.assertTrue(context.checkoutUserInformationPage.findTheFirstNameField().isDisplayed(), "User information page first name field displayed");
        Assert.assertTrue(context.checkoutUserInformationPage.findTheLastNameField().isDisplayed(), "User information page last name field displayed");
        Assert.assertTrue(context.checkoutUserInformationPage.findTheZipCodeField().isDisplayed(), "User information page postal code field displayed");
        Assert.assertTrue(context.checkoutUserInformationPage.findTheContinueButton().isDisplayed(), "User information page continue button displayed");
    }

    @When("the user enters their checkout information and clicks on continue")
    public void userEnterInformationDetailsAndClickOnContinue() {
        context.checkoutUserInformationPage.enterFirstNameValue("new auto user 1");
        context.checkoutUserInformationPage.enterLastNameValue("new auto user last name");
        context.checkoutUserInformationPage.enterPostalCodeValue("12121");

        context.checkoutOverviewPage = context.checkoutUserInformationPage.clickOnContinueButton();
    }
}
