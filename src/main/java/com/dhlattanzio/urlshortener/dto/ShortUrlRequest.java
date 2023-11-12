package com.dhlattanzio.urlshortener.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ShortUrlRequest(
        @NotEmpty(message = "URL cannot be empty")
        String url,

        @Pattern(regexp = "^[a-zA-Z0-9]$", message = "Keyword must be alphanumeric")
        String keyword
) {}
