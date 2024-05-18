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
                // Open the web page
            driver.get(page.getUrl());

                // Get the title of the page
            String actualTitle = driver.getTitle();

                // Verify the title
            Assert.assertTrue(actualTitle.contains(page.getExpectedTitle()));
              
        }
    }


    @Test
    public void loginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

    }
    @Test
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getTitle().contains("Agroinform - Mez"));

        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

        mainPage.clickLoginDropdown();
        mainPage.clickLogoutButton();

        Assert.assertTrue(mainPage.getBodyText().contains("BEJELENTKEZ"));

    }
    @Test
    public void searchNewsTest(){
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getTitle().contains("Agroinform - Mez"));

        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

        NewsPage newsPage = mainPage.goToNewsPage();

        newsPage.setSearchTerm(searchTermNews);
        newsPage.clickSearch();

        Assert.assertTrue(newsPage.getBodyText().contains("kkentheted a termel"));

    }

    @Test
    public void marketPageTest(){
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getTitle().contains("Agroinform - Mez"));

        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

        MarketPage marketPage = mainPage.goToMarketPage();

        marketPage.setSearchTerm(searchTermMarket);

        marketPage.clickSearch();

        Assert.assertTrue(marketPage.getBodyText().contains("JOHN DEERE 5085M traktor"));
    }

    @Test
    public void forumPageTest(){
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getTitle().contains("Agroinform - Mez"));

        mainPage.clickLoginDropdown();
        mainPage.setUsername(email);
        mainPage.setPassword(password);

        mainPage.clickLoginButton();

        Assert.assertTrue(mainPage.getBodyText().contains("az Agroinformon!"));

        ForumPage forumPage = mainPage.goToForumPage();

        forumPage.setSearchTerm(searchTermNews);

        forumPage.clickSearch();

        Assert.assertTrue(forumPage.getBodyText().contains("gyomirt"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

   
}
