import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class NewsPage extends BasePage {

    private final By searchBarLocator = By.xpath("//form[contains(@id, 'hir-kereses-form')]//input[contains(@name, 'search')]");

    private final By searchButtonLocator = By.xpath("//form[contains(@id, 'hir-kereses-form')]//button[contains(@name, 'ker_submit')]");

    public NewsPage(WebDriver webDriver){
        super(webDriver);
    }

    public void setSearchTerm(String input){
        waitAndReturnElement(searchBarLocator).sendKeys(input);
    }

    public void clickSearch(){
        waitAndReturnElement(searchButtonLocator).click();
    }


}