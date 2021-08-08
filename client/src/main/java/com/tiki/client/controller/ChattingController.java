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
    public Map<String, Object> chatList(ChatDTO chatDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list= chatService.chatList(chatDTO);
            resultMap.put("chatList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/getRoomNo")  /*룸넘버 조회 */
    @ResponseBody
    public Map<String, Object> getRoomNo(ChatDTO chatDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        int result=0;
        System.out.println(chatDTO);
        try {
            result = chatService.getRoomNo(chatDTO);
            resultMap.put("roomNo", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

}
