package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePageSteps extends BaseTestSteps{
    public HomePageSteps(TestContext testContext){
        super(testContext);
    }

    @Given("user logged to the application with valid credential")
    public void userLoggedToTheApplicationWithValidCredential(){
        successfulUserLoggedToTheApplicationWithValidCredential();
    }

    @Then("home page sections should be displayed")
    public void homePageSectionsShouldBeDisplayed(){
        validateHeaderSection();
        validateFooterSection();
        validateBodySection();
    }

    private void validateHeaderSection(){
        Assert.assertTrue(context.homePage.findTheHeaderSection().isDisplayed(), "Validate the header section displayed");
        Assert.assertTrue(context.homePage.findTheHamburgerIcon().isDisplayed(), "Validate the hamburger menu displayed");
        Assert.assertTrue(context.homePage.findTheCartIcon().isDisplayed(), "Validate the cart icon displayed");
        Assert.assertTrue(context.homePage.findTheProductHeader().isDisplayed(), "Validate the product header section displayed");
        Assert.assertTrue(context.homePage.findTheProductHeaderLabel().isDisplayed(), "Validate the product header section label displayed");
        Assert.assertEquals(context.homePage.findTheProductHeaderLabel().getText(), "Products",  "Validate the product header section label");
        Assert.assertTrue(context.homePage.findTheSortingSelectionContainer().isDisplayed(), "Validate the sorting selection section displayed");
    }

    private void validateFooterSection(){
        Assert.assertTrue(context.homePage.findTheFooterTwitterLinkIcon().isDisplayed(), "Validated the footer Twitter icon displayed");
        Assert.assertTrue(context.homePage.findTheFooterFbLinkIcon().isDisplayed(), "Validated the footer FB icon displayed");
        Assert.assertTrue(context.homePage.findTheFooterLinkedInLinkIcon().isDisplayed(), "Validated the footer LinkedIn icon displayed");

        Assert.assertEquals(context.homePage.findTheFooterTwitterLinkIcon().getAttribute("href"), "https://twitter.com/saucelabs", "Validated the url of the twitter link");
        Assert.assertEquals(context.homePage.findTheFooterFbLinkIcon().getAttribute("href"), "https://www.facebook.com/saucelabs", "Validated the url of the FB link");
        Assert.assertEquals(context.homePage.findTheFooterLinkedInLinkIcon().getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/", "Validated the url of the LinkedIn link");
        Assert.assertEquals(context.homePage.findFooterCopyRightLabelElement().getText(), "© 2026 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy", "Validated the footer copy rights label");
    }

    private void validateBodySection(){
        for (WebElement element : context.homePage.findAllInventoryItems()){
            Assert.assertTrue(context.homePage.findTheInventoryTitleElement(element).isDisplayed(), "Validate that inventory item displayed");
        }

        WebElement sauceLabBackpackInv = context.homePage.findAnInventoryItemByLabel("Sauce Labs Backpack");
        Assert.assertEquals(context.homePage.findTheInventoryTitleElement(sauceLabBackpackInv).getText(), "Sauce Labs Backpack", "Validated the inventory title");
        Assert.assertEquals(context.homePage.findTheInventoryPriceElement(sauceLabBackpackInv).getText(), "$29.99", "Validated the inventory price label");
        Assert.assertEquals(context.homePage.findTheInventoryAddToCartElement(sauceLabBackpackInv).getText(), "Add to cart", "Validated the inventory add to cart button label");
    }

    @When("user clicks on the cart icon")
    public void userClicksOnTheCartIcon() {
        context.itemsCheckoutPage = context.homePage.openCartPage();
    }

    @Then("user should navigate to the home page")
    public void userShouldNavigateToTheHomePage() {
        Assert.assertTrue(context.homePage.findTheHeaderSection().isDisplayed(), "Validate the header section displayed");
        Assert.assertTrue(context.homePage.findTheHamburgerIcon().isDisplayed(), "Validate the hamburger menu displayed");
    }

    @When("user add {string}, {string} to the cart")
    public void userAddToTheCart(String inventoryItem1, String inventoryItem2) {
        context.homePage.findTheInventoryAddToCartElement(context.homePage.findAnInventoryItemByLabel(inventoryItem1)).click();
        context.homePage.findTheInventoryAddToCartElement(context.homePage.findAnInventoryItemByLabel(inventoryItem2)).click();
    }

}
