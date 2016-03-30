package com.bemobi.shortener.dao;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ciroxavier on 3/29/16.
 */
public interface UrlRepositoryCustom {
    public WriteResult upsert(String alias, String fullUrl);
}
