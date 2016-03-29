package com.bemobi.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ciroxavier on 3/27/16.
 */

@Service
public class ShortenerService {

    @Autowired
    private ShortRepository shortRepository;

    public void save(String url, String alias) {
        Short aShort = new Short(alias, url);
        shortRepository.save(aShort);
    }


    public String find(String alias) {
        return shortRepository.findOne(alias).getUrl();
    }
}
