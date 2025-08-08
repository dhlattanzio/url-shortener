package com.dhlattanzio.urlshortener.exception.mapper;

import com.dhlattanzio.urlshortener.exception.dto.ErrorDto;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDto(Instant.now(), exception.getMessage()))
                .build();
    }
}
