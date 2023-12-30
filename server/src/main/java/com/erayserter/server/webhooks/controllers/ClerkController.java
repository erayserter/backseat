package com.erayserter.server.webhooks.controllers;

import com.erayserter.server.webhooks.services.ClerkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.svix.exceptions.WebhookVerificationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/webhooks/clerk")
public class ClerkController {

    private final ClerkService clerkService;

    public ClerkController(ClerkService clerkService) {
        this.clerkService = clerkService;
    }

    @PostMapping
    public void handleClerkWebhook(@RequestHeader("svix-id") String svixId,
                                   @RequestHeader("svix-timestamp") String svixTimestamp,
                                   @RequestHeader("svix-signature") String svixSignature,
                                   @RequestBody String payload) throws WebhookVerificationException, JsonProcessingException {
        clerkService.verifyWebhook(svixId, svixTimestamp, svixSignature, payload);
        clerkService.handleAction(payload);
    }
}
