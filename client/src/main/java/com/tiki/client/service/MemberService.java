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

    public MemberDTO Detail(String userId){
        return webClient.get()
                .uri("/mbr/{id}",userId)
                .retrieve()
                .bodyToMono(MemberDTO.class) //반환정보
                .block();
    }

    public int deleteMember(String mbrId){
        return webClient.delete()
                .uri("/mbr/{mbrId}",mbrId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public String findId(MemberDTO memberDTO){
        return webClient.post()
                .uri("/mbr/id")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }

    public Boolean findPwd(String id, String name ,String phone){
        return webClient.get()
                .uri("/mbr/{id}/{name}/{phone}",id,name,phone)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public int updatePwd(MemberDTO memberDTO) {
        return webClient.patch()
                .uri("/mbr/password")
                .body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public boolean existId(String id){
        return webClient.get()
                .uri("/mbr/existence/id/{id}",id)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public boolean existPhone(String phone){
        return webClient.get()
                .uri("/mbr/existence/phone/{phone}",phone)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public boolean existEmail(String email){
        return webClient.get()
                .uri("/mbr/existence/email/{email}",email)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(Boolean.class) //반환정보
                .block();
    }

    public String certifyEmail(String id){
        return webClient.get()
                .uri("/mbr/emailrollcheck/{id}",id)
                //.body(Mono.just(memberDTO), MemberDTO.class)
                .retrieve()
                .bodyToMono(String.class) //반환정보
                .block();
    }


}
