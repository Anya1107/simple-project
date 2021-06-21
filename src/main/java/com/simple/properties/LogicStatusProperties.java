package com.simple.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "logic-status")
public class LogicStatusProperties {
    private final Map<String, String> logicStatuses;
    private final String defaultMessage;
}
