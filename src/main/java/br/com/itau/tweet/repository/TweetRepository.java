package br.com.itau.tweet.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.tweet.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, String> {
}