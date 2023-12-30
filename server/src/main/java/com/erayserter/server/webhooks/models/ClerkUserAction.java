package com.erayserter.server.webhooks.models;


public record ClerkUserAction(
        String type,
        String object,
        Object data
) {}
