package com.acciona.twitterapi.web;

import com.acciona.twitterapi.entity.TwitterData;
import com.acciona.twitterapi.service.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TwitterController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(TwitterController.class);
    
    @Autowired
    TwitterService twitterService;
    
    @GetMapping
    public List<TwitterData> getTweets() {
        LOGGER.info("Retrieve all tweets");
        return twitterService.getTweets();
    }
    
    @PostMapping
    //TODO Control response codes from service
    public TwitterData validateTweet(@Valid @RequestBody TwitterData twitterData) {
        LOGGER.info("Validate tweet from" + twitterData.getName());
        twitterService.validateTweet(twitterData);
        LOGGER.info("Tweet validated");
        return twitterData;
    }
    
    @GetMapping("/validates")
    public List<TwitterData> getValidatedTweets() {
        LOGGER.info("Retrieve all validated Tweets");
        return twitterService.getValidatedTweets();
    }
    
    @GetMapping("/hashtags/{number}")
    public List<String> getHashtags(@Valid @PathVariable String number) {
        LOGGER.info(String.format("Retrieve top %s of hashTags", number));
        return twitterService.getTopNHashtags(number);
    }
    
}
