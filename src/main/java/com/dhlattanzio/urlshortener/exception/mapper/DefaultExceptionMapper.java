package com.dhlattanzio.urlshortener.exception.mapper;

import com.dhlattanzio.urlshortener.exception.dto.ErrorDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        log.error("An unexpected error occurred", e);
        return Response.serverError()
                .entity(new ErrorDto(Instant.now(), "An unexpected error occurred"))
                .build();
    }
}
