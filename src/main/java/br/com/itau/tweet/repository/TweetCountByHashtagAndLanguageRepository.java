package br.com.itau.tweet.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.tweet.model.TweetCountByHashtagAndLanguage;

public interface TweetCountByHashtagAndLanguageRepository extends CrudRepository<TweetCountByHashtagAndLanguage, String>{

}
