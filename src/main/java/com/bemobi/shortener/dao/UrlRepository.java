package com.bemobi.shortener.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ciroxavier on 3/29/16.
 */
public interface UrlRepository extends MongoRepository<Url, String>, UrlRepositoryCustom {
}
