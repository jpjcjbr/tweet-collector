package br.com.itau.tweet.dto;

public class TweetCountByHashtagDTO {

	private String hashtag;
	private String language;
	private Integer count = 0;
	
	public TweetCountByHashtagDTO(String hashtag, String language, Integer count) {
		this.hashtag = hashtag;
		this.language = language;
		this.count = count;
	}
	
	public TweetCountByHashtagDTO() {
	}

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
