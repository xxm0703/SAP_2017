package bg.sofia.uni.fmi.mjt.newsfeed.dto;

public class ArticlesDto implements ResponseDto {
    public String title;
    public String description;
    public String url;
    public String publishedAt;
    public String content;

    public String show() {
        return String.format("\nTitle: %s", title) +
                String.format("\nDescription: %s", description) +
                String.format("\nContent: %s", content) +
                String.format("\nSource: %s", url) +
                String.format("\nPublished at: %s", publishedAt);
    }

}
