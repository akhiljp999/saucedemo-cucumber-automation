package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutUserInformationPage extends BasePage{
    public CheckoutUserInformationPage(WebDriver driver){
        super(driver);
    }

    By checkoutInfoPageTitle = By.className("title");
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By checkoutContinueButton = By.id("continue");


    public WebElement findThePageTitle(){
        return findAnElement(checkoutInfoPageTitle);
    }
    public WebElement findTheFirstNameField(){
        return findAnElement(firstNameField);
    }
    public WebElement findTheLastNameField(){
        return findAnElement(lastNameField);
    }
    public WebElement findTheZipCodeField(){
        return findAnElement(postalCodeField);
    }
    public WebElement findTheContinueButton(){
        return findAnElement(checkoutContinueButton);
    }

    public void enterFirstNameValue(String value){
        enterText(findTheFirstNameField(), value);
    }
    public void enterLastNameValue(String value){
        enterText(findTheLastNameField(), value);
    }
    public void enterPostalCodeValue(String value){
        enterText(findTheZipCodeField(), value);
    }

    public CheckoutOverviewPage clickOnContinueButton(){
        findTheContinueButton().click();
        return new CheckoutOverviewPage(driver);
    }
}
