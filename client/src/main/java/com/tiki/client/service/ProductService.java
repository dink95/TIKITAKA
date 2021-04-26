package com.tiki.client.service;


import com.tiki.client.domain.ProductDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service("ProductService")
public class ProductService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createProduct(ProductDTO productDTO) throws Exception {
        return webClient.post()
                .uri("/prd")
                .body(Mono.just(productDTO), ProductDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}