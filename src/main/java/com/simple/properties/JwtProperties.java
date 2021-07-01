package com.simple.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt-constants")
public class JwtProperties {
    private final String secret;
    private final Duration accessExpirationDate;
    private final Duration refreshExpirationDate;
}
