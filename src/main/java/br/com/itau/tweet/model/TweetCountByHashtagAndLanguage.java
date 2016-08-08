package br.com.itau.tweet.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("tweet_count_by_hashtag_and_language")
public class TweetCountByHashtagAndLanguage {

	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String hashtag;
	
	@PrimaryKeyColumn(ordinal = 2)
	private String language;
	
	private Integer count;

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
