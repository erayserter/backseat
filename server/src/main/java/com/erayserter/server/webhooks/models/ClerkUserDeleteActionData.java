package com.erayserter.server.webhooks.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ClerkUserDeleteActionData(String id) {
}
