package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CheckoutCompleteSteps extends BaseTestSteps{
    public CheckoutCompleteSteps(TestContext context){
        super(context);
    }
    @Then("user should see the order success message with order details")
    public void userShouldSeeTheOrderSuccessMessageWithOrderDetails() {
        Assert.assertTrue(context.checkoutCompletePage.findThePageTitle().isDisplayed(), "Validated the complete payment page title displayed");
        Assert.assertEquals(context.checkoutCompletePage.findThePageTitle().getText(), "Checkout: Complete!", "Validated the complete payment page title");
        Assert.assertTrue(context.checkoutCompletePage.findTheBackToHomeButton().isDisplayed(), "Validated the complete payment page back to home button displayed");

        Assert.assertEquals(context.checkoutCompletePage.findCompletePaymentHeader().getText(), "Thank you for your order!", "Validated the complete payment page message header");
        Assert.assertEquals(context.checkoutCompletePage.findTheCompletePaymentText().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Validated the complete payment success message");
    }
}
