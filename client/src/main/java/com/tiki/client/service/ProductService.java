package com.tiki.client.service;

import com.tiki.client.domain.InsertProductDTO;
import com.tiki.client.domain.ProductDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service("ProductService")
public class ProductService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createProduct(InsertProductDTO productDTO) throws Exception {
        return webClient.post()
                .uri("/prd")
                .body(Mono.just(productDTO), InsertProductDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public List productList() throws Exception {
        return webClient.get()
                .uri("/prd/list")
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQuerytList(String prodNm, Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/{prodNm}/{catNo}",prodNm,catNo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public ProductDTO productDetail(Integer prodNo) {
        return webClient.get()
                .uri("/prd/{prodNo}",prodNo)
                .retrieve()
                .bodyToMono(ProductDTO.class) //반환정보
                .block();
    }
    /*public boolean existEmail(String email){
        return webClient.get()
                .uri("/mbr/existence/email/{email}",email)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }*/

}