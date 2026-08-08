package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ItemsCheckoutSteps extends BaseTestSteps{
    public ItemsCheckoutSteps(TestContext context){
        super(context);
    }

    @Then("checkout page should display")
    public void checkoutPageShouldDisplay() {
        Assert.assertTrue(context.itemsCheckoutPage.findTheCheckoutButton().isDisplayed(), "Checkout button displayed");
        Assert.assertTrue(context.itemsCheckoutPage.findTheContinueShoppingButton().isDisplayed(), "Continue shopping button displayed");

        Assert.assertEquals(context.itemsCheckoutPage.findTheCartDescLabel().getText(), "Description", "Validate the items description label text");
        Assert.assertEquals(context.itemsCheckoutPage.findTheCartQuantityLabel().getText(), "QTY", "Validate the items quality label text");
    }

    @And("no items should be available in checkout page")
    public void noItemsShouldBeAvailableInCheckoutPage() {
        Assert.assertTrue(context.itemsCheckoutPage.findCartItems().isEmpty(), "Validate no items available in cart now");
    }

    @Given("user logged to the application with valid credential and navigated to the checkout page")
    public void userLoggedToTheApplicationWithValidCredentialAndNavigatedToTheCheckoutPage() {
        successfulUserLoggedToTheApplicationWithValidCredential();
        context.itemsCheckoutPage = context.homePage.openCartPage();
    }

    @When("use clicks on the continue shopping button")
    public void useClicksOnTheContinueShoppingButton() {
        context.homePage = context.itemsCheckoutPage.clickOnContinueShoppingButton();
    }

    @And("Added items should display in checkout page")
    public void addedItemsShouldDisplayInCheckoutPage() {
        Assert.assertTrue(context.itemsCheckoutPage.findTheItemsCheckoutTitle().isDisplayed(), "Items checkout page displayed");
        Assert.assertEquals(context.itemsCheckoutPage.findTheItemsCheckoutTitle().getText(), "Your Cart", "Validated the Items checkout page title");
        Assert.assertEquals(context.itemsCheckoutPage.findTheNumberOfItemsInCart(), 2, "Validated the number of items added in cart");
        Assert.assertEquals(context.itemsCheckoutPage.findCartItems().size(), 2, "Validated the number of items displayed in checkout page");
    }

    @And("user navigated to checkout page after adding items to cart")
    public void userNavigatedToCheckoutPageAfterAddingItemsToCart() {
        context.homePage.findTheInventoryAddToCartElement(context.homePage.findAnInventoryItemByLabel("Sauce Labs Backpack")).click();
        context.homePage.findTheInventoryAddToCartElement(context.homePage.findAnInventoryItemByLabel("Test.allTheThings() T-Shirt (Red)")).click();
        context.itemsCheckoutPage = context.homePage.openCartPage();
    }

    @When("user clicks on the checkout button")
    public void userClicksOnTheCheckoutButton() {
        context.checkoutUserInformationPage = context.itemsCheckoutPage.clickOnCheckoutButton();
    }
}
