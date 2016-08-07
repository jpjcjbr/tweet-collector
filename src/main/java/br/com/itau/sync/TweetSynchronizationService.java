package br.com.itau.sync;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.itau.tweet.TweetService;

@Service
public class TweetSynchronizationService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TweetSynchronizationService.class);
	
	private Set<String> hashtags = Sets.newHashSet("brasil,brazil,brasil2016,brazil2016,jogosolimpicos,olimpiadas,olimpiadas2016,olympics,rio2016,riodejaneiro".split(","));
	
	@Autowired
	private TweetService tweetService;
	
	public void synchronize() {
		LOGGER.info("Clearing tweets table");
		
		Long startTime = System.currentTimeMillis();
		
		tweetService.clearAll();
		hashtags.stream().forEach(hashtag -> tweetService.saveTweetsByHashtag(hashtag));
		
		LOGGER.info("Tweets synchronization ended in {} milliseconds", (System.currentTimeMillis() - startTime));
	}
}
