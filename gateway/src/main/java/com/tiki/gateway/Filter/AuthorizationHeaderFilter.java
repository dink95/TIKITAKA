package com.tiki.gateway.Filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment env;


    public AuthorizationHeaderFilter(Environment env){
        super(Config.class);
        this.env = env;
    }

    public static class Config{

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String mbrId =  request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(1);

            String jwt = authorizationHeader.replace("Bearer ", "");

            String token = isJwtValid(jwt);

            //id와 디코드한 토큰의 값은 같아야 한다.
            if (token==null||(token!=mbrId)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

        private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(httpStatus);

            log.error(err);
            return response.setComplete();
        }

        private String isJwtValid(String jwt) {

            String subject = null;

            try {
                subject = Jwts.parser().setSigningKey("tiki")
                        .parseClaimsJws(jwt).getBody()
                        .getSubject();

            } catch (Exception ex) {
                subject = null;
            }

            if (subject == null || subject.isEmpty()) {
                subject = null;
            }

            return subject;
        }



}
