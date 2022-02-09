package bg.sofia.uni.fmi.mjt.newsfeed;

import bg.sofia.uni.fmi.mjt.newsfeed.enums.Asset;

public class Url {
    private final String API_ENDPOINT = "https://rest.coinapi.io/v1/exchangerate/USD?";
    private final String API_KEY = "DC09B703-9478-4238-A69A-9B41D36DFEDC";
    // required parameters
    private final Asset asset;
    // optional parameters
    private final boolean invert;

    private Url(UrlBuilder builder) {
        asset = builder.asset;
        invert = builder.invert;
    }

    @Override
    public String toString() {
        var str = new StringBuilder(API_ENDPOINT)
                .append("apiKey=")
                .append(API_KEY)
                .append("&filter_asset_id=")
                .append(asset.name())
                .append("&invert=")
                .append(invert);
        return str.toString();
    }

    public static UrlBuilder builder(Asset asset) {
        return new UrlBuilder(asset);
    }


    // Builder Class
    public static class UrlBuilder {

        // required parameters
        private final Asset asset;

        // optional parameters
        private boolean invert = true;

        private UrlBuilder(Asset asset) {
            this.asset = asset;
        }

        public void setInvert(boolean invert) {
            this.invert = invert;
        }

        public Url build() {
            return new Url(this);
        }

    }

}
