package com.tiki.client.controller;


import com.tiki.client.domain.AuctionDTO;
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
    public void join(AuctionDTO auctionDTO) {
        auctionDTO.setMbrId(auctionDTO.getMbrId());
        template.convertAndSend("/subscribe/chat/room/" + auctionDTO.getProdNo(), auctionDTO);
    }

    @MessageMapping("/chat/message")
    public void message(AuctionDTO auctionDTO) {
        template.convertAndSend("/subscribe/chat/room/" + auctionDTO.getProdNo(), auctionDTO);
    }
}
