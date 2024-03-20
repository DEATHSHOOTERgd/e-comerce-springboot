package com.example.demo.api.filters;

import com.example.demo.application.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JWTFilter implements WebFilter {


    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(exchange.getRequest().getPath().toString().startsWith("/api/v1/auth")){
            return chain.filter(exchange);
        }

        if(token.startsWith("Bearer ")){
            token=token.substring(7);
        }

        if(!jwtUtils.validateToken(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
