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
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createMember(MemberDTO memberDTO) throws Exception{
        return webClient.post()
                .uri("/mbr")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public String login(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/login")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public String idcheck(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/idcheck")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public String findid(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/id")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public Boolean findpwd(String id, String name ,String phone){
        return webClient.get()
                .uri("/mbr/{id}/{name}/{phone}",id,name,phone)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public int update(MemberDTO memberDTO) {
        return webClient.patch()
                .uri("/mbr/password")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}
