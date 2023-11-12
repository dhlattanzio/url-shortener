package com.dhlattanzio.urlshortener.resource;

import com.dhlattanzio.urlshortener.dto.ShortUrlRequest;
import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/")
public class UrlShortenerResource {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerResource(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @POST
    @Path("/api/v1/shortener")
    public Response shortLink(ShortUrlRequest request) {
        var result = urlShortenerService.shortOne(request.url(), request.keyword());
        return Response.ok(result).build();
    }

    @GET
    @Path("/{keyword}")
    public Response redirect(@PathParam("keyword") String keyword) {
        var url = urlShortenerService.getUrlOf(keyword);
        return Response.status(Response.Status.FOUND).location(URI.create(url)).build();
    }

    @GET
    @Path("/{keyword}/qr")
    public Response getQR(@PathParam("keyword") String keyword) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
