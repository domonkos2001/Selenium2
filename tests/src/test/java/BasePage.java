import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasePage {

    private final By loginDropdownLocator = By.xpath("//button[contains(@class, 'button-small-secondary-custom')]");
    private final By usernameLocator = By.xpath("//div[contains(@id,'login_tab1')]//input[contains(@placeholder, 'Email c')]");
    private final By passwordLocator = By.xpath("//div[contains(@id,'login_tab1')]//input[contains(@placeholder, 'Jelsz')]");
    private final By loginButtonLocator = By.xpath("//div[contains(@id,'login_tab1')]//button[contains(@name, 'login_sbmt')]");
    private final By logoutButtonLocator = By.xpath("//div[contains(@class,'dropdown-menu dropdown-menu-right show')]//a[contains(@href, '/?logout=true')]");
    private final By newsButtonLocator = By.xpath("//div[contains(@class, 'f-container nav-secondary')]//a[contains(@href, '/hirek')]");
    private final By marketButtonLocator = By.xpath("//div[contains(@class, 'f-container nav-secondary')]//a[contains(@href, '/aprohirdetes')]");
    private final By forumButtonLocator = By.xpath("//div[contains(@class, 'f-container nav-secondary')]//a[contains(@href, '/forum')]");

    protected WebDriver driver;
    private WebDriverWait wait;


    private final By bodyLocator = By.tagName("body");

    public BasePage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, 10);
    }
    protected WebElement waitAndReturnElement(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getBodyText() {
        return waitAndReturnElement(bodyLocator).getText();    
    }

    public void setUsername(String username){
        waitAndReturnElement(usernameLocator).sendKeys(username);
    }

    public void setPassword(String password){
        waitAndReturnElement(passwordLocator).sendKeys(password);
    }
    public void clickLoginDropdown(){
        waitAndReturnElement(loginDropdownLocator).click();
    }

    public void clickLoginButton(){
        waitAndReturnElement(loginButtonLocator).click();
    }

    public void clickLogoutButton(){
        waitAndReturnElement(logoutButtonLocator).click();
    }

    public NewsPage goToNewsPage(){
        waitAndReturnElement(newsButtonLocator).click();

        return new NewsPage(driver);

    }

    public MarketPage goToMarketPage(){
        waitAndReturnElement(marketButtonLocator).click();

        return new MarketPage(driver);

    }

    public ForumPage goToForumPage(){
        waitAndReturnElement(forumButtonLocator).click();

        return new ForumPage(driver);

    }

}