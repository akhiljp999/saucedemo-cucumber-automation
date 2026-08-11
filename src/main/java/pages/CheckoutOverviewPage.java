package pages;

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
    By finishButton = By.id("finish");


    public List<WebElement> findCartItems(){
        return areCartItemsDisplayed(cartItems);
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
    public WebElement findTheFinishPaymentButton(){
        return findAnElement(finishButton);
    }
    public CheckoutCompletePage clickOnCheckoutFinishButton(){
        findTheFinishPaymentButton().click();
        return new CheckoutCompletePage(driver);
    }
}
