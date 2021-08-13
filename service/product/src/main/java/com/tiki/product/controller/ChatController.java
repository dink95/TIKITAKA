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
    @GetMapping(value="/chat/roomNo/{prodNo}/{sendId}/{recipientId}")
    public int selectRoomNumber(@PathVariable("prodNo") int prodNo,
                                @PathVariable("sendId") String sendId,
                                @PathVariable("recipientId") String recipientId) {


        int roomNo;
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setSendId(sendId);
        chatDTO.setRecipientId(recipientId);

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


    // prodNo, roomNo 로 마지막 대화 recipient Id 받아오기
    @GetMapping(value = "/chat/lastChat/id/{prodNo}/{roomNo}")
    public ChatDTO selectLastChatId(@PathVariable("prodNo") int prodNo,
                                    @PathVariable("roomNo") int roomNo) {

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setRoomNo(roomNo);
        return  chatService.selectLastChatId(chatDTO);

    }

    // 특정 chatIdx에 해당하는 채팅만 보기
    @GetMapping("/chat/{chatIdx}")
    public ChatDTO selectChatDetail(@PathVariable("chatIdx") int chatIdx) {
        return chatService.selectChatDetail(chatIdx);
    }

    // recipientId 에 해당하는 readCount 보기
    @GetMapping(value = "/chat/chatReadCount/{recipientId}")
    public List selectChatReadCount(@PathVariable("recipientId") String recipientId) {

        List<ChatDTO> chatList;
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setRecipientId(recipientId);
        chatList = chatService.selectChatReadCountById(chatDTO);
        return chatList;
    }

    // prodNo,roomNo 에 해당하는 readCount 보기
    @GetMapping(value = "/chat/chatReadCount/{prodNo}/{roomNo}")
    public int selectChatReadCountByProdNoRoomNo(@PathVariable("prodNo") int prodNo,
                                    @PathVariable("roomNo") int roomNo) {

        int readCount;
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setRoomNo(roomNo);
        readCount = chatService.selectChatReadCountByProdNoRoomNo(chatDTO);
        return readCount;
    }

    // 읽은 메세지 업데이트
    @PatchMapping("/chat/updateView/{prodNo}/{roomNo}")
    public int updateViewChat(@PathVariable("prodNo") int prodNo,
                              @PathVariable("roomNo") int roomNo){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setRoomNo(roomNo);
        return chatService.updateViewChat(chatDTO);
    }

    // 안읽은 메세지 업데이트
    @PatchMapping("/chat/updateReadCount/{prodNo}/{roomNo}")
    public int updateChatReadCount(@PathVariable("prodNo") int prodNo,
                              @PathVariable("roomNo") int roomNo){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setProdNo(prodNo);
        chatDTO.setRoomNo(roomNo);
        return chatService.updateChatReadCount(chatDTO);
    }


}
