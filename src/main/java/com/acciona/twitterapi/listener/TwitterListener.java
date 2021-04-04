package com.acciona.twitterapi.listener;

import com.acciona.twitterapi.entity.TwitterData;
import com.acciona.twitterapi.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.Arrays;
import java.util.List;

public class TwitterListener implements StatusListener {
    
    final static List listValidLanguages = Arrays.asList("es", "fr", "it");
    
    @Autowired
    TwitterService twitterService;
    
    @Override
    public void onStatus(Status status) {
        //TODO lang is deprecated, use user account to check language
        if (listValidLanguages.contains(status.getUser().getLang())) {
    
            TwitterData twitterData = new TwitterData(status.getUser().getScreenName(), status.getText(), status.getGeoLocation());
            if (twitterService.addTwitterData(twitterData)) {
                twitterService.addHashTags(status.getHashtagEntities());
            }
            
            String twitterStream = status.getUser().getScreenName()
                    + "\t" + status.getText()
                    + "\t" + status.getGeoLocation();
            System.out.println(twitterStream);
        }
    }
    
    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
    
    }
    
    @Override
    public void onTrackLimitationNotice(int i) {
    
    }
    
    @Override
    public void onScrubGeo(long l, long l1) {
    
    }
    
    @Override
    public void onStallWarning(StallWarning stallWarning) {
    
    }
    
    @Override
    public void onException(Exception e) {
    
    }
}
