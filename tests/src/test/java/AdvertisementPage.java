import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AdvertisementPage extends BasePage {

    private final By newAdvertisementButtonLocator = By.xpath("//div[contains(@class, 'tabs-wrapper mt-auto clearfix')]//a[contains(@href, 'hirdetes_feladas')]");
   
    public AdvertisementPage(WebDriver webDriver){
        super(webDriver);
    }

    public NewAdvertisementPage goToNewAdvertisementPage(){
        waitAndReturnElement(newAdvertisementButtonLocator).click();

        return new NewAdvertisementPage(driver);
    }



}