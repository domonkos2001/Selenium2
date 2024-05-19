import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import java.util.ArrayList; 
import java.net.MalformedURLException;
import java.net.URL;

class Page {

    String url;
    String expectedTitle;
    
    Page(String url,String expectedTitle){
        this.url = url;
        this.expectedTitle = expectedTitle;

    }

    public String getUrl(){
        return url;
    }
    public String getExpectedTitle(){
        return expectedTitle;
    }

}

public class SeleniumTest {

    WebDriver driver;
    private WebDriverWait wait;
    
    private final String email = "domonkos2001@gmail.com";
    private final String password = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"; 
    private final String searchTermNews = "kukorica";
    private final String searchTermMarket = "Traktor";

    @Before
    public void setup() throws MalformedURLException{
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        
    }

    @Test
    public void staticPageTitleTest() {
        List<Page> pages = new ArrayList<>();

        pages.add(new Page("https://www.agroinform.hu/hirek", "gazdas"));
        pages.add(new Page("https://www.agroinform.hu/aprohirdetes", "hirdet"));
        pages.add(new Page("https://www.agroinform.hu/termenypiac", "kukorica"));

        for (Page page : pages) {

            driver.get(page.getUrl());

            String actualTitle = driver.getTitle();

            Assert.assertTrue(actualTitle.contains(page.getExpectedTitle()));
              
        }
    }

    @Test
    public void loginTest() {
        login();

    }

    @Test
    public void loginWrongPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(randomPassword());
        mainPage.clickLoginButton();
        Assert.assertTrue(mainPage.wrongPasswordAllertIsPresent());

    }

    @Test
    public void logoutTest() {
        MainPage mainPage = login();

        mainPage.clickLoginDropdown();
        mainPage.clickLogoutButton();

        Assert.assertTrue(mainPage.getBodyText().contains("BEJELENTKEZ"));
        
        
    }
    @Test
    public void searchNewsTest(){
        MainPage mainPage = login();

        NewsPage newsPage = mainPage.goToNewsPage();

        newsPage.setSearchTerm(searchTermNews);
        newsPage.clickSearch();

        Assert.assertTrue(newsPage.getBodyText().contains("kkentheted a termel"));

    }

    @Test
    public void marketPageTest(){
        MainPage mainPage = login();

        MarketPage marketPage = mainPage.goToMarketPage();

        marketPage.setSearchTerm(searchTermMarket);

        marketPage.clickSearch();

        Assert.assertTrue(marketPage.traktorHrefIsPresent());
    }

    @Test
    public void forumPageTest(){
        MainPage mainPage = login();

        ForumPage forumPage = mainPage.goToForumPage();

        forumPage.setSearchTerm(searchTermNews);

        forumPage.clickSearch();

        Assert.assertTrue(forumPage.getBodyText().contains("gyomirt"));
    }

    @Test
    public void navigateBackTest(){
        MainPage mainPage = login();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.agroinform.hu/kezdooldal"));
        
        ForumPage forumPage = mainPage.goToForumPage();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.agroinform.hu/forum"));

        driver.navigate().back();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.agroinform.hu/kezdooldal"));

    }

    @Test
    public void newMachineAdvertisementPageTest(){
        
        MainPage mainPage = login();

        mainPage.clickLoginDropdown();

        NewMachineAdvertisement newMachineAdvertisementPage  = mainPage.goToAdvertisementPage().goToNewAdvertisementPage().goToNewMachineAdvertisementPage();

        Assert.assertTrue(newMachineAdvertisementPage.getBodyText().contains("ALAPADATAI"));
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    private MainPage login(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

        return mainPage;
    }

    private String randomPassword(){
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for(int i=0; i< 10; i++){
            int random_char = rand.nextInt((122 - 97) + 1) + 97;
            sb.append(((char) random_char));
        }

        return sb.toString();

    }

   
}

