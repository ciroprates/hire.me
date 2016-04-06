package com.bemobi.shortener.persistence;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by ciroxavier on 3/27/16.
 */
public class Url {

    @Id
    private String alias;

    private String fullUrl;

    private Date creationDate;

    private int hitCount;

    public Url(String alias, String fullUrl) {
        this.alias = alias;
        this.fullUrl = fullUrl;
        this.creationDate = new Date();
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
