package com.tiki.client.service;

import com.tiki.client.domain.BlackListDTO;
import com.tiki.client.domain.ManagerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("BlackListService")
public class BlackListService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int insertBlackList(BlackListDTO blackListDTO,String mbrId, String token){
        return webClient.post()
                .uri("/black")
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)

                .body(Mono.just(blackListDTO), BlackListDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public List<BlackListDTO> selectAllBlackList(String mbrId, String token){
        return webClient.get()
                .uri("/black")
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)

                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }


}
