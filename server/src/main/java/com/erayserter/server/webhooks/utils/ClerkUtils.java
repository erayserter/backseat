package com.erayserter.server.webhooks.utils;

import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClerkUtils {
    public static HttpHeaders getWebhookHeaders(String svixId,
                                                String svixTimestamp,
                                                String svixSignature) {
        Map<String, List<String>> headerMap = new HashMap<>();
        headerMap.put("svix-id", Collections.singletonList(svixId));
        headerMap.put("svix-timestamp", Collections.singletonList(svixTimestamp));
        headerMap.put("svix-signature", Collections.singletonList(svixSignature));

        return HttpHeaders.of(headerMap, (s1, s2) -> true);
    }
}
