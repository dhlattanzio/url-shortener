package com.dhlattanzio.urlshortener.exception.dto;

import java.time.Instant;

public record ErrorDto(
        Instant timestamp,
        String message

) {}
