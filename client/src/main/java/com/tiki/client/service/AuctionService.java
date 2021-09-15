package com.tiki.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.client.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuctionService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int insertAuctionProduct(AuctionDTO auctionDTO){
        return webClient.post()
                .uri("/auction")
                .body(Mono.just(auctionDTO), AuctionDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int updateBid(AuctionDTO auctionDTO){
        return webClient.patch()
                .uri("/auction")
                .body(Mono.just(auctionDTO), AuctionDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public AuctionDTO selectAuctionByProdNo(int prodNo){
        return webClient.get()
                .uri("/auction/{prodNo}",prodNo)
                .retrieve()
                .bodyToMono(AuctionDTO.class) //반환정보
                .block();
    }

    public int insertBiddingProduct(BidDTO bidDTO){
        return webClient.post()
                .uri("/auction/bid")
                .body(Mono.just(bidDTO), BidDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int updateBiddingProduct(BidDTO bidDTO){
        return webClient.patch()
                .uri("/auction/bid")
                .body(Mono.just(bidDTO), BidDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public List<String> selectAllBiddingProduct(String mbrId){
        return webClient.get()
                .uri("/auction/bid/{id}",mbrId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }
}
