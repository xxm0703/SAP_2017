package bg.sofia.uni.fmi.mjt;

import bg.sofia.uni.fmi.mjt.newsfeed.*;

public class NewsFeedRetriever {

    public static void main(String[] args) {
        Commander cmd = Commander.getInstance();
        cmd.run(System.in);
    }
}
