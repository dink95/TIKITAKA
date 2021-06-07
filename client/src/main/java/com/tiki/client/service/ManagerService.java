package com.tiki.client.service;


import com.tiki.client.domain.ManagerDTO;
import com.tiki.client.domain.MemberDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("ManagerService")
public class ManagerService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public String loginManager(ManagerDTO managerDTO){
        return webClient.post()
                .uri("/man/login")
                .body(Mono.just(managerDTO), ManagerDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public ManagerDTO selectManagerDetail(String manId){
        return webClient.get()
                .uri("/mbr/{manId}",manId)
                .retrieve()
                .bodyToMono(ManagerDTO.class) //반환정보
                .block();
    }

}
