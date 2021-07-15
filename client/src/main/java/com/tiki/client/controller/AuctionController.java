package com.tiki.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


@Controller
public class AuctionController {
    @GetMapping(value = "/auction/list") /*채팅방 페이지*/
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("auction/list");
        return view;
    }

    @GetMapping(value = "/auction/detail") /*채팅방 페이지*/
    public ModelAndView detail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("auction/detail");
        return view;
    }

}
