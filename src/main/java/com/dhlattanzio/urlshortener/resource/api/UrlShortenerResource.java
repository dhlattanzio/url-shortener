package com.dhlattanzio.urlshortener.resource.api;

import com.dhlattanzio.urlshortener.dto.ShortUrlRequest;
import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/api/v1/shortener")
public class UrlShortenerResource {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerResource(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @POST
    public Response shortLink(ShortUrlRequest request) {
        var result = urlShortenerService.shortOne(request.url(), request.keyword());
        return Response.ok(result).build();
    }
}
