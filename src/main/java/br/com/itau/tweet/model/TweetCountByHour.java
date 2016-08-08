package br.com.itau.tweet.model;

import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("tweet_count_by_hour_and_day")
public class TweetCountByHour {

	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private Integer hour;
	
	@PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private Date date;
	
	private Integer count;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
