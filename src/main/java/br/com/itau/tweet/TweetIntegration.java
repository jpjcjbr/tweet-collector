package br.com.itau.tweet;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import br.com.itau.tweet.model.Tweet;

@Component
public class TweetIntegration {

	private final Logger LOGGER = LoggerFactory.getLogger(TweetIntegration.class);
	
	private static final int RESULT_SIZE = 100;
	
	@Value("${twitter.consumer.key}")
	private String consumerKey;
	
	@Value("${twitter.consumer.secret}")
	private String consumerSecret;
	
	@Value("${twitter.access.token}")
	private String accessToken;
	
	@Value("${twitter.access.token.secret}")
	private String accessTokenSecret;
	
	private Twitter twitter;
	
	@PostConstruct
	public void init() {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	public Collection<Tweet> getTweetsByHashtag(String hashtag) {
		LOGGER.info("Getting tweets for hashtag={}", hashtag);
		
		SearchResults searchResults = twitter.searchOperations().search("#" + hashtag, RESULT_SIZE);

		LOGGER.info("Found {} tweets for hashtag={}", searchResults.getTweets().size(), hashtag);
		
		return searchResults.getTweets().stream().map(originalTweet -> createTweet(originalTweet, hashtag)).collect(Collectors.toList());
	}

	private Tweet createTweet(org.springframework.social.twitter.api.Tweet originalTweet, String hashtag) {
		Tweet tweet = new Tweet();
		
		tweet.setId(originalTweet.getIdStr());
		tweet.setLanguage(originalTweet.getLanguageCode());
		tweet.setPostDate(originalTweet.getCreatedAt());
		tweet.setHashtag(hashtag);
		tweet.setUsername(originalTweet.getUser().getScreenName());
		tweet.setFollowersCount(originalTweet.getUser().getFollowersCount());
		tweet.setMessage(originalTweet.getText());
		
		return tweet;
	}
}
