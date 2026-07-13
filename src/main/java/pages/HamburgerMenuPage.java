package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HamburgerMenuPage extends BasePage{
    By hamburgerMenuContainer = By.className("bm-menu");
    By allItemsMenu = By.id("inventory_sidebar_link");
    By aboutMenu = By.id("about_sidebar_link");
    By logoutMenu = By.id("logout_sidebar_link");
    By resetMenu = By.id("reset_sidebar_link");

    public HamburgerMenuPage(WebDriver driver) {
        super(driver);
        waitUntilPageLoaded(hamburgerMenuContainer);
    }

    public WebElement findHamburgerContainer(){
        return findAnElement(hamburgerMenuContainer);
    }

    public WebElement findTheAllItemsMenu(){
        return findAnElement(allItemsMenu);
    }

    public WebElement findAboutMenu(){
        return findAnElement(aboutMenu);
    }

    public WebElement findLogoutMenu(){
        return findAnElement(logoutMenu);
    }

    public WebElement findResetMenu(){
        return findAnElement(resetMenu);
    }

    public HomePage navigateToInventoryPage(){
        clickOnElement(allItemsMenu);
        return new HomePage(driver);
    }

    public LoginPage clickOnLogoutButton(){
        clickOnElement(logoutMenu);
        return new LoginPage(driver);
    }
}
