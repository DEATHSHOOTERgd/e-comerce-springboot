package com.example.demo.api.extentions;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RequestContextAccessor {
    public Mono<String> getTraceId() {
        return Mono.deferContextual(context -> {
            String traceId = context.getOrDefault("traceId", "NOT-AVAILABLE");
            return Mono.just(traceId);
        });
    }
}
