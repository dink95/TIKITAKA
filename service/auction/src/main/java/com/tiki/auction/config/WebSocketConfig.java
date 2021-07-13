package com.tiki.auction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //webSocket 서버 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");//이로 시작하는 메세지가 메시지 브로커에 라우팅
		config.setApplicationDestinationPrefixes("/app");//로 시작하는 메세지가 message-handling method로 라우팅
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpoint").setAllowedOrigins("http://localhost:8080").withSockJS();
		//웹소켓을 지원하지 않는 브라우저에 폴백 옵션 활성화
		//fall back d리나 어떤 기능이 약해지거나 제대로 동작 못할 시, 이를 대처하는 기능 또는 동작

	}
}