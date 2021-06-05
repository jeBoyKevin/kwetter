package com.kwetter.tweetservice;

import com.kwetter.tweetservice.dal.repository.TweetRepository;
import com.kwetter.tweetservice.models.Tweet;
import com.kwetter.tweetservice.models.returnModels.GetTweetsFromReturnModel;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
class TweetserviceApplicationTests {


    @Configuration
    static class ContextConfiguration {
        @Bean
        public TweetRepository tweetRepository() {
            TweetRepository tweetRepository = new TweetRepository(true);

            return tweetRepository;
        }
    }
    @Autowired
    private TweetRepository tweetRepository;

    @Test
    void testGetTweetsFromUser() {
        GetTweetsFromReturnModel returnModel = tweetRepository.getTweetsFromUser(0);

        Tweet expected = new Tweet();

        expected.setTweet_id(0);
        expected.setUser_id(0);
        expected.setDate("01-06-2021");
        expected.setLikes(5);
        expected.setMessage("Dit is een test tweet");

        Tweet actual = returnModel.getTweets().get(0);

        assertThat(actual)
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    void testSendTweet() {
        SendTweetReturnModel returnModel = tweetRepository.sendTweet(0, "Dit is een test tweet");

        boolean expected = true;

        boolean actual = returnModel.isSuccess();

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteTweet() {
        SendTweetReturnModel returnModel = tweetRepository.deleteTweet(0);

        boolean expected = true;

        boolean actual = returnModel.isSuccess();

        assertEquals(expected, actual);
    }

//    @Test
//    void testGetMentions() {
//        String returnModel = tweetRepository.getMentions();
//
//        String expected = "";
//
//        String actual = returnModel;
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void testLikeTweet() {
//        String returnModel = tweetRepository.likeTweet();
//
//        String expected = "";
//
//        String actual = returnModel;
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void testGetTweets() {
//        String returnModel = tweetRepository.getTweets();
//
//        String expected = "";
//
//        String actual = returnModel;
//
//        assertEquals(expected, actual);
//    }



}
