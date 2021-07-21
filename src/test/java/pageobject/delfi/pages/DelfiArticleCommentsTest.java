package pageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.BaseFunc;


public class DelfiArticleCommentsTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 2;

    private BaseFunc baseFunc;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        baseFunc = new BaseFunc();
        baseFunc.openPage("delfi.lv");

        //---------------------------HOME PAGE-----------------------------------------------------------
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();

        String homePageTitle = homePage.getTitle(ARTICLE_ID);
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);

        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

        //-------------------------ARTICLE PAGE----------------------------------------------------------
        //       ArticlePage articlePage = new ArticlePage(baseFunc);

        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title on article page!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count on article page!");

        articlePage.openCommentsPage();

        //-------------------------COMMENTS PAGE---------------------------------------------------------
        int commentPageCommentCount = articlePage.getCommentsCountFromCommentPage();
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentCount, "Wrong comment count on comment page!");

        String commentPageTitle = articlePage.getTitleFromCommentPage();
        Assertions.assertEquals(homePageTitle, commentPageTitle, "Wrong title on comment page!");

    }

    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}