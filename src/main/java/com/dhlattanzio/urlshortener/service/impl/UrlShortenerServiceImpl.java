package com.dhlattanzio.urlshortener.service.impl;

import com.dhlattanzio.urlshortener.entity.ShortenedUrl;
import com.dhlattanzio.urlshortener.service.KeywordGenerator;
import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final KeywordGenerator keywordGenerator;

    public UrlShortenerServiceImpl(KeywordGenerator keywordGenerator) {
        this.keywordGenerator = keywordGenerator;
    }

    @Transactional
    @Override
    public String getUrlOf(String keyword) {
        ShortenedUrl shortenedUrl = ShortenedUrl.findById(keyword);
        shortenedUrl.hits = shortenedUrl.hits == null ? 1 : shortenedUrl.hits + 1;
        shortenedUrl.persist();

        return shortenedUrl.url;
    }

    @Transactional
    @Override
    public ShortenedUrl shortOne(String url, String keyword) {
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.keyword = (keyword != null) ? keyword : keywordGenerator.generateUnique();
        shortenedUrl.url = url;
        shortenedUrl.persist();

        return shortenedUrl;
    }

}
