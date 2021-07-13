package com.tiki.auction.controller;

import com.tiki.auction.domain.Chat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@Slf4j
public class ChatController {

    // 채팅 메세지 전달
    @MessageMapping("/chat/{room}")//앞에 /app이 붙으면 자동으로 연결
    @SendTo("/topic/chat/{room}")
    public Chat broadcasting(Chat chat) {

        chat.setSendDate(new Date());
        return chat;
    }

}