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

    public int updateProduct(ProductDTO productDTO) {
        return webClient.patch()
                .uri("/prd/update")
                .body(Mono.just(productDTO), ProductDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int deleteProduct(Integer prodNo,String selId) {
        return webClient.delete()
                .uri("/prd/{prodNo}/{selId}",prodNo,selId)
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

    public List productQuerytListHighPrice(String prodNm, Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/highprice/{prodNm}/{catNo}",prodNm,catNo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQuerytListLowPrice(String prodNm, Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/lowprice/{prodNm}/{catNo}",prodNm,catNo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryProdNmList(String prodNm) throws Exception {
        return webClient.get()
                .uri("/prd/result/prodNm/{prodNm}",prodNm)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryProdNmListHighPrice(String prodNm) throws Exception {
        return webClient.get()
                .uri("/prd/result/prodNm/highPrice/{prodNm}",prodNm)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryProdNmListLowPrice(String prodNm) throws Exception {
        return webClient.get()
                .uri("/prd/result/prodNm/lowPrice/{prodNm}",prodNm)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    //판매중
    public List productQuerySelIdList(String selId) throws Exception {
        return webClient.get()
                .uri("/prd/result/selId/{selId}",selId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }
    //판매완료
    public List productQuerySelIdListFinish(String selId) throws Exception {
        return webClient.get()
                .uri("/prd/result/selId/finish/{selId}",selId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryCatNoList(Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/catNo/{catNo}",catNo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryCatNoListHighPrice(Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/catNo/highPrice/{catNo}",catNo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List productQueryCatNoListLowPrice(Integer catNo) throws Exception {
        return webClient.get()
                .uri("/prd/result/catNo/lowPrice/{catNo}",catNo)
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

    public int updateProdfinish(ProductDTO productDTO) {
        return webClient.patch()
                .uri("/prd/prodfinish")
                .body(Mono.just(productDTO), ProductDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}