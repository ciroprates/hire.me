package com.bemobi.shortener.controller;

import java.io.IOException;
import java.util.Optional;

import com.bemobi.shortener.persistence.Url;
import com.bemobi.shortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ShortenerController {

    @Autowired
    private ShortenerService shortenerService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Url shorten(@RequestParam(name = "url") String fullUrl,
                        @RequestParam Optional<String> alias,
                        HttpServletResponse response) {


        Url url = shortenerService.save(fullUrl, alias);
        response.setStatus(HttpStatus.CREATED.value());
        return url;

    }

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public void redirect(@PathVariable String alias, HttpServletResponse response) {

        String fullUrl = shortenerService.find(alias);

        try {
            response.sendRedirect(fullUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}