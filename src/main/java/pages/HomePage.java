package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    By homePageTitle = By.xpath("//*[@class='header_label']//*[@class='app_logo']");
    By headerSection = By.className("header_label");
    By cartIcon = By.id("shopping_cart_container");
    By productHeader = By.className("header_secondary_container");
    By productHeaderLabel = By.className("title");
    By sortingSelectionContainer = By.className("select_container");
    By hamburgerIcon = By.className("bm-burger-button");

    By footerTwitterLink = By.xpath("//*[@class='social_twitter']//a");
    By footerFbLink = By.xpath("//*[@class='social_facebook']//a");
    By footerLinkedInLink = By.xpath("//*[@class='social_linkedin']//a");
    By footerCopyRightLabel = By.className("footer_copy");

    By inventoryItems = By.className("inventory_item");


    public WebElement findThePageTitleElement(){
        return findAnElement(homePageTitle);
    }

    public WebElement findTheHeaderSection(){
        return findAnElement(headerSection);
    }

    public WebElement findTheCartIcon(){
        return findAnElement(cartIcon);
    }

    public WebElement findTheProductHeader(){
        return findAnElement(productHeader);
    }

    public WebElement findTheProductHeaderLabel(){
        return findAnElement(productHeaderLabel);
    }

    public WebElement findTheSortingSelectionContainer(){
        return findAnElement(sortingSelectionContainer);
    }

    public WebElement findTheHamburgerIcon(){
        return findAnElement(hamburgerIcon);
    }

    public WebElement findTheFooterTwitterLinkIcon(){
        return findAnElement(footerTwitterLink);
    }
    public WebElement findTheFooterFbLinkIcon(){
        return findAnElement(footerFbLink);
    }
    public WebElement findTheFooterLinkedInLinkIcon(){
        return findAnElement(footerLinkedInLink);
    }

    public WebElement findFooterCopyRightLabelElement(){
        return findAnElement(footerCopyRightLabel);
    }

    public List<WebElement> findAllInventoryItems(){
        return findElements(inventoryItems);
    }



}
