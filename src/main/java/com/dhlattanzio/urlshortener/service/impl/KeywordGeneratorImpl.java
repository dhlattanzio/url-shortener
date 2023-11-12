package com.dhlattanzio.urlshortener.service.impl;

import com.dhlattanzio.urlshortener.service.KeywordGenerator;
import jakarta.enterprise.context.ApplicationScoped;

import java.security.SecureRandom;

@ApplicationScoped
public class KeywordGeneratorImpl implements KeywordGenerator {
    private static final String BASE_62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    @Override
    public String generateUnique() {
        StringBuilder sb = new StringBuilder(4);

        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(BASE_62_CHARS.length());
            sb.append(BASE_62_CHARS.charAt(randomIndex));
        }

        return sb.toString();
    }
}
