package com.tiki.client.controller;


import com.tiki.client.domain.AuctionDTO;
import com.tiki.client.domain.AuctionChatDTO;
import com.tiki.client.service.AuctionService;
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

    @Autowired
    private AuctionService auctionService;

    @MessageMapping("/chat/join")

    public void join(AuctionDTO auctionDTO ) {
        auctionDTO.setMbrId(auctionDTO.getMbrId());
        template.convertAndSend("/subscribe/chat/room/first" + auctionDTO.getProdNo(), auctionDTO);
    }

    @MessageMapping("/chat/message")
    public void message(AuctionChatDTO auctionChatDTO) {
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setMbrId(auctionChatDTO.getMbrId());
        auctionDTO.setEndBid(Integer.parseInt( auctionChatDTO.getMessage()));
        auctionService.updateBid(auctionDTO);
        template.convertAndSend("/subscribe/chat/room/" + auctionChatDTO.getProdNo(), auctionChatDTO);
    }
}
