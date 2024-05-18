import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class ForumPage extends BasePage {

    private final By searchBarLocator = By.xpath("//form[contains(@id, 'forum-search-form')]//input[contains(@name, 'mit')]");

    private final By searchButtonLocator = By.xpath("//button[contains(@class, 'btn button-bigger-secondary py-100 of_button of_greenbutton ')]");


    public ForumPage(WebDriver driver) {
        super(driver);
    }

     public void setSearchTerm(String input){
        waitAndReturnElement(searchBarLocator).sendKeys(input);
    }

    public void clickSearch(){
        waitAndReturnElement(searchButtonLocator).click();
    }

}