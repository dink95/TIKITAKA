package com.tiki.client.service;

import com.tiki.client.domain.ComplainDTO;
import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.paging.SearchDTO;
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


    public int insertComplain(ComplainDTO complainDTO,String mbrId, String token){
        return webClient.post()
                .uri("/comp/insert")
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
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
    public int deleteComplain(int idx, String mbrId, String token){
        return webClient.delete()
                .uri("/comp/{idx}",idx)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public List<ComplainDTO> selectBySpt(String spt,String mbrId, String token){
        return webClient.get()
                .uri("/comp/spt/{spt}",spt)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List<ComplainDTO> selectByRepo(String repo,String mbrId, String token){
        return webClient.get()
                .uri("/comp/repo/{repo}",repo)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }


    public int getTotalCount(SearchDTO searchDTO,String mbrId ,String token){
        return webClient.post()
                .uri("/comp/totalcount")
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .body(Mono.just(searchDTO), SearchDTO.class)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public List<ComplainDTO> selectAllComplains(SearchDTO searchDTO, String mbrId, String token){
        return webClient.post()
                .uri("/comp")
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .body(Mono.just(searchDTO), SearchDTO.class)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();

    }


    public ComplainDTO selectComplainDetailByIndex(int idx, String mbrId, String token){
        return webClient.get()
                .uri("/comp/{idx}",idx)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(ComplainDTO.class) //반환정보
                .block();
    }

    public List<ComplainDTO> selectListNotRead(){
        return webClient.get()
                .uri("/comp/not")
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public List<ComplainDTO> selectListAlreadyRead(){
        return webClient.get()
                .uri("/comp/already")
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }


}
