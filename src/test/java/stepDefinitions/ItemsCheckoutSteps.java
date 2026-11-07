package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import models.CartItem;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemsCheckoutSteps extends BaseTestSteps{
    public ItemsCheckoutSteps(TestContext context){
        super(context);
    }

    @Then("the cart page should be displayed")
    public void checkoutPageShouldDisplay() {
        Assert.assertTrue(context.itemsCheckoutPage.findTheCheckoutButton().isDisplayed(), "Checkout button displayed");
        Assert.assertTrue(context.itemsCheckoutPage.findTheContinueShoppingButton().isDisplayed(), "Continue shopping button displayed");

        Assert.assertEquals(context.itemsCheckoutPage.findTheCartDescLabel().getText(), "Description", "Validate the items description label text");
        Assert.assertEquals(context.itemsCheckoutPage.findTheCartQuantityLabel().getText(), "QTY", "Validate the items quality label text");
    }

    @And("the cart should be empty")
    public void noItemsShouldBeAvailableInCheckoutPage() {
        Assert.assertTrue(context.itemsCheckoutPage.areCartItemsDisplayed().isEmpty(), "Validate no items available in cart now");
    }

    @Given("the user is logged in with valid credentials and is on the cart page")
    public void userLoggedToTheApplicationWithValidCredentialAndNavigatedToTheCheckoutPage() {
        successfulUserLoggedToTheApplicationWithValidCredential();
        context.itemsCheckoutPage = context.homePage.openCartPage();
    }

    @When("the user clicks on the continue shopping button")
    public void useClicksOnTheContinueShoppingButton() {
        context.homePage = context.itemsCheckoutPage.clickOnContinueShoppingButton();
    }

    @And("the cart should contain only the items added by the user")
    public void addedItemsShouldDisplayInCheckoutPage() {
        Assert.assertTrue(context.itemsCheckoutPage.findTheItemsCheckoutTitle().isDisplayed(), "Items checkout page displayed");
        Assert.assertEquals(context.itemsCheckoutPage.findTheItemsCheckoutTitle().getText(), "Your Cart", "Validated the Items checkout page title");
        Assert.assertFalse(context.expectedCartItems.isEmpty(), "Expected items must be captured before opening the cart");
        int expectedQuantity = context.expectedCartItems.stream().mapToInt(CartItem::quantity).sum();
        Assert.assertEquals(context.itemsCheckoutPage.findTheNumberOfItemsInCart(), expectedQuantity,
                "Cart badge should match the total selected quantity");
        List<CartItem> actualItems = context.itemsCheckoutPage.readCartItems();
        Assert.assertEquals(itemCounts(actualItems), itemCounts(context.expectedCartItems),
                "Cart must contain exactly the selected items with matching descriptions, prices and quantities");
    }

    private Map<CartItem, Long> itemCounts(List<CartItem> items) {
        return items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @And("the user has added items to the cart and opened the cart page")
    public void userNavigatedToCheckoutPageAfterAddingItemsToCart() {
        context.expectedCartItems.add(context.homePage.addItemToCart("Sauce Labs Backpack"));
        context.expectedCartItems.add(context.homePage.addItemToCart("Test.allTheThings() T-Shirt (Red)"));
        context.itemsCheckoutPage = context.homePage.openCartPage();
    }

    @When("the user clicks on the checkout button")
    public void userClicksOnTheCheckoutButton() {
        context.checkoutUserInformationPage = context.itemsCheckoutPage.clickOnCheckoutButton();
    }
}
