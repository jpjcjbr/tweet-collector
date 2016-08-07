package br.com.itau.tweet;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import br.com.itau.tweet.model.Tweet;

@Component
public class TweetIntegration {

	private final Logger LOGGER = LoggerFactory.getLogger(TweetIntegration.class);
	
	private static final int RESULT_SIZE = 100;
	
	private Twitter twitter = new TwitterTemplate(
			"NtC07nDCX2WSpaealKX7C3M82", 
			"DAZyacehmwNnzG32QJcc2jXO5LhYGxoTQtWMmYzYgHn9Yr8pm5",
			"31326502-JW2wN5vBoG5gU4pQzxZkHRpUlthufVkP6QkdQR0d8",
			"28ysDCga2L91bOF80Ce4zpAXLEw4D8iAYtVnhyRNFN4ST"
	);

	public Collection<Tweet> getTweetsByHashtag(String hashtag) {
		LOGGER.debug("Getting tweets for hashtag={}", hashtag);
		
		SearchResults searchResults = twitter.searchOperations().search("#" + hashtag, RESULT_SIZE);

		LOGGER.debug("Found {} tweets for hashtag={}", searchResults.getTweets().size(), hashtag);
		
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
