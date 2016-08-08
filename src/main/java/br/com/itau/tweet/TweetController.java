package br.com.itau.tweet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.tweet.dto.TweetCountByHashtagDTO;
import br.com.itau.tweet.dto.TweetCountByHourDTO;
import br.com.itau.tweet.dto.UserDTO;

@RestController
@RequestMapping("/tweets")
public class TweetController {

	@Autowired
	private TweetService tweetService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/top-users")
	public List<UserDTO> topUsers() {
		return tweetService.getTopUsersByFollowersCount().stream()
				.map(u -> new UserDTO(u.getUsername(), u.getFollowersCount()))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tweet-count-by-hashtag-and-language")
	public List<TweetCountByHashtagDTO> tweetCountByHashtagAndLanguage() {
		return tweetService.getTweetCountByHashtag().stream()
				.map(u -> new TweetCountByHashtagDTO(u.getHashtag(), u.getLanguage(), u.getCount()))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tweet-count-by-hour")
	public List<TweetCountByHourDTO> tweetCountByHour() {
		return tweetService.getTweetCountByHour().stream()
				.map(u -> new TweetCountByHourDTO(u.getHour(), u.getDate(), u.getCount()))
				.collect(Collectors.toList());
	}
	
}
