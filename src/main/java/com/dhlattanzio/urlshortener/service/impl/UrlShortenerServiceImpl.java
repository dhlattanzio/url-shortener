package com.dhlattanzio.urlshortener.service.impl;

import com.dhlattanzio.urlshortener.entity.ShortenedUrl;
import com.dhlattanzio.urlshortener.exception.IncorrectPasswordException;
import com.dhlattanzio.urlshortener.exception.TagUsedException;
import com.dhlattanzio.urlshortener.resource.api.UrlShortenerResource;
import com.dhlattanzio.urlshortener.service.TagGenerator;
import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final TagGenerator tagGenerator;
    private final String password;
    private final UrlShortenerResource urlShortenerResource;

    public UrlShortenerServiceImpl(TagGenerator tagGenerator,
                                   @ConfigProperty(name = "app.password") String password, UrlShortenerResource urlShortenerResource) {
        this.tagGenerator = tagGenerator;
        this.password = password;
        this.urlShortenerResource = urlShortenerResource;
    }

    @Transactional
    @Override
    public String getUrlOf(String tag) {
        ShortenedUrl shortenedUrl = ShortenedUrl.findById(tag);
        shortenedUrl.hits = shortenedUrl.hits == null ? 1 : shortenedUrl.hits + 1;
        shortenedUrl.persist();

        return shortenedUrl.url;
    }

    @Transactional
    @Override
    public ShortenedUrl shortOne(String url, String tag, String password) {
        if (!this.password.equals(password)) {
            throw new IncorrectPasswordException("Password is incorrect");
        }

        if (tag != null && ShortenedUrl.findByIdOptional(tag).isPresent()) {
            throw new TagUsedException("Tag '%s' already used for another URL".formatted(tag));
        }

        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.tag = (tag != null) ? tag : tagGenerator.generateUnique();
        shortenedUrl.url = url;
        shortenedUrl.persist();

        return shortenedUrl;
    }

}
