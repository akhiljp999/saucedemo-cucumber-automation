package context;

import pages.*;
import models.CartItem;
import java.util.ArrayList;
import java.util.List;

public class TestContext {
    public final List<CartItem> expectedCartItems = new ArrayList<>();
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
