package com.tiki.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChattingController {


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

}
