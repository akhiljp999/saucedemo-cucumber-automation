package context;

import pages.*;

public class TestContext {
    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    private LoginPage loginPage;
    public HomePage homePage;
    public HamburgerMenuPage hamburgerMenuPage;
    public ItemsCheckoutPage itemsCheckoutPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutUserInformationPage checkoutUserInformationPage;
    public CheckoutCompletePage checkoutCompletePage;
}
