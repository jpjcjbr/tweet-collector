package br.com.itau.summarization;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Sets;

@Service
public class TweetSummarizationIntegration {

	private final Logger LOGGER = LoggerFactory.getLogger(TweetSummarizationIntegration.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${summarizer.url}")
	private String baseUrl;
	
	public void startSummarization() {
		LOGGER.info("Calling summarization module");
		
		Set<String> urls = Sets.newHashSet(
				baseUrl + "/summarizarion/top-users", 
				baseUrl + "/summarizarion/tweet-count-by-hashtag-and-language", 
				baseUrl + "/summarizarion/tweet-count-by-hour"
		);
		
		urls.parallelStream().forEach(url -> restTemplate.getForObject(url, Void.class));
		
		LOGGER.info("Summarization ended");
	}
}
