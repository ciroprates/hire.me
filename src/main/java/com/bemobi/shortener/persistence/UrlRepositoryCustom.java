package com.bemobi.shortener.persistence;

import com.mongodb.WriteResult;

/**
 * Created by ciroxavier on 3/29/16.
 */
public interface UrlRepositoryCustom {
    public WriteResult upsert(String alias, String fullUrl);
}
