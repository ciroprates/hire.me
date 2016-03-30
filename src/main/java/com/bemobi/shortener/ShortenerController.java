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
    public String shorten(@RequestParam(name = "url") String fullUrl,
                        @RequestParam(required = false) String alias) {

        boolean isUpdatedOfExisting = shortenerService.upsert(fullUrl, alias);

        String message;
        if (isUpdatedOfExisting) {
            message = "Updated!";
        } else {
            message = "Inserted!";
        }

        return message;
    }

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public void redirect(@PathVariable String alias, HttpServletResponse response) {

        String fullUrl = shortenerService.find(alias);

        try {
            response.sendRedirect(fullUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}