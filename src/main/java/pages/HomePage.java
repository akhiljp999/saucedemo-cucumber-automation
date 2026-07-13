package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
        waitUntilPageLoaded(homePageTitle);
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

    String inventoryItemsXpath = "//div[@class='inventory_item_label']//div[text()='{REPLACE_TEXT}']//ancestor::div[@class='inventory_item']";

    By inventoryTitle = By.className("inventory_item_name");
    By inventoryPrice = By.className("inventory_item_price");
    By inventoryAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");


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

    public WebElement findAnInventoryItemByIndex(int index){
        return findAllInventoryItems().get(index);
    }

    public WebElement findAnInventoryItemByLabel(String label){
        return findAnElement(constructTheLocatorWithXpath(inventoryItemsXpath, label));
    }

    public WebElement findTheInventoryTitleElement(WebElement element){
        return findAnElement(element, inventoryTitle);
    }
    public WebElement findTheInventoryPriceElement(WebElement element){
        return findAnElement(element, inventoryPrice);
    }
    public WebElement findTheInventoryAddToCartElement(WebElement element){
        return findAnElement(element, inventoryAddToCartButton);
    }

    public HamburgerMenuPage openHamburgerMenu(){
        findTheHamburgerIcon().click();
        return new HamburgerMenuPage(driver);
    }




}
