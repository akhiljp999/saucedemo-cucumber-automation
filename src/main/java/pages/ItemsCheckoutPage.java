package pages;

import models.CartItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemsCheckoutPage extends BasePage{

    public ItemsCheckoutPage(WebDriver driver){
        super(driver);
    }

    By pageTitle = By.className("title");
    By continueShoppingButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");
    By cartQuantityLabel = By.className("cart_quantity_label");
    By cartDescLabel = By.className("cart_desc_label");

    By cartItems = By.className("cart_item");

    By cartAddedItemsCount = By.className("shopping_cart_badge");



    public WebElement findTheContinueShoppingButton(){
        return findAnElement(continueShoppingButton);
    }
    public WebElement findTheCheckoutButton(){
        return findAnElement(checkoutButton);
    }
    public WebElement findTheCartQuantityLabel(){
        return findAnElement(cartQuantityLabel);
    }
    public WebElement findTheCartDescLabel(){
        return findAnElement(cartDescLabel);
    }
    public WebElement findTheItemsCheckoutTitle(){return findAnElement(pageTitle);}

    public List<WebElement> findCartItems(){
        return findElements(cartItems);
    }

    public List<CartItem> readCartItems() {
        List<CartItem> items = new ArrayList<>();
        for (WebElement item : findCartItems()) {
            items.add(new CartItem(
                    item.findElement(By.className("inventory_item_name")).getText(),
                    item.findElement(By.className("inventory_item_desc")).getText(),
                    new BigDecimal(item.findElement(By.className("inventory_item_price"))
                            .getText().replace("$", "").trim()),
                    Integer.parseInt(item.findElement(By.className("cart_quantity")).getText().trim())));
        }
        return items;
    }

    public List<WebElement> areCartItemsDisplayed(){
        return areCartItemsDisplayed(cartItems);
    }

    public int findTheNumberOfItemsInCart(){
        return Integer.parseInt(getElementText(cartAddedItemsCount));
    }

    public HomePage clickOnContinueShoppingButton(){
        findTheContinueShoppingButton().click();
        return new HomePage(driver);
    }
    public CheckoutUserInformationPage clickOnCheckoutButton(){
        findTheCheckoutButton().click();
        return new CheckoutUserInformationPage(driver);
    }
}
