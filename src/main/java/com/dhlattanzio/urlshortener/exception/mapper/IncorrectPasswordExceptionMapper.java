package com.dhlattanzio.urlshortener.exception.mapper;

import com.dhlattanzio.urlshortener.exception.dto.ErrorDto;
import com.dhlattanzio.urlshortener.exception.IncorrectPasswordException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class IncorrectPasswordExceptionMapper implements ExceptionMapper<IncorrectPasswordException> {
    @Override
    public Response toResponse(IncorrectPasswordException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDto(Instant.now(), e.getMessage()))
                .build();
    }
}
