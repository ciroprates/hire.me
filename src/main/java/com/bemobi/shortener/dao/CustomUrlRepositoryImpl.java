package com.bemobi.shortener.dao;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by ciroxavier on 3/29/16.
 */
public class CustomUrlRepositoryImpl implements CustomUrlRepository {

    private final MongoOperations operations;

    @Autowired
    public CustomUrlRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public WriteResult upsert(String alias, String fullUrl) {
        return operations.upsert(query(where("_id").is(alias)),
                        update("fullUrl", fullUrl),
                        Url.class);
    }
}
