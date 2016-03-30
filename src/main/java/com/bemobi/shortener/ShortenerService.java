package com.bemobi.shortener;


import com.bemobi.shortener.dao.Url;
import com.bemobi.shortener.dao.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ciroxavier on 3/27/16.
 */

@Service
public class ShortenerService {

    @Autowired
    private UrlRepository urlRepository;

    public boolean upsert(String fullUrl, String alias) {
        return urlRepository.upsert(alias, fullUrl).isUpdateOfExisting();
    }


    public String find(String alias) {
        Url url = urlRepository.findOne(alias);
        String fullUrl = Optional.ofNullable(url)
                .map(Url::getFullUrl)
                .orElseThrow(() -> new IllegalArgumentException("Alias not found!"));
        return fullUrl;
    }
}
