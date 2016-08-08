package br.com.itau.tweet.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.tweet.model.TopUsersByFollowerCount;

public interface TopUsersByFollowerCountRepository extends CrudRepository<TopUsersByFollowerCount, String> {

}
