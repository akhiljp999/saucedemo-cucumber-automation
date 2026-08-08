package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutOverviewPage;

public class CheckoutOverviewSteps extends BaseTestSteps{
    public CheckoutOverviewSteps(TestContext context){
        super(context);
    }
    @Then("checkout over page should display with checkout details")
    public void checkoutOverPageShouldDisplayWithCheckoutDetails() {
        Assert.assertTrue(context.checkoutOverviewPage.findThePageTitle().isDisplayed(), "Validated the checkout overview page displayed");
        Assert.assertEquals(context.checkoutOverviewPage.findThePageTitle().getText(), "Checkout: Overview", "Validated the checkout overview page title");

        Assert.assertTrue(context.checkoutOverviewPage.findThePaymentInfoElement().isDisplayed(), "Validated the checkout overview page payment details label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheShippingInfoElement().isDisplayed(), "Validated the checkout overview page shipping details label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findThePriceTotalTitle().isDisplayed(), "Validated the checkout overview page total price label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheTotalPriceToPayElement().isDisplayed(), "Validated the checkout overview page final price label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheFinishPaymentButton().isDisplayed(), "Validated the checkout overview page finish payment button displayed");
    }

    @When("user clicks on finish button from overview page")
    public void userClicksOnFinishButtonFromOverviewPage() {
        context.checkoutCompletePage = context.checkoutOverviewPage.clickOnCheckoutFinishButton();
    }
}
