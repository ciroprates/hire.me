package com.bemobi.shortener.dao;

import com.mongodb.WriteResult;

/**
 * Created by ciroxavier on 3/29/16.
 */
public interface CustomUrlRepository {
    public WriteResult upsert(String alias, String fullUrl);
}
