package com.dhlattanzio.urlshortener.resource;

import com.dhlattanzio.urlshortener.service.UrlShortenerService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/")
public class RedirectResource {
    private final UrlShortenerService urlShortenerService;

    public RedirectResource(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GET
    @Path("/{tag}")
    public Response redirect(@PathParam("tag") String tag) {
        var url = urlShortenerService.getUrlOf(tag);
        return Response.status(Response.Status.FOUND).location(URI.create(url)).build();
    }

    @GET
    @Path("/{tag}/qr")
    public Response getQR(@PathParam("tag") String tag) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
