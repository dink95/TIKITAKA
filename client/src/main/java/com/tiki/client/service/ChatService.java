package com.tiki.client.service;

import com.tiki.client.domain.AuctionChatDTO;
import com.tiki.client.domain.ChatDTO;
import com.tiki.client.domain.InsertProductDTO;
import com.tiki.client.domain.ProductDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("ChatService")
public class ChatService {

    private WebClient webClient = WebClient.builder() //공통적으로 모든 요청에 사용되는 webclient 정보
            .baseUrl("http://localhost:8079") //gateway url
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public int createChat(ChatDTO chatDTO,String mbrId, String token) throws Exception {
        return webClient.post()
                .uri("/chat")
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .body(Mono.just(chatDTO), ChatDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    //채팅 리스트
    public List chatList(int prodNo , int roomNo,String mbrId, String token) throws Exception {
        return webClient.get()
                .uri("/chat/allChat/{prodNo}/{roomNo}",prodNo,roomNo)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    //룸넘버
    public int getRoomNo(int prodNo, String sendId, String recipientId,String mbrId, String token) throws Exception {
        return webClient.get()
                .uri("/chat/roomNo/{prodNo}/{sendId}/{recipientId}",prodNo,sendId,recipientId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    //채팅 목록 -> 특정 아이디로 조회
    public List chatListById(String sendId,String mbrId, String token) throws Exception {
        return webClient.get()
                .uri("/chat/existChat/{sendId}",sendId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    public int updateViewChat(int prodNo, int roomNo, String loginId ,String mbrId, String token) {
        return webClient.patch()
                .uri("/chat/updateView/{prodNo}/{roomNo}/{loginId}",prodNo,roomNo,loginId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int selectReadCount(int prodNo, int roomNo, String loginId,String mbrId, String token ) {
        return webClient.get()
                .uri("/chat/readCount/{prodNo}/{roomNo}/{loginId}",prodNo,roomNo,loginId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int selectAllReadCount(String loginId ,String mbrId, String token) {
        return webClient.get()
                .uri("/chat/readAllCount/{loginId}",loginId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    public int selectSendCount(int prodNo, int roomNo, String loginId ,String mbrId, String token) {
        return webClient.get()
                .uri("/chat/sendCount/{prodNo}/{roomNo}/{loginId}",prodNo,roomNo,loginId)
                .header(HttpHeaders.AUTHORIZATION,token)
                .header(HttpHeaders.AUTHORIZATION,mbrId)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }







}
