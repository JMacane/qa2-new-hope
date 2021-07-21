package pageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;


public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");
    private final By BOOKMARK_ANONYMOUS = By.xpath(".//li[contains (@class, 'show-anon')]");
    private final By BOOKMARK_REGISTERED = By.xpath(".//li[contains (@class, 'show-reg')]");
    private final By COMMENTS_ON_COMMENT_PAGE = By.xpath(".//span[contains (@class, 'type-cnt')]");
    private final By TITLE_ON_COMMENT_PAGE = By.xpath(".//h1[contains(@class, 'article-title')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(TITLE);
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");

        if (baseFunc.findElements(COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }

    public ArticlePage openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc.click(COMMENTS);
        return null;
    }

    public int getCommentsCountFromCommentPage() {
        LOGGER.info("Getting article comments count from comments page");

        WebElement bookmarkAnonymous = baseFunc.findElements(BOOKMARK_ANONYMOUS).get(0);
        WebElement bookmarkRegistered = baseFunc.findElements(BOOKMARK_REGISTERED).get(0);

        int commentsInBookmarkAnonymous = 0;

        if (baseFunc.findElements(bookmarkAnonymous, COMMENTS_ON_COMMENT_PAGE).isEmpty()) {
            commentsInBookmarkAnonymous = 0;
        } else {
            String commentsCountToParse = baseFunc.getText(bookmarkAnonymous, COMMENTS_ON_COMMENT_PAGE);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            commentsInBookmarkAnonymous = Integer.parseInt(commentsCountToParse);
        }

        int commentsInBookmarkRegistered;
        if (baseFunc.findElements(bookmarkRegistered, COMMENTS_ON_COMMENT_PAGE).isEmpty()) {
            commentsInBookmarkRegistered = 0;
        } else {
            String commentsCountToParse = baseFunc.getText(bookmarkRegistered, COMMENTS_ON_COMMENT_PAGE);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            commentsInBookmarkRegistered = Integer.parseInt(commentsCountToParse);
        }

        int totalCommentCount = commentsInBookmarkAnonymous + commentsInBookmarkRegistered;
        LOGGER.info("Article comments count from comments page: " + totalCommentCount);
        return totalCommentCount;
    }

    public String getTitleFromCommentPage() {
        LOGGER.info("Getting article title from comment page");
        return baseFunc.getText(TITLE_ON_COMMENT_PAGE);
    }
}