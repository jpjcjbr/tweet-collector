package br.com.itau.database;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Component
public class CreateSchema {
	
	private static final String KEYSPACE_REPLACE_TOKEN = "%keyspace%";

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keyspace;
	
	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;

	private static String CREATE_KEYSPACE = "create keyspace if not exists %keyspace% "
			+ "WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };";
	
	private static String USE_KEYSPACE = "use %keyspace%;";
	
	private static String CREATE_TABLE_TWEET = "create table IF NOT EXISTS tweet ("
			+ "id text, postdate timestamp, hashtag text, "
			+ "language text, message text, username text, "
			+ "followersCount int, PRIMARY KEY (id, hashtag));";
	
	private static String CREATE_TABLE_TOP_USERS = "create table IF NOT EXISTS "
			+ "top_users_by_follower_count (username text PRIMARY KEY, followersCount int);";

	private static String CREATE_TABLE_TWEET_COUNT_BY_HASHTAG = "create table IF NOT EXISTS "
			+ "tweet_count_by_hashtag_and_language (hashtag text, language text, count int, PRIMARY KEY (hashtag, language));";
	
	private String CREATE_TABLE_TWEET_COUNT_BY_HOUR = "create table IF NOT EXISTS "
			+ "tweet_count_by_hour_and_day (hour int, date timestamp, count int, PRIMARY KEY ((hour, date)));";
	
	@PostConstruct
	public void create() {
		Cluster cluster = Cluster.builder().addContactPoints(contactPoints).withPort(9042).build();

		Session session = cluster.connect();
		
		session.execute(CREATE_KEYSPACE.replaceAll(KEYSPACE_REPLACE_TOKEN, keyspace));
		
		session.execute(USE_KEYSPACE.replaceAll(KEYSPACE_REPLACE_TOKEN, keyspace));
		
		session.execute(CREATE_TABLE_TWEET);
		session.execute(CREATE_TABLE_TOP_USERS);
		session.execute(CREATE_TABLE_TWEET_COUNT_BY_HASHTAG);
		session.execute(CREATE_TABLE_TWEET_COUNT_BY_HOUR);

		session.close();
		cluster.close();
	}
}
