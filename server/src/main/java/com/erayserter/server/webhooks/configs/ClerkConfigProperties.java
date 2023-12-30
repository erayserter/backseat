package com.erayserter.server.webhooks.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("clerk")
public record ClerkConfigProperties(String webhookSecret) {

}
