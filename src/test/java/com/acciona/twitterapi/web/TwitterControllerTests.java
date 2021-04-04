package com.acciona.twitterapi.web;

import com.acciona.twitterapi.service.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO Tests for the controller with mocked service
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TwitterControllerTests {
    
    private MockMvc mockMvc;
    
    @Mock
    private TwitterService twitterService;
    
    @InjectMocks
    private TwitterController twitterController;
    
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(twitterController).build();
    }
    
    @Test
    public void retrieveEmptyTweetsList() throws Exception {
        when(twitterService.getTweets()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
    

}
