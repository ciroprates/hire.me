package com.bemobi.shortener.dao;

import org.springframework.data.annotation.Id;

/**
 * Created by ciroxavier on 3/27/16.
 */
public class Url {

    @Id
    private String alias;

    private String fullUrl;


    public Url(String alias, String fullUrl) {
        this.alias = alias;
        this.fullUrl = fullUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
