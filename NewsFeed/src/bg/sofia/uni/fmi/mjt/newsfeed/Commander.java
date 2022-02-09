package bg.sofia.uni.fmi.mjt.newsfeed;

import bg.sofia.uni.fmi.mjt.newsfeed.dto.ErrorDto;
import bg.sofia.uni.fmi.mjt.newsfeed.dto.NewsFeedDto;
import bg.sofia.uni.fmi.mjt.newsfeed.dto.ResponseDto;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.PageOutOfBoundsException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Commander {
    private static Commander instance;
    private final Gson gson;
    private Url url;
    private static final String helpMsg = """ 
            Format:
            exit - to end connection
            keyWords:{keyword}(,category:{category},country:{country},page:{1:3}) optional in brackets
            Tips: after you have requested a feed it is possible to call: "next" to get the next page
            """;

    private Commander() {
        gson = new Gson();
        url = null;
    }

    private String showResponse(HttpResponse<String> response) {
        ResponseDto dto;
        if (response.statusCode() != 200) {
            dto = gson.fromJson(response.body(), ErrorDto.class);
        } else {
            dto = gson.fromJson(response.body(), NewsFeedDto.class);
        }
        return dto.show();
    }

    private HttpResponse<String> getResponse() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create(url.toString()))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Map<String, String> parseInput(String input) throws URISyntaxException {
        Map<String, String> pi = new HashMap<>();
        for (var attr : input.split(",")) {
            String[] kvPair = attr.split(":");
            if (kvPair.length < 2)
                throw new URISyntaxException(attr, "Bad input format");
            pi.put(kvPair[0].toLowerCase(), kvPair[1]);
        }
        return pi;
    }

    private void composeUrl(Map<String, String> params) throws URISyntaxException {
        if (!params.containsKey("keywords"))
            throw new URISyntaxException("", "\"keywords\" is required field");
        Url.UrlBuilder ub = Url.builder(params.get("keywords"));
        if (params.containsKey("country"))
            ub.setCountry(Country.valueOf(params.get("country").toLowerCase()));
        if (params.containsKey("category"))
            ub.setCategory(Category.valueOf(params.get("category").toLowerCase()));
        if (params.containsKey("page"))
            ub.setPage(Integer.parseInt(params.get("page")));
        url = ub.build();
    }

    public static Commander getInstance() {
        if (instance == null)
            instance = new Commander();

        return instance;
    }

    public void run(InputStream in) {
        String userInput;
        Scanner sc = new Scanner(in);
        url = null;
        System.out.println(helpMsg);
        while (true) {
            userInput = sc.nextLine();

            if (userInput.equals("exit"))
                break;
            try {
                if (userInput.equals("next") && url != null) {
                    url.nextPage();
                } else {
                    var params = parseInput(userInput);
                    composeUrl(params);
                }
                var r = getResponse();
                System.out.println(showResponse(r));
            } catch (PageOutOfBoundsException | URISyntaxException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
