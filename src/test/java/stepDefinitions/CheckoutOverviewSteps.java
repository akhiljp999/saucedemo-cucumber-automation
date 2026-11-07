package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import models.CartItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheckoutOverviewSteps extends BaseTestSteps{
    // Expected SauceDemo tax rule: 8% of the subtotal, rounded to cents.
    private static final BigDecimal EXPECTED_TAX_RATE = new BigDecimal("0.08");
    public CheckoutOverviewSteps(TestContext context){
        super(context);
    }
    @Then("the checkout overview page should display the checkout details")
    public void checkoutOverPageShouldDisplayWithCheckoutDetails() {
        Assert.assertTrue(context.checkoutOverviewPage.findThePageTitle().isDisplayed(), "Validated the checkout overview page displayed");
        Assert.assertEquals(context.checkoutOverviewPage.findThePageTitle().getText(), "Checkout: Overview", "Validated the checkout overview page title");

        Assert.assertTrue(context.checkoutOverviewPage.findThePaymentInfoElement().isDisplayed(), "Validated the checkout overview page payment details label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheShippingInfoElement().isDisplayed(), "Validated the checkout overview page shipping details label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findThePriceTotalTitle().isDisplayed(), "Validated the checkout overview page total price label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheTotalPriceToPayElement().isDisplayed(), "Validated the checkout overview page final price label displayed");
        Assert.assertTrue(context.checkoutOverviewPage.findTheFinishPaymentButton().isDisplayed(), "Validated the checkout overview page finish payment button displayed");
    }

    @When("the user clicks on the finish button on the overview page")
    public void userClicksOnFinishButtonFromOverviewPage() {
        context.checkoutCompletePage = context.checkoutOverviewPage.clickOnCheckoutFinishButton();
    }

    @And("the checkout overview should contain only the items added to the cart")
    public void checkoutOverviewShouldContainOnlyTheItemsAddedToTheCart() {
        Assert.assertFalse(context.expectedCartItems.isEmpty(), "Expected items must be captured before checkout");
        List<CartItem> actualItems = context.checkoutOverviewPage.readCartItems();
        Assert.assertEquals(itemCounts(actualItems), itemCounts(context.expectedCartItems),
                "Overview must contain exactly the selected items with matching descriptions, prices and quantities");
    }

    private Map<CartItem, Long> itemCounts(List<CartItem> items) {
        return items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @And("the checkout overview should display the correct totals")
    public void checkoutOverviewShouldDisplayTheCorrectTotals() {
        Assert.assertFalse(context.expectedCartItems.isEmpty(), "Expected items must be captured before checkout");
        BigDecimal expectedSubtotal = context.expectedCartItems.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.UNNECESSARY);
        BigDecimal expectedTax = expectedSubtotal.multiply(EXPECTED_TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedTotal = expectedSubtotal.add(expectedTax);

        Assert.assertEquals(context.checkoutOverviewPage.readItemSubtotal().compareTo(expectedSubtotal), 0,
                "Item subtotal should equal " + expectedSubtotal);
        Assert.assertEquals(context.checkoutOverviewPage.readTaxAmount().compareTo(expectedTax), 0,
                "Tax should equal " + expectedTax);
        Assert.assertEquals(context.checkoutOverviewPage.readTotalAmount().compareTo(expectedTotal), 0,
                "Final total should equal " + expectedTotal);
    }
}
