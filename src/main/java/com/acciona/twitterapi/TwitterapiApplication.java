package com.acciona.twitterapi;

import com.acciona.twitterapi.listener.TwitterListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;


@SpringBootApplication
public class TwitterapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterapiApplication.class, args);
    
        TwitterListener twitterListener = new TwitterListener();
    
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterListener);
        
    }

}
