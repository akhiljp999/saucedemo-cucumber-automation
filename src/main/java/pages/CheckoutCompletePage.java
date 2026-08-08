package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    By pageTitle = By.className("title");
    By completeMessageHeader = By.className("complete-header");
    By completeMessageText = By.className("complete-text");
    By backHomeButton = By.id("back-to-products");

    public WebElement findThePageTitle(){
        return findAnElement(pageTitle);
    }
    public WebElement findCompletePaymentHeader(){
        return findAnElement(completeMessageHeader);
    }
    public WebElement findTheCompletePaymentText(){
        return findAnElement(completeMessageText);
    }
    public WebElement findTheBackToHomeButton(){
        return findAnElement(backHomeButton);
    }
}
