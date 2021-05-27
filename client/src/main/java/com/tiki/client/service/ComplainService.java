package com.tiki.client.service;

import com.tiki.client.domain.ComplainDTO;
import com.tiki.client.domain.MemberDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service("ComplainService")
public class ComplainService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public int insertComplain(ComplainDTO complainDTO){
        return webClient.post()
                .uri("/comp")
                .body(Mono.just(complainDTO), ComplainDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    /**
     * 신고 게시판 삭제 API
     * @param idx : 게시판 PK
     * @return 성공 시, 1 아닐 시 , 0
     */
    public int deleteComplain(int idx){
        return webClient.delete()
                .uri("/comp/{idx}",idx)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public List<ComplainDTO> selectBySpt(String spt){
        return webClient.get()
                .uri("/comp/spt/{spt}",spt)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List<ComplainDTO> selectByRepo(String repo){
        return webClient.get()
                .uri("/comp/repo/{repo}",repo)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

}
