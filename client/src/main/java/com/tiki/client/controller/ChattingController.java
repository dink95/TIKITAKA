package com.tiki.client.controller;

import com.tiki.client.domain.AuctionChatDTO;
import com.tiki.client.domain.ChatDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChattingController {

    @Autowired
    private ChatService chatService;


    @GetMapping(value = "/member/chatting/chat") /*채팅방 페이지*/
    public ModelAndView chat() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/chatting/chat");
        return view;
    }

    @GetMapping(value = "/member/chatting/room") /*채팅방 페이지*/
    public ModelAndView room() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/chatting/room");
        return view;
    }

    @PostMapping("/chat/create")  /*채팅 생성*/
    @ResponseBody
    public Map<String, Object> createChat(ChatDTO chatDTO) {

        Map<String, Object> resultMap = new HashMap<>();
        int result = 0;
        System.out.println(chatDTO);
        try {
            result = chatService.createChat(chatDTO);
            if (result > 0) {
                resultMap.put("resultCode", 200);
            } else {
                resultMap.put("resultCode", 400);
            }
        } catch (Exception e) {
            resultMap.put("resultCode", 500);
        }
        return resultMap;
    }

    @RequestMapping("/chat/list")  /*채팅 리스트 보기 */
    @ResponseBody
    public Map<String, Object> chatList(@RequestParam(value = "prodNo") int prodNo,
                                        @RequestParam(value = "roomNo") int roomNo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list = chatService.chatList(prodNo, roomNo);
            resultMap.put("chatList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }


    @RequestMapping("/chat/getRoomNo")  /*룸넘버 조회 */
    @ResponseBody
    public Map<String, Object> getRoomNo(@RequestParam(value = "prodNo") int prodNo,
                                         @RequestParam(value = "sendId") String sendId,
                                            @RequestParam(value = "recipientId") String recipientId) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = 0;

        try {
            result = chatService.getRoomNo(prodNo, sendId, recipientId);
            resultMap.put("roomNo", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/list/id")  /*채팅 리스트 보기 */
    @ResponseBody
    public Map<String, Object> chatListById(@RequestParam(value = "sendId") String sendId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list = chatService.chatListById(sendId);
            resultMap.put("chatList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/last/id")  /*마지막 채팅 recipientId 받아오기 */
    @ResponseBody
    public Map<String, Object> chatListById(@RequestParam(value = "prodNo") int prodNo,
                                            @RequestParam(value = "roomNo") int roomNo) {
        Map<String, Object> resultMap = new HashMap<>();
        ChatDTO chatDTO = new ChatDTO();
        try {
            chatDTO = chatService.LastChatId(prodNo, roomNo);
            resultMap.put("LastChatList", chatDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/readcount/id")  /*안읽은 메세지 개수 보기 */
    @ResponseBody
    public Map<String, Object> chatReadCountById(@RequestParam(value = "recipientId") String recipientId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list = chatService.chatReadCountById(recipientId);
            resultMap.put("readCountList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/readcount/prodNo/roomNo")  /*안읽은 메세지 개수 보기 */
    @ResponseBody
    public Map<String, Object> chatReadCountByProdNoRoomNo(@RequestParam(value = "prodNo") int prodNo,
                                                 @RequestParam(value = "roomNo") int roomNo) {
        Map<String, Object> resultMap = new HashMap<>();
       int readCount = 0;
        try {
            readCount = chatService.chatReadCountByProdNoRoomNo(prodNo, roomNo);
            resultMap.put("readCount", readCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/updateView")  /*읽은 채팅 조회 업데이트*/
    @ResponseBody
    public int updateViewChat(@RequestParam(value="prodNo") int prodNo,
                              @RequestParam(value="roomNo") int roomNo) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int result = 0;
        try {
            result = chatService.updateViewChat(prodNo,roomNo);
        } catch (Exception e) {

        }
        return result;
    }

    @RequestMapping("/chat/updateReadCount")  /*안읽은 채팅 readcount 추가*/
    @ResponseBody
    public int updateReadCount(@RequestParam(value="prodNo") int prodNo,
                              @RequestParam(value="roomNo") int roomNo) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int result = 0;
        try {
            result = chatService.updateReadCount(prodNo,roomNo);
        } catch (Exception e) {

        }
        return result;
    }


}
