package com.bemobi.shortener.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by ciroxavier on 3/29/16.
 */
public interface UrlRepository extends MongoRepository<Url, String>, UrlRepositoryCustom {

    public Optional<Url> findTopByOrderByCreationDateDesc();
}
