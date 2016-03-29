package com.bemobi.shortener;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ShortenerController {

    @Autowired
    private ShortenerService shortenerService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void shorten(@RequestParam String url,
                        @RequestParam(required = false) String alias) {

        shortenerService.save(url, alias);

        return;
    }

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public void redirect(@PathVariable String alias, HttpServletResponse response) {

        System.out.printf(alias);

        String url = shortenerService.find(alias);

        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}