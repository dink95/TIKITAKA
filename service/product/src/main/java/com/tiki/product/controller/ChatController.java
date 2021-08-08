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
    @GetMapping(value = "/chat/allChat/{prodNo}/{roomNo}")
    public List selectAllChat(@PathVariable("prodNo") int prodNo,
                              @PathVariable("roomNo") int roomNo) {

        List<ChatDTO> chatList;
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setRoomNo(roomNo);
        chatList = chatService.selectAllChat(chatDTO);

        if(chatList == null) {
            System.out.println("에러가 나왔으니 확인바람");
        }

        return chatList;

    }

    // prodNo + sendId + recipientId 에 해당하는 룸 넘버 보내기
    @GetMapping("/chat/roomNo")
    public int selectRoomNumber(@RequestBody ChatDTO chatDTO) {

        int roomNo;
        Integer check = chatService.findRoomNoSendId(chatDTO);

        if(check == null) {
            System.out.println("상품번호 혹은 보내는 사람 혹은 받는 사람의 아이디가 잘못됨");
            roomNo = 0;
        }
        else {
            roomNo = check.intValue();
        }

        return roomNo;

    }

    // 해당 Id가 참가하고 있는 채팅 목록 불러오기
    @GetMapping(value = "/chat/existChat/{sendId}")
    public List selectExistChatList(@PathVariable("sendId") String sendId) {

        List<ChatDTO> chatList;

        chatList = chatService.selectExistChatList(sendId);

        return chatList;
    }

    // 특정 chatIdx에 해당하는 채팅만 보기
    @GetMapping("/chat/{chatIdx}")
    public ChatDTO selectChatDetail(@PathVariable("chatIdx") int chatIdx) {
        return chatService.selectChatDetail(chatIdx);
    }


}
