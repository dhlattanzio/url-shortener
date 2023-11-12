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
