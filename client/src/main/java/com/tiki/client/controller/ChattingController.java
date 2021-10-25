package com.tiki.client.controller;

import com.tiki.client.domain.AuctionChatDTO;
import com.tiki.client.domain.ChatDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChattingController {

    @Autowired
    private ChatService chatService;

    /******* 채팅방 *******/
    @GetMapping(value = "/member/chatting/chat") /*채팅방 페이지*/
    public ModelAndView chat() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/chatting/chat");
        return view;
    }

    @PostMapping("/chat/create")  /*채팅 생성*/
    @ResponseBody
    public Map<String, Object> createChat(ChatDTO chatDTO, HttpServletRequest request) {
        Cookie idCookie = WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        Map<String, Object> resultMap = new HashMap<>();
        int result = 0;
        try {
            result = chatService.createChat(chatDTO,idCookie.getValue(),tokenCookie.getValue());
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

    @PostMapping("/chat/getRoomNo")  /*룸넘버 조회 */
    @ResponseBody
    public Map<String, Object> getRoomNo(@RequestParam(value = "prodNo") int prodNo,
                                         @RequestParam(value = "sendId") String sendId,
                                         @RequestParam(value = "recipientId") String recipientId,
                                         HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        Map<String, Object> resultMap = new HashMap<>();
        int result = 0;

        try {
            result = chatService.getRoomNo(prodNo, sendId, recipientId, idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("roomNo", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/list")  /*채팅 리스트 보기 */
    @ResponseBody
    public Map<String, Object> chatList(@RequestParam(value = "prodNo") int prodNo,
                                        @RequestParam(value = "roomNo") int roomNo,
                                        HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;

        try {
            list = chatService.chatList(prodNo, roomNo,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("chatList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @RequestMapping("/chat/updateView")  /*읽은 채팅 업데이트*/
    @ResponseBody
    public int updateViewChat(@RequestParam(value="prodNo") int prodNo,
                              @RequestParam(value="roomNo") int roomNo,
                              @RequestParam(value="loginId") String loginId,
                              HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        HashMap<String,Object> resultMap = new HashMap<>();
        int result = 0;
        try {
            result = chatService.updateViewChat(prodNo,roomNo,loginId,idCookie.getValue(),tokenCookie.getValue());
        } catch (Exception e) {

        }
        return result;
    }


    /******* 채팅방 목록 ******/
    @GetMapping(value = "/member/chatting/room") /*채팅방 목록 페이지*/
    public ModelAndView room() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/chatting/room");
        return view;
    }


    @RequestMapping("/chat/list/id")  /*채팅방 목록 보기 */
    @ResponseBody
    public Map<String, Object> chatListById(@RequestParam(value = "sendId") String sendId,
                                            HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> list = null;
        try {
            list = chatService.chatListById(sendId,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("chatList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }


    @RequestMapping("/chat/readCount")  /*안읽은 메세지 개수 */
    @ResponseBody
    public Map<String, Object> selectReadCount(@RequestParam(value="prodNo") int prodNo,
                                               @RequestParam(value="roomNo") int roomNo,
                                               @RequestParam(value="loginId") String loginId,
                                               HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        HashMap<String,Object> resultMap = new HashMap<>();
        int readCount = 0;
        try {
            readCount = chatService.selectReadCount(prodNo,roomNo,loginId,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("readCount",readCount);
        } catch (Exception e) {

        }
        return resultMap;
    }

    @RequestMapping("/chat/readAllCount")  /*안읽은 메세지 개수 */
    @ResponseBody
    public Map<String, Object> selectAllReadCount(@RequestParam(value="loginId") String loginId,
                                                  HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        HashMap<String,Object> resultMap = new HashMap<>();
        int readCount = 0;
        try {
            readCount = chatService.selectAllReadCount(loginId,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("readAllCount",readCount);
        } catch (Exception e) {

        }
        return resultMap;
    }

    @RequestMapping("/chat/sendCount")  /*상대방이 안읽은 메세지 개수 */
    @ResponseBody
    public Map<String, Object> selectSendCount(@RequestParam(value="prodNo") int prodNo,
                                               @RequestParam(value="roomNo") int roomNo,
                                               @RequestParam(value="loginId") String loginId,
                                               HttpServletRequest request) {
        Cookie idCookie =WebUtils.getCookie(request, "mbrId");
        Cookie tokenCookie =WebUtils.getCookie(request, "token");
        HashMap<String,Object> resultMap = new HashMap<>();
        int sendCount = 0;
        try {
            sendCount = chatService.selectSendCount(prodNo,roomNo,loginId,idCookie.getValue(),tokenCookie.getValue());
            resultMap.put("sendCount",sendCount);
        } catch (Exception e) {

        }
        return resultMap;
    }



}
