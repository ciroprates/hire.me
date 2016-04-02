package com.bemobi.shortener.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by ciroxavier on 3/30/16.
 */
@Service
public class AliasService {

    private final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final Random RANDOM = new Random();

    private final int ALIAS_SIZE = 6;

    public String generateAlias() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALIAS_SIZE; i++) {
            int charIndex = RANDOM.nextInt(ALPHANUMERIC.length() - 1);
            sb.append(ALPHANUMERIC.toCharArray()[charIndex]);
        }

        return sb.toString();
    }

    public static String buildAlphanumericString() {
        IntStream alphaStream = IntStream.concat(IntStream.rangeClosed(65, 90), IntStream.rangeClosed(97, 122));
        IntStream alphaNumericStream = IntStream.concat(alphaStream, IntStream.rangeClosed(48, 57));
        Stream<String> stringStream = alphaNumericStream.mapToObj(a -> String.valueOf((char) a));
        return stringStream.reduce(String::concat).get();
    }

}
