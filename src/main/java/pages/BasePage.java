package pages;

import factory.ConfigReader;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getPropertyAsNumber("explicit.wait.seconds")));
    }

    protected WebElement findAnElement(By locator){
        return waitForElement(locator);
    }

    protected WebElement findAnElement(WebElement sourceElement, By locator){
        return sourceElement.findElement(locator);
    }

    protected List<WebElement> findElements(By locator){
        return waitForAllElements(locator);
    }

    protected List<WebElement> areCartItemsDisplayed(By locator){
        try {
            return waitForAllElements(locator);
        } catch (TimeoutException e){
            return new ArrayList<>();
        }
    }

    protected void clickOnElement(By locator){
        waitForElement(locator).click();
    }

    protected void clickOnElement(WebElement element){
        element.click();
    }

    protected void enterText(By locator, String value){
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void enterText(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    protected String getElementText(By locator){
        return waitForElement(locator).getText();
    }

    protected WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitUntilPageLoaded(By locator){
        waitForElement(locator);
    }

    protected List<WebElement> waitForAllElements(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    protected boolean waitUntilTheInvisibilityOfElement(By locator){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected By constructTheLocatorWithXpath(String locatorXpath, String replacementText){
        return By.xpath(locatorXpath.replace("{REPLACE_TEXT}", replacementText));
    }
}
