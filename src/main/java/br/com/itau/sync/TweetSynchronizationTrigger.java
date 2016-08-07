package br.com.itau.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.itau.summarization.TweetSummarizationIntegration;

@Service
public class TweetSynchronizationTrigger {

	private static final int SYNCHRONIZATION_FREQUENCY = 1 * 30 * 1000;
	
	@Autowired
	private TweetSynchronizationService tweetSynchronizationService;
	
	@Autowired
	private TweetSummarizationIntegration tweetSummarizationIntegration; 

	@Scheduled(fixedRate = SYNCHRONIZATION_FREQUENCY)
	public void startSync() {
		tweetSynchronizationService.synchronize();
		tweetSummarizationIntegration.startSummarization();
	}
}
