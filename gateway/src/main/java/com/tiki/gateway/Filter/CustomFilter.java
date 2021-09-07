package com.tiki.gateway.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

   public static class Config {
       //Put the configuration info
   }

    public CustomFilter(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
       //pre filter
       return (exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: req id -> {}", request.getId());

            //Custom post filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom POST filter: res id -> {}", response.getRawStatusCode());
            }));
        } ;
    }
}
