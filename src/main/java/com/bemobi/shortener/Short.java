package com.bemobi.shortener;

import org.springframework.data.annotation.Id;

/**
 * Created by ciroxavier on 3/27/16.
 */
public class Short {

    @Id
    private String alias;

    private String url;


    public Short(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
