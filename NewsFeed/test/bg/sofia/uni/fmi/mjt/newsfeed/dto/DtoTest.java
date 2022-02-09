package bg.sofia.uni.fmi.mjt.newsfeed.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {
    @Test
    void error() {
        var err = new ErrorDto();
        err.status = "400";
        err.code = "apiKeyMissing";
        err.message = "User error";
        assertTrue(err.show().contains("Error"));
    }

    @Test
    void newsFeed() {
        var newsFeed = new NewsFeedDto();
        newsFeed.articles = new ArrayList();
        assertTrue(newsFeed.show().contains("Total results: "));
    }

    @Test
    void article() {
        var article = new ArticlesDto();
        assertTrue(article.show().contains("Title: "));
    }
}
