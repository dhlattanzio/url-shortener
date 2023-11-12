package com.dhlattanzio.urlshortener.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface AppConfig {
    String name();
    String dns();
}