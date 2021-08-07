package com.tiki.product.controller;

import com.tiki.product.domain.ChatDTO;
import com.tiki.product.service.ChatService;
import com.tiki.product.domain.ProductDTO;
import com.tiki.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ProductService productService;

    // 채팅 insert
    @PostMapping("/chat")
    public int insertChat(@RequestBody ChatDTO chatDTO){

        ChatDTO dto = chatDTO;

        int prodNo = dto.getProdNo();
        if(productService.selectRoomCount(prodNo) == 0) {

            productService.updateRoomCount(prodNo);
            dto.setRoomNo(productService.selectRoomCount(prodNo));
            return chatService.insertChat(dto);

        }
        else {

            Integer roomNo = chatService.findRoomNoSendId(dto);

            if(roomNo == null) {
                productService.updateRoomCount(prodNo);
                dto.setRoomNo(productService.selectRoomCount(prodNo));
                return chatService.insertChat(dto);
            }
            else {
                int realRoomNo = roomNo.intValue();
                dto.setRoomNo(realRoomNo);
                return chatService.insertChat(dto);
            }
        }
    }

    // prodNo + roomNo 에 해당하는 전체 채팅 보기
    @GetMapping(value = "/chat/allChat")
    public List selectAllChat(@RequestBody ChatDTO chatDTO) {

        List<ChatDTO> chatList;

        chatList = chatService.selectAllChat(chatDTO);

        if(chatList == null) {
            System.out.println("에러가 나왔으니 확인바람");
        }

        return chatList;

    }

    // 특정 chatIdx에 해당하는 채팅만 보기
    @GetMapping("/chat/{chatIdx}")
    public ChatDTO selectChatDetail(@PathVariable("chatIdx") int chatIdx) {
        return chatService.selectChatDetail(chatIdx);
    }


}
