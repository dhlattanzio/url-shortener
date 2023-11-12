package com.dhlattanzio.urlshortener.service;

import com.dhlattanzio.urlshortener.entity.ShortenedUrl;

public interface UrlShortenerService {
    String getUrlOf(String keyword);
    ShortenedUrl shortOne(String url, String keyword);
}
