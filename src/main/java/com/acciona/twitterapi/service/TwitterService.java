package com.acciona.twitterapi.service;

import com.acciona.twitterapi.entity.TwitterData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.HashtagEntity;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

@Service
public class TwitterService {
    
    public static final String DEFAULT_TOP_NUMBER = "10";
    @Value("${min.user.followers}")
    private int minUserFollowers;
    
    private List<TwitterData> twitterDataList = new ArrayList<>();
    private TreeMap<String, Integer> hashTags = new TreeMap<>();
    private Twitter twitter = new TwitterFactory().getInstance();
    private final long cursor = -1;
    private final Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);
    
    public boolean addTwitterData(TwitterData twitterData) {
        IDs followers;
        try {
            followers = twitter.getFollowersIDs(twitterData.getName(), cursor);
            if (followers.getIDs().length > minUserFollowers) {
                twitterDataList.add(twitterData);
                return true;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<TwitterData> getTweets() {
        return twitterDataList;
    }
    
    public void validateTweet(TwitterData twitterData) {
        //TODO Validate Tweet
    }
    
    public List<TwitterData> getValidatedTweets() {
        //TODO Get all validated tweets
        return new ArrayList<>();
    }
    
    public boolean addHashTags(HashtagEntity[] hashtagEntities) {
        if (hashtagEntities != null && hashtagEntities.length > 0) {
            for (HashtagEntity hashtagEntity : hashtagEntities) {
                if (hashTags.containsKey(hashtagEntity.getText())) {
                    hashTags.put(hashtagEntity.getText(), hashTags.get(hashtagEntity.getText()) + 1);
                } else {
                    hashTags.put(hashtagEntity.getText(), 1);
                }
            }
            return true;
        }
        return false;
    }
    
    public List<String> getTopNHashtags(String number) {
        if (number == null || !StringUtils.isNumeric(number)) {
            LOGGER.debug("Use default value for top N HashTags");
            number = DEFAULT_TOP_NUMBER;
        }
        Integer top = Integer.parseInt(number);
        PriorityQueue<String> topN = new PriorityQueue<String>(top, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Double.compare(hashTags.get(s1), hashTags.get(s2));
            }
        });
    
        for(String key:hashTags.keySet()){
            if (topN.size() < top)
                topN.add(key);
            else if (hashTags.get(topN.peek()) < hashTags.get(key)) {
                topN.poll();
                topN.add(key);
            }
        }
        return (List) Arrays.asList(topN.toArray());
        
    }
}
