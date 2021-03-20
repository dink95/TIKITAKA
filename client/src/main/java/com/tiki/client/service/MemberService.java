package com.tiki.client.service;


import com.tiki.client.domain.MemberDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service("MemberService")
public class MemberService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8080") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createMember(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}
