package com.shinhan.heehee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
		// 메시지를 구독하는 요청 url -> 메시지를 받을 때
		// Message Broker에게 전달됨
        config.enableSimpleBroker("/topic");
        
        // 메시지를 발행하는 요청 url -> 메시지를 보낼 때
        // @MessageMapping 메소드가 할당된 곳으로 매핑됨
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// stomp 접속 url -> /ws-stomp
    	registry.addEndpoint("/ws") //연결될 엔드포인트
                .withSockJS(); //SocketJS를 연결한다는 설정
    }

}

