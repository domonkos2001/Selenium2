import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class NewMachineAdvertisement extends BasePage {

    private final By newImageLocator = By.xpath("//li[contains(@id, 'img_0')]//img[contains(@alt, 'p hozz')]");
    private final String filePathToImage = System.getProperty("user.dir") + "/src/test/resources/Basketball.png";

    public NewMachineAdvertisement(WebDriver webDriver){
        super(webDriver);
    }

    public void UploadImage(String imagePath){
        waitAndReturnElement(newImageLocator).click();

        WebElement inputfile = waitAndReturnElement(By.cssSelector("input[type='file']"));

        inputfile.sendKeys(filePathToImage);
    }


}
