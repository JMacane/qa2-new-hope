package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE = By.tagName("article");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void acceptCookies() {
        LOGGER.info("Accepting cookies");
        baseFunc.click(ACCEPT_COOKIE_BTN);
    }

    public WebElement getArticleById(int id) {
        LOGGER.info("Getting article Nr." + (id + 1));
        List<WebElement> articles = baseFunc.findElements(ARTICLE);

        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");

        return articles.get(id);
    }

    public String getTitle(int id) {
        LOGGER.info("Getting title for article Nr.: " + (id + 1));
        return baseFunc.getText(getArticleById(id), TITLE);
    }

    public int getCommentsCount(int id) {
        LOGGER.info("Getting comment count for article Nr." + (id + 1));

        if (baseFunc.findElements(getArticleById(id), COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(getArticleById(id), COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }

    public ArticlePage openArticle(int id) {
        LOGGER.info("Opening article Nr." + (id + 1));
        baseFunc.click(getArticleById(id));
        return new ArticlePage(baseFunc);
    }
}