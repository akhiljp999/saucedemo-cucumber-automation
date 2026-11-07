package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HamburgerMenuPage;

public class HamburgerMenuSteps extends BaseTestSteps{
    public HamburgerMenuSteps(TestContext context){
        super(context);
    }
    @When("the user clicks on the hamburger icon")
    public void user_clicks_on_the_hamburger_icon(){
        context.hamburgerMenuPage = context.homePage.openHamburgerMenu();
    }

    @Then("the hamburger menu should be displayed")
    public void hamburgerMenuShouldBeDisplayed() {
        Assert.assertTrue(context.hamburgerMenuPage.findHamburgerContainer().isDisplayed(), "Hamburger menu displayed");
    }

    @And("the expected menu options should be displayed")
    public void expectedMenuOptionsShouldBeDisplayed() {
        Assert.assertTrue(context.hamburgerMenuPage.findTheAllItemsMenu().isDisplayed(), "All items menu displayed");
        Assert.assertTrue(context.hamburgerMenuPage.findAboutMenu().isDisplayed(), "About menu displayed");
        Assert.assertTrue(context.hamburgerMenuPage.findLogoutMenu().isDisplayed(), "Logout menu displayed");
        Assert.assertTrue(context.hamburgerMenuPage.findResetMenu().isDisplayed(), "Reset menu displayed");

        Assert.assertEquals(context.hamburgerMenuPage.findTheAllItemsMenu().getText(), "All Items", "All Items menu label verified");
        Assert.assertEquals(context.hamburgerMenuPage.findAboutMenu().getText(), "About", "About menu label verified");
        Assert.assertEquals(context.hamburgerMenuPage.findLogoutMenu().getText(), "Logout", "Logout menu label verified");
        Assert.assertEquals(context.hamburgerMenuPage.findResetMenu().getText(), "Reset App State", "Reset menu label verified");
    }
}
