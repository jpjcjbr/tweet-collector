package br.com.itau.tweet.dto;

import java.util.Date;

public class TweetCountByHourDTO {

	private int hour;
	private Date timestamp;
	private Integer count = 0;
	
	public TweetCountByHourDTO(int hour, Date timestamp, Integer count) {
		this.hour = hour;
		this.timestamp = timestamp;
		this.count = count;
	}
	
	public TweetCountByHourDTO() {
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
