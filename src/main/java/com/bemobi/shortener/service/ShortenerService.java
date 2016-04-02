package com.bemobi.shortener.service;


import com.bemobi.shortener.persistence.Url;
import com.bemobi.shortener.persistence.UrlRepository;
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

    @Autowired
    private AliasService aliasService;

    private final int ATTEMPT_LIMIT = 3;

    public boolean upsert(String fullUrl, String alias) {
        return urlRepository.upsert(alias, fullUrl).isUpdateOfExisting();
    }


    private void save(String fullUrl) {

        String alias;
        Url url;

        int attemptCount = 0;

        do {
            alias = aliasService.generateAlias();
            url = urlRepository.findOne(alias);
            attemptCount++;
        } while (url != null && attemptCount < ATTEMPT_LIMIT);

        if (url == null) {
            url = new Url(alias, fullUrl);
            urlRepository.save(url);
        } else {
            throw new RuntimeException("Could not generate a new alias! Please try again");
        }

        return;
    }

    public void save(String fullUrl, Optional<String> alias) {

        if (!alias.isPresent()) {
            save(fullUrl);
            return;
        }

        boolean alreadyExists = alias
                .map(id -> urlRepository.findOne(id))
                .isPresent();

        if (!alreadyExists) {
            Url url = new Url(alias.get(), fullUrl);
            urlRepository.save(url);
            return;
        } else {
            throw new IllegalArgumentException("Given alias already exists!");
        }

    }

    public String find(String alias) {
        Url url = urlRepository.findOne(alias);
        String fullUrl = Optional.ofNullable(url)
                .map(Url::getFullUrl)
                .orElseThrow(() -> new IllegalArgumentException("Alias not found!"));
        return fullUrl;
    }
}
