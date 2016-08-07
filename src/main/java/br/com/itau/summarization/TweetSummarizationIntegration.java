package br.com.itau.summarization;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Sets;

@Service
public class TweetSummarizationIntegration {

	private RestTemplate restTemplate = new RestTemplate();
	
	public void startSummarization() {
		Set<String> urls = Sets.newHashSet(
				"http://localhost:8081/top-users", 
				"http://localhost:8081/tweet-count-by-hashtag-and-language", 
				"http://localhost:8081/tweet-count-by-hour"
		);
		
		urls.parallelStream().forEach(url -> restTemplate.getForObject(url, Void.class));
	}
}
