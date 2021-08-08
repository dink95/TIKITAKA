package com.tiki.client.service;

import com.tiki.client.domain.AuctionChatDTO;
import com.tiki.client.domain.ChatDTO;
import com.tiki.client.domain.InsertProductDTO;
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

    public int createChat(ChatDTO chatDTO) throws Exception {
        return webClient.post()
                .uri("/chat")
                .body(Mono.just(chatDTO), ChatDTO.class)
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

    //채팅 리스트
    public List chatList(ChatDTO chatDTO) throws Exception {
        return webClient.get()
                .uri("/chat/allchat")
                .retrieve()
                .bodyToMono(List.class) //반환정보
                .block();
    }

    //룸넘버
    public int getRoomNo(ChatDTO chatDTO) throws Exception {
        return webClient.get()
                .uri("/chat/roomNo")
                .retrieve()
                .bodyToMono(Integer.class) //반환정보
                .block();
    }

}
