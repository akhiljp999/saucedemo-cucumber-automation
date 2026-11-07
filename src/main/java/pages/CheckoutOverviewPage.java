package pages;

import models.CartItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage{
    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }

    By pageTitle = By.className("title");
    By cartItems = By.className("cart_item");
    By paymentInfoLabel = By.xpath("//*[@data-test='payment-info-label']");
    By shippingInfoLabel = By.xpath("//*[@data-test='shipping-info-label']");
    By priceTotalLabel = By.xpath("//*[@data-test='total-info-label']");
    By totalPriceToPay = By.xpath("//*[@data-test='total-label']");
    By totalTaxAmount = By.xpath("//*[@data-test='tax-label']");
    By itemSubtotal = By.xpath("//*[@data-test='subtotal-label']");
    By finishButton = By.id("finish");


    public List<WebElement> findCartItems(){
        return areCartItemsDisplayed(cartItems);
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

    public WebElement findThePageTitle(){
        return findAnElement(pageTitle);
    }
    public WebElement findThePaymentInfoElement(){
        return findAnElement(paymentInfoLabel);
    }
    public WebElement findTheShippingInfoElement(){
        return findAnElement(shippingInfoLabel);
    }
    public WebElement findThePriceTotalTitle(){
        return findAnElement(priceTotalLabel);
    }
    public WebElement findTheTotalPriceToPayElement(){
        return findAnElement(totalPriceToPay);
    }

    public BigDecimal readItemSubtotal() {
        return readAmount(itemSubtotal);
    }

    public BigDecimal readTaxAmount() {
        return readAmount(totalTaxAmount);
    }

    public BigDecimal readTotalAmount() {
        return readAmount(totalPriceToPay);
    }

    private BigDecimal readAmount(By locator) {
        String label = findAnElement(locator).getText();
        return new BigDecimal(label.substring(label.indexOf('$') + 1).trim());
    }
    public WebElement findTheFinishPaymentButton(){
        return findAnElement(finishButton);
    }
    public CheckoutCompletePage clickOnCheckoutFinishButton(){
        findTheFinishPaymentButton().click();
        return new CheckoutCompletePage(driver);
    }
}
