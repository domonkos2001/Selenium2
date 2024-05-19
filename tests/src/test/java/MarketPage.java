import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class MarketPage extends BasePage {

    private final By searchBarLocator = By.xpath("//input[contains(@placeholder,'Mit keresel?')]");
    private final By traktorHrefLocator = By.xpath("//a[contains(@href, '/aprohirdetes/Traktor/s?skip_redirect=1')]");
    private final By searchButtonLocator = By.xpath("//div[contains(@class, 'container primary-border-container marketplace-search-additions')]//button[contains(@type,'submit')]");


    public MarketPage(WebDriver webDriver){
        super(webDriver);
    }

    public void setSearchTerm(String input){
        waitAndReturnElement(searchBarLocator).sendKeys(input);
    }

    public void clickSearch(){
        waitAndReturnElement(searchButtonLocator).click();
    }

    public boolean traktorHrefIsPresent(){
        return elementIsPresent(traktorHrefLocator);
    }


}