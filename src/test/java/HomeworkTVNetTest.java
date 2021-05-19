import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeworkTVNetTest {

    //COOKIES + DISPLAY BLOCK
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By DISPLAY_BLOCK_REMOVE = By.xpath(".//*[contains(@style, 'display:block')]");

    //ARTICLES
    private final By ARTICLE = By.xpath(".//*[contains(@class, 'list-article__text')]");
    private final By TITLE_OF_CURRENT_ARTICLE = By.xpath(".//*[contains(@itemprop, 'headline name')]");
    private final By COUNT_OF_COMMENTS_TO_CURRENT_ARTICLE = By.xpath(".//*[contains(@class, 'list-article__comment section')]");
    private final By COMMENTS_TO_CURRENT_ARTICLE = By.xpath(".//*[contains(@class, 'article-share__item--comments')]");

    //TVNET LOGO + RUS LANGUAGE
    private final By TVNET_LOGO = By.xpath(".//*[contains(@class, 'flex header-logo')]");
    private final By LANGUAGE_RUS = By.xpath(".//*[contains(text(), 'RUS')]");


    @Test
    public void openCommentsOfFirstArticle() {
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://www.tvnet.lv/");

        WebDriverWait wait = new WebDriverWait(browserWindow, 10);

        List<WebElement> elements = browserWindow.findElements(ACCEPT_COOKIES_BTN);

        if (elements.size() != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));
            browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        }

        elements = browserWindow.findElements(DISPLAY_BLOCK_REMOVE);

        if (elements.size() != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(DISPLAY_BLOCK_REMOVE));
            browserWindow.findElement(DISPLAY_BLOCK_REMOVE).click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE));
        browserWindow.findElement(ARTICLE).click();

        elements = browserWindow.findElements(ACCEPT_COOKIES_BTN);

        if (elements.size() != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
            browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        }

        elements = browserWindow.findElements(DISPLAY_BLOCK_REMOVE);

        if (elements.size() != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(DISPLAY_BLOCK_REMOVE));
            browserWindow.findElement(DISPLAY_BLOCK_REMOVE).click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(COMMENTS_TO_CURRENT_ARTICLE));
        browserWindow.findElement(COMMENTS_TO_CURRENT_ARTICLE).click();
    }

    @Test
    public void titleOfFirstArticle() {
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://www.tvnet.lv/");

        WebDriverWait wait = new WebDriverWait(browserWindow, 10);

        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE));
        System.out.println("Title of first article: " + browserWindow.findElement(ARTICLE).findElement(TITLE_OF_CURRENT_ARTICLE).getText());

        browserWindow.close();
    }

    @Test
    public void titlesOfAllArticlesWithoutComments() {
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://www.tvnet.lv/");

        WebDriverWait wait = new WebDriverWait(browserWindow, 10);

        List<WebElement> article = browserWindow.findElements(ARTICLE);

        for (int i = 0; i < article.size(); i++) {
            System.out.println("Title of article " + i + ": " + browserWindow.findElements(ARTICLE).get(i).findElement(TITLE_OF_CURRENT_ARTICLE).getText());
        }

        browserWindow.close();
    }

    @Test
    public void titlesOfAllArticlesWithComments() {
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://www.tvnet.lv/");

        WebDriverWait wait = new WebDriverWait(browserWindow, 10);

        List<WebElement> article = browserWindow.findElements(ARTICLE);

        for (int i = 0; i < article.size(); i++) {
            System.out.println("Title of article " + i + ": " + browserWindow.findElements(ARTICLE).get(i).findElement(TITLE_OF_CURRENT_ARTICLE).getText());

            List<WebElement> commentsToCurrentArticle = browserWindow.findElements(ARTICLE).get(i).findElements(COUNT_OF_COMMENTS_TO_CURRENT_ARTICLE);

            if (commentsToCurrentArticle.size() == 0)
                System.out.println("Article without comments");
            else
                System.out.println("Comments to article " + i + ": " + browserWindow.findElements(ARTICLE).get(i).findElement(COUNT_OF_COMMENTS_TO_CURRENT_ARTICLE).getText());
        }

        browserWindow.close();
    }
}
