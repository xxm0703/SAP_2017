package bg.sofia.uni.fmi.mjt.newsfeed.dto;

import java.util.List;

public class NewsFeedDto implements ResponseDto {
    public String status;
    public int totalResults;
    public List<ArticlesDto> articles;
    private static final String LINE_SEPARATOR = "\n--------------------";

    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total results: ").append(totalResults);
        for (var news : articles) {
            sb.append(news.show()).append(LINE_SEPARATOR);
        }
        return sb.toString();
    }
}
