import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiArticleCommentsTest {

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");

    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private final Logger LOGGER = LogManager.getLogger (DelfiArticleCommentsTest.class);

    private WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info ("This test is checking titles and comments count on home/article/comments pages");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");

        LOGGER.info ("Open browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.delfi.lv/");

        //------HOME PAGE ------------
        LOGGER.info ("Close cookie modal window");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));


        driver.findElement(ACCEPT_COOKIES_BTN).click();

        LOGGER.info ("Find article number 6");
        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(5);

        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText();
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

        LOGGER.info ("Click home page title");
        article.findElement(HOME_PAGE_TITLE).click();

        //------ARTICLE PAGE ------------
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();

        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

    }


    private int getCommentsCount(By locator) {
        int commentsCount = 0;
        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }
        return commentsCount;

    }

    private int getCommentsCount(WebElement we, By locator) {
        int commentsCount = 0;
        if (!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }
        return commentsCount;
    }


    private int removeBrackets (WebElement we){
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1);
        return  Integer.parseInt(commentsCountText);
    }


    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}
