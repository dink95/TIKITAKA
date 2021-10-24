package com.tiki.client.service;

import com.tiki.client.domain.BuyListDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("BuyListService")
public class BuyListService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int insertBuyList(BuyListDTO buyListDTO){
        return webClient.post()
                .uri("/buylist")
                .body(Mono.just(buyListDTO), BuyListDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public List selectBuyList(String buyerId){
        return webClient.get()
                .uri("/buylist/showbuylist/{buyerId}",buyerId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }
}