package com.bemobi.shortener.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String>, CustomUrlRepository {


}