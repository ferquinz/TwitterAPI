package com.acciona.twitterapi.service;

import com.acciona.twitterapi.entity.TwitterData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//TODO Implements all tests related to service methods
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TwitterServiceTests {
    
    @Autowired
    private TwitterService twitterService;
    
    @Test
    void getEmptyTweets() {
        List<TwitterData> twitterDataList = twitterService.getTweets();
        assertNotNull(twitterDataList);
        assertEquals(twitterDataList.size(), 0);
    }
}
