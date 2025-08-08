package com.dhlattanzio.urlshortener.resource.api;

import com.dhlattanzio.urlshortener.dto.ShortUrlRequest;
import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class UrlShortenerResource {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerResource(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @POST
    public Response shortLink(@Valid ShortUrlRequest request) {
        var result = urlShortenerService.shortOne(request.url(), request.tag(), request.password());
        return Response.ok(result).build();
    }
}
