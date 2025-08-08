package com.dhlattanzio.urlshortener.service;

import com.dhlattanzio.urlshortener.entity.ShortenedUrl;

public interface UrlShortenerService {
    String getUrlOf(String tag);
    ShortenedUrl shortOne(String url, String tag, String password);
}
