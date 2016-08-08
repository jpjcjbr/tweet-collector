package br.com.itau.tweet.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.tweet.model.TweetCountByHour;

public interface TweetCountByHourRepository extends CrudRepository<TweetCountByHour, String>{

}
