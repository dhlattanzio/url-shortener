package com.dhlattanzio.urlshortener.exception.mapper;

import com.dhlattanzio.urlshortener.exception.TagUsedException;
import com.dhlattanzio.urlshortener.exception.dto.ErrorDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class TagUsedExceptionMapper implements ExceptionMapper<TagUsedException> {
    @Override
    public Response toResponse(TagUsedException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDto(Instant.now(), e.getMessage()))
                .build();
    }
}
