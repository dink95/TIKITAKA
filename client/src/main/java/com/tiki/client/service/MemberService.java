package com.tiki.client.service;


import com.tiki.client.domain.MemberDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;


@Service("MemberService")
public class MemberService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createMember(MemberDTO memberDTO) throws Exception{
        return webClient.post()
                .uri("/mbr/unAuth")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public String login(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/unAuth/login")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public MemberDTO Detail(String id, String token){
        return webClient.get()
                .uri("/mbr/Auth/{id}",id)
                .header(HttpHeaders.AUTHORIZATION,id)
                .header(HttpHeaders.AUTHORIZATION,token)
                .retrieve()
                .bodyToMono(MemberDTO.class) //반환정보
                .block();
    }

    public int deleteMember(String mbrId, String token){
        return webClient.delete()
                .uri("/mbr/Auth/{mbrId}",mbrId)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public String findId(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/unAuth/id")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public Boolean findPwd(String id, String name ,String phone){
        return webClient.get()
                .uri("/mbr/unAuth/{id}/{name}/{phone}",id,name,phone)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public int updatePwd(MemberDTO memberDTO) {
        return webClient.patch()
                .uri("/mbr/unAuth/password")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public boolean existId(String id){
        return webClient.get()
                .uri("/mbr/unAuth/existence/id/{id}",id)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public boolean existPhone(String phone){
        return webClient.get()
                .uri("/mbr/unAuth/existence/phone/{phone}",phone)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public boolean existEmail(String email){
        return webClient.get()
                .uri("/mbr/unAuth/existence/email/{email}",email)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public String certifyEmail(String id, String token){
        return webClient.get()
                .uri("/mbr/Auth/emailrollcheck/{id}",id)
                .header(HttpHeaders.AUTHORIZATION,id)
                .header(HttpHeaders.AUTHORIZATION,token)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public int updateMemberInfo(MemberDTO memberDTO) {
        return webClient.patch()
                .uri("/mbr/info")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int updateMemberRole(MemberDTO memberDTO) {
        return webClient.patch()
                .uri("/mbr/role")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int updateMemberPoints(MemberDTO memberDTO){
        return webClient.patch()
                .uri("/mbr/points")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int updateMemberGrade(MemberDTO memberDTO){
        return webClient.patch()
                .uri("/mbr/grade")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}
