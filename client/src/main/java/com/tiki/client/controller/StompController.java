package com.tiki.client.controller;


import com.tiki.client.domain.AuctionDTO;
import com.tiki.client.domain.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    private final SimpMessagingTemplate template;

    @Autowired
    public StompController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/chat/join")
    public void join(ChatDTO chatDTO) {
        chatDTO.setMbrId(chatDTO.getMbrId());
        template.convertAndSend("/subscribe/chat/room/" + chatDTO.getProdNo(), chatDTO);
    }

    @MessageMapping("/chat/message")
    public void message(ChatDTO chatDTO) {
        template.convertAndSend("/subscribe/chat/room/" + chatDTO.getProdNo(), chatDTO);
    }
}
