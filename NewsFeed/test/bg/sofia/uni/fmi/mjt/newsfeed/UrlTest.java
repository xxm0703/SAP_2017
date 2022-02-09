package bg.sofia.uni.fmi.mjt.newsfeed;

import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.PageOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UrlTest {
    private Url.UrlBuilder ub;

    @BeforeEach
    private void setUp() {
        ub = Url.builder("random");
    }

    @Test
    void pages() {
        assertDoesNotThrow(() -> ub.setPage(2));
        assertEquals(ub.build().getPage(), 2);
        var url = ub.build();
        assertDoesNotThrow(url::nextPage);
        assertEquals(url.getPage(), 3);
        assertThrows(PageOutOfBoundsException.class, () -> ub.setPage(4));
        assertEquals(ub.build().getPage(), 2);
    }

    @Test
    void country() {
        assertDoesNotThrow(() -> ub.setCountry(Country.co));
        assertEquals(ub.build().getCountry(), Country.co);
        assertEquals(Country.co.toString(), "&country=co");
    }

    @Test
    void category() {
        assertDoesNotThrow(() -> ub.setCategory(Category.sports));
        assertEquals(ub.build().getCategory(), Category.sports);
        assertEquals(Category.sports.toString(), "&category=sports");
    }

    @Test
    void keyWords() {
        assertEquals(ub.build().getKeyWords(), "random");
    }
    @Test
    void composeString() {
        String expectedUrl = "https://newsapi.org/v2/top-headlines?pageSize=50" +
                "&apiKey=0cde5385fb34480d87688f0b9267e8cb&q=random&page=1";
        assertEquals(ub.build().toString(),expectedUrl);
    }
}
