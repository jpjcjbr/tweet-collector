package br.com.itau.tweet;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.tweet.model.Tweet;
import br.com.itau.tweet.model.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetIntegration tweetIntegration;
	
	@Autowired
	private TweetRepository tweetRepository;
	
	public Collection<Tweet> getTweetsByHashtag(String hashtag) {
		return tweetIntegration.getTweetsByHashtag(hashtag);
	}
	
	public void saveTweets(Collection<Tweet> tweets) {
		tweetRepository.save(tweets);
	}

	public void saveTweetsByHashtag(String hashtag) {
		saveTweets(getTweetsByHashtag(hashtag));
	}

	public void clearAll() {
		tweetRepository.deleteAll();
	}
}
