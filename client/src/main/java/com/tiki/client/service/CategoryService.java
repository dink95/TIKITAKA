package com.tiki.client.service;

import com.tiki.client.domain.CategoryDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service("CategoryService")
public class CategoryService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public CategoryDTO getCategory(Integer catNo) {
        return webClient.get()
                .uri("/cat/{catNo}", catNo)
                .retrieve()
                .bodyToMono(CategoryDTO.class) //반환정보
                .block();
    }

    public List<CategoryDTO> getAllCategory(CategoryDTO categoryDTO) {
        return webClient.get()
                .uri("/cat/list")
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }
}
