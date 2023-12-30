package com.erayserter.server.webhooks.services;

import com.erayserter.server.authentication.models.User;
import com.erayserter.server.authentication.repositories.UserRepository;
import com.erayserter.server.webhooks.configs.ClerkConfigProperties;
import com.erayserter.server.webhooks.models.ClerkUserAction;
import com.erayserter.server.webhooks.models.ClerkUserDeleteActionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svix.Webhook;
import com.svix.exceptions.WebhookVerificationException;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;

import static com.erayserter.server.webhooks.utils.ClerkUtils.getWebhookHeaders;

@Service
public class ClerkService {
    private final ClerkConfigProperties clerkConfigProperties;
    private final UserRepository userRepository;

    public ClerkService(ClerkConfigProperties clerkConfigProperties, UserRepository userRepository) {
        this.clerkConfigProperties = clerkConfigProperties;
        this.userRepository = userRepository;
    }

    public void verifyWebhook(String svixId, String svixTimestamp, String svixSignature, String payload) throws WebhookVerificationException {
        HttpHeaders headers = getWebhookHeaders(svixId, svixTimestamp, svixSignature);

        Webhook webhook = new Webhook(clerkConfigProperties.webhookSecret());

        webhook.verify(payload, headers);
    }

    public void handleAction(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ClerkUserAction clerkUserAction = mapper.readValue(payload, ClerkUserAction.class);

        switch (clerkUserAction.type()) {
            case "user.created", "user.updated" -> {
                User user = mapper.convertValue(clerkUserAction.data(), User.class);
                userRepository.save(user);
            }
            case "user.deleted" -> {
                ClerkUserDeleteActionData deleteActionData = mapper.convertValue(clerkUserAction.data(), ClerkUserDeleteActionData.class);
                User user = userRepository.findById(deleteActionData.id()).orElseThrow();
                userRepository.delete(user);
            }
            default -> System.out.println("Unknown action type: " + clerkUserAction.type());
        }
    }
}
