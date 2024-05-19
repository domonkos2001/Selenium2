import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class NewAdvertisementPage extends BasePage {

    private final By newMachineAdvertisementButtonLocator = By.xpath("//label[contains(@for, 'category-input-gep')]");

    public NewAdvertisementPage(WebDriver webDriver){
        super(webDriver);
    }

    public NewMachineAdvertisement goToNewMachineAdvertisementPage(){
        waitAndReturnElement(newMachineAdvertisementButtonLocator).click();

        return new NewMachineAdvertisement(driver);
    }



}