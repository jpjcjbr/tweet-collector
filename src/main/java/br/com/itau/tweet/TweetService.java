package br.com.itau.tweet;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.itau.tweet.model.TopUsersByFollowerCount;
import br.com.itau.tweet.model.Tweet;
import br.com.itau.tweet.model.TweetCountByHashtagAndLanguage;
import br.com.itau.tweet.model.TweetCountByHour;
import br.com.itau.tweet.repository.TopUsersByFollowerCountRepository;
import br.com.itau.tweet.repository.TweetCountByHashtagAndLanguageRepository;
import br.com.itau.tweet.repository.TweetCountByHourRepository;
import br.com.itau.tweet.repository.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetIntegration tweetIntegration;
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TopUsersByFollowerCountRepository topUsersByFollowerCountRepository;
	
	@Autowired
	private TweetCountByHashtagAndLanguageRepository tweetCountByHashtagAndLanguageRepository;
	
	@Autowired
	private TweetCountByHourRepository tweetCountByHourRepository;
	
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

	public Collection<TopUsersByFollowerCount> getTopUsersByFollowersCount() {
		return Lists.newArrayList(topUsersByFollowerCountRepository.findAll()).stream()
				.sorted(sortByFollowersCountDescending())
				.collect(Collectors.toList());
	}
	
	public Collection<TweetCountByHashtagAndLanguage> getTweetCountByHashtag() {
		return Lists.newArrayList(tweetCountByHashtagAndLanguageRepository.findAll());
	}

	public Collection<TweetCountByHour> getTweetCountByHour() {
		return Lists.newArrayList(tweetCountByHourRepository.findAll()).stream()
				.sorted(sortByHourAndDayDescending())
				.collect(Collectors.toList());
	}

	private Comparator<? super TweetCountByHour> sortByHourAndDayDescending() {
		return (u1, u2) -> {
			int hourCompareResult = u2.getHour().compareTo(u1.getHour());
			
			if(hourCompareResult == 0) {
				return u2.getDate().compareTo(u1.getDate());
			}
			
			return hourCompareResult;
		};
	}

	private Comparator<? super TopUsersByFollowerCount> sortByFollowersCountDescending() {
		return (u1, u2) -> u2.getFollowersCount().compareTo(u1.getFollowersCount());
	}
	
}
