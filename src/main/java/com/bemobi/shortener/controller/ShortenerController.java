package com.bemobi.shortener.controller;

import java.io.IOException;
import java.util.Optional;

import com.bemobi.shortener.persistence.Url;
import com.bemobi.shortener.service.RedirectService;
import com.bemobi.shortener.service.ShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ShortenerController {

    @Autowired
    private ShortenService shortenService;

    @Autowired
    private RedirectService redirectService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Url shorten(@RequestParam(name = "url") String fullUrl,
                       @RequestParam Optional<String> alias,
                       HttpServletResponse response) {


        Url url = shortenService.shorten(fullUrl, alias);
        response.setStatus(HttpStatus.CREATED.value());
        return url;

    }

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public void redirect(@PathVariable String alias, HttpServletResponse response) throws IOException {

        String fullUrl = redirectService.find(alias);

        if (!Optional.ofNullable(fullUrl).isPresent()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        response.sendRedirect(fullUrl);
        redirectService.incrementHitCount(alias);
    }
}