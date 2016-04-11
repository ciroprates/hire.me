package com.bemobi.shortener.service;

import com.bemobi.shortener.persistence.Url;
import com.bemobi.shortener.persistence.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ciroxavier on 4/10/16.
 */
@Service
public class RedirectService {

    @Autowired
    private UrlRepository urlRepository;

    public String find(String alias) {
        Url url = urlRepository.findOne(alias);
        String fullUrl = Optional.ofNullable(url)
                .map(Url::getFullUrl)
                .orElseThrow(() -> new IllegalArgumentException("Alias not found!"));
        return fullUrl;
    }

    public void incrementHitCount(String alias) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> findAndIncrement(alias));
        shutdown(executor);
    }

    private void findAndIncrement(String alias) {
        Url url = urlRepository.findOne(alias);
        int hitCount = url.getHitCount();
        url.setHitCount(++hitCount);
        urlRepository.save(url);
    }

    private void shutdown(ExecutorService executor) {
        try {
            // attempt to shutdown executor
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            // attempt to shutdown executor
        }
    }
}
