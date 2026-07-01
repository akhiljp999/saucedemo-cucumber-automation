package pages;

import factory.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    By loginPageTitle = By.className("login_logo");
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By loginErrorMessage = By.xpath("//*[contains(@class, 'error-message-container error')]//h3");
    By loginErrorMessageCloseButton = By.xpath("//*[contains(@class, 'error-message-container error')]//h3/button");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loadTheLoginPage(){
        driver.get(ConfigReader.getProperty("base.url"));
        return this;
    }

    public WebElement findThePageTitle(){
        return findAnElement(loginPageTitle);
    }

    public LoginPage enterUserName(String username){
        enterText(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password){
        enterText(passwordField, password);
        return this;
    }

    public HomePage clickOnLoginButton(){
        clickOnElement(loginButton);
        return new HomePage(driver);
    }

    public WebElement findTheLoginErrorMessage(){
        return findAnElement(loginErrorMessage);
    }

    public LoginPage closeTheLoginErrorMessage(){
        clickOnElement(loginErrorMessageCloseButton);
        return this;
    }

    public boolean checkIfTheLoginErrorMessageClosed(){
        return waitUntilTheInvisibilityOfElement(loginErrorMessage);
    }
}
