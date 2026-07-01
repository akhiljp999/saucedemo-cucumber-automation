package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HomePageSteps extends BaseTestSteps{
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
        Assert.assertTrue(homePage.findTheHeaderSection().isDisplayed(), "Validate the header section displayed");
        Assert.assertTrue(homePage.findTheHamburgerIcon().isDisplayed(), "Validate the hamburger menu displayed");
        Assert.assertTrue(homePage.findTheCartIcon().isDisplayed(), "Validate the cart icon displayed");
        Assert.assertTrue(homePage.findTheProductHeader().isDisplayed(), "Validate the product header section displayed");
        Assert.assertTrue(homePage.findTheProductHeaderLabel().isDisplayed(), "Validate the product header section label displayed");
        Assert.assertEquals(homePage.findTheProductHeaderLabel().getText(), "Products",  "Validate the product header section label");
        Assert.assertTrue(homePage.findTheSortingSelectionContainer().isDisplayed(), "Validate the sorting selection section displayed");
    }

    private void validateFooterSection(){
        Assert.assertTrue(homePage.findTheFooterTwitterLinkIcon().isDisplayed(), "Validated the footer Twitter icon displayed");
        Assert.assertTrue(homePage.findTheFooterFbLinkIcon().isDisplayed(), "Validated the footer FB icon displayed");
        Assert.assertTrue(homePage.findTheFooterLinkedInLinkIcon().isDisplayed(), "Validated the footer LinkedIn icon displayed");

        Assert.assertEquals(homePage.findTheFooterTwitterLinkIcon().getAttribute("href"), "https://twitter.com/saucelabs", "Validated the url of the twitter link");
        Assert.assertEquals(homePage.findTheFooterFbLinkIcon().getAttribute("href"), "https://www.facebook.com/saucelabs", "Validated the url of the FB link");
        Assert.assertEquals(homePage.findTheFooterLinkedInLinkIcon().getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/", "Validated the url of the LinkedIn link");
        Assert.assertEquals(homePage.findFooterCopyRightLabelElement().getText(), "© 2026 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy", "Validated the footer copy rights label");
    }

    private void validateBodySection(){

    }
}
